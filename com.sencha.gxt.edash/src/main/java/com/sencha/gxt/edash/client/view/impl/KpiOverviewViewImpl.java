/**
 * Sencha GXT 1.0.0-SNAPSHOT - Sencha for GWT
 * Copyright (c) 2006-2018, Sencha Inc.
 *
 * licensing@sencha.com
 * http://www.sencha.com/products/gxt/license/
 *
 * ================================================================================
 * Commercial License
 * ================================================================================
 * This version of Sencha GXT is licensed commercially and is the appropriate
 * option for the vast majority of use cases.
 *
 * Please see the Sencha GXT Licensing page at:
 * http://www.sencha.com/products/gxt/license/
 *
 * For clarification or additional options, please contact:
 * licensing@sencha.com
 * ================================================================================
 *
 *
 *
 *
 *
 *
 *
 *
 * ================================================================================
 * Disclaimer
 * ================================================================================
 * THIS SOFTWARE IS DISTRIBUTED "AS-IS" WITHOUT ANY WARRANTIES, CONDITIONS AND
 * REPRESENTATIONS WHETHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, MERCHANTABLE QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, DURABILITY, NON-INFRINGEMENT, PERFORMANCE AND
 * THOSE ARISING BY STATUTE OR FROM CUSTOM OR USAGE OF TRADE OR COURSE OF DEALING.
 * ================================================================================
 */
package com.sencha.gxt.edash.client.view.impl;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ToggleButtonCell;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.AreaSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace.PerformanceMetric;
import com.sencha.gxt.edash.client.view.KpiOverviewView;
import com.sencha.gxt.edash.client.widget.chart.GaugeChart;
import com.sencha.gxt.edash.client.widget.chart.RGBA;
import com.sencha.gxt.edash.shared.model.Kpi;
import com.sencha.gxt.edash.shared.model.KpiPropertyAccess;
import com.sencha.gxt.edash.shared.model.Statistic;
import com.sencha.gxt.theme.edash.custom.client.base.button.EdashToggleButtonAppearance;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

/**
 *
 */
public class KpiOverviewViewImpl implements KpiOverviewView {

  private static final double MIN_HEIGHT = 700d;
  private static final double MIN_WIDTH = 560d;

  interface MyUiBinder extends UiBinder<Widget, KpiOverviewViewImpl> {
  }

  private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);


  interface Style extends CssResource {
    String statisticsHeader();

    String statisticsBody();

    @ClassName("sparkline-inner")
    String sparklineInner();

    String sparkline();

    @ClassName("statistic-tag")
    String statisticTag();

    @ClassName("statistic-description")
    String statisticDescription();

    String ended();

    String active();

    String paused();

  }

  interface Resources extends ClientBundle {
    @Source("Kpi.gss")
    Style style();
  }

  interface DataRenderer extends XTemplates {
    @XTemplate(source = "statisticsBody.html")
    public SafeHtml renderTemplate(Style style, List<Statistic> items);
  }

  @Inject
  private PlaceController placeController;

  private KpiPropertyAccess kpiPropertyAccess = GWT.create(KpiPropertyAccess.class);
  private Resources resources = GWT.create(Resources.class);
  private DataRenderer dataRenderer = GWT.create(DataRenderer.class);


  @UiField(provided = true)
  GaugeChart inStoreChart;

  @UiField(provided = true)
  GaugeChart onlineChart;

  @UiField(provided = true)
  Chart performanceChart;

  @UiField
  ToggleButton clicksButton;
  @UiField
  ToggleButton wonButton;
  @UiField
  ToggleButton salesButton;
  @UiField
  ToggleButton goalsButton;
  @UiField
  HTML statisticsPanel;

  private PerformanceMetric filterMetric = PerformanceMetric.CLICKS;
  private ToggleGroup metricToggleGroup;

  private Widget widget;

  private ListStore<Kpi> kpiStore = new ListStore<Kpi>(kpiPropertyAccess.key());

  public KpiOverviewViewImpl() {
    StyleInjectorHelper.ensureInjected(resources.style(), true);

    StoreSortInfo<Kpi> sortInfo = new StoreSortInfo<Kpi>(kpiPropertyAccess.date(), SortDir.ASC);
    kpiStore.addSortInfo(sortInfo);
    kpiStore.addFilter(new StoreFilter<Kpi>() {
      @Override
      public boolean select(Store<Kpi> store, Kpi parent, Kpi item) {
        return filterMetric.getType().equals(item.getType());
      }
    });
    kpiStore.setEnableFilters(true);
  }

  @Override
  public Widget asWidget() {
    if (widget == null) {
      inStoreChart = new GaugeChart("IN STORE", 25, new RGB(42, 200, 239));
      onlineChart = new GaugeChart("ONLINE", 50, new RGB(17, 200, 151));
      performanceChart = createPerformanceChart();

      Widget content = uiBinder.createAndBindUi(this);

      final VerticalLayoutData outerLayoutData = new VerticalLayoutData(1, 1);
      final VerticalLayoutContainer outer = new VerticalLayoutContainer();
      outer.add(content, outerLayoutData);
      outer.setScrollMode(ScrollMode.AUTO);

      outer.addResizeHandler(new ResizeHandler() {
        @Override
        public void onResize(ResizeEvent event) {
          outerLayoutData.setHeight(event.getHeight() < MIN_HEIGHT ? MIN_HEIGHT : 1d);
          outerLayoutData.setWidth(event.getWidth() < MIN_WIDTH ? MIN_WIDTH : 1d);
        }
      });

      widget = outer;

      clicksButton.setData("metric", PerformanceMetric.CLICKS);
      wonButton.setData("metric", PerformanceMetric.WON);
      salesButton.setData("metric", PerformanceMetric.SALES);
      goalsButton.setData("metric", PerformanceMetric.GOALS_MET);


      metricToggleGroup = new ToggleGroup();
      metricToggleGroup.add(clicksButton);
      metricToggleGroup.add(wonButton);
      metricToggleGroup.add(salesButton);
      metricToggleGroup.add(goalsButton);
      metricToggleGroup.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
        @Override
        public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
          updateFromMetricChange((ToggleButton) event.getValue());
        }
      });

      setMetric(filterMetric);

      List<Statistic> statistics = new ArrayList<Statistic>();
      statistics.add(new Statistic("active", "2 for 1, 30% off for $50", 0.5d));
      statistics.add(new Statistic("paused", "$100 for new customers", 0.3d));
      statistics.add(new Statistic("ended", "$25 referral", 0.1d));

      statisticsPanel.addStyleName(resources.style().statisticsBody());
      statisticsPanel.setHTML(dataRenderer.renderTemplate(resources.style(), statistics));
    }
    return widget;
  }

  @Override
  public void setDelegate(Delegate delegate) {
    //noop
  }


  @Override
  public void setKpiData(List<Kpi> result) {
    kpiStore.clear();
    kpiStore.addAll(result);
  }

  @Override
  public void setMetric(PerformanceMetric metric) {
    this.filterMetric = metric;
    if (metricToggleGroup == null) {
      return;
    }

    switch (metric) {
      case CLICKS:
        metricToggleGroup.setValue(clicksButton, true);
        break;
      case WON:
        metricToggleGroup.setValue(wonButton, true);
        break;
      case SALES:
        metricToggleGroup.setValue(salesButton, true);
        break;
      case GOALS_MET:
        metricToggleGroup.setValue(goalsButton, true);
        break;
    }
  }

  private Chart createPerformanceChart() {
    final Chart<Kpi> chart = new Chart<Kpi>();
    chart.getDefaultInsets().setRight(30);


    NumericAxis<Kpi> axis = new NumericAxis<Kpi>();
    axis.setPosition(Position.LEFT);
    axis.addField(kpiPropertyAccess.data1());
    axis.addField(kpiPropertyAccess.data2());

    axis.setDisplayGrid(true);
    chart.addAxis(axis);

    CategoryAxis<Kpi, Date> catAxis = new CategoryAxis<Kpi, Date>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(kpiPropertyAccess.date());
    catAxis.setLabelProvider(new LabelProvider<Date>() {
      @Override
      public String getLabel(Date item) {
        return DateTimeFormat.getFormat("MMM yyyy").format(item);
      }
    });
    catAxis.setLabelStepRatio(17);
    catAxis.setMinorTickSteps(0);

    chart.addAxis(catAxis);

    AreaSeries<Kpi> series = new AreaSeries<Kpi>();
    series.setYAxisPosition(Position.LEFT);
    series.addYField(kpiPropertyAccess.data1());
    series.addYField(kpiPropertyAccess.data2());
    series.addColor(new RGBA(34, 198, 239, 0.5));
    series.addColor(new RGBA(241, 73, 91, 0.5));

    chart.bindStore(kpiStore);
    chart.addSeries(series);

    return chart;
  }

  @UiFactory
  protected ToggleButton createToggleButton() {
    return new ToggleButton(new ToggleButtonCell(new EdashToggleButtonAppearance()));
  }

  @UiHandler({"clicksButton", "wonButton", "salesButton", "goalsButton"})
  public void onMetricSelect(SelectEvent event) {
    ToggleButton selectedButton = (ToggleButton) event.getSource();
    if (!selectedButton.getValue()) {
      selectedButton.setValue(true);
      return;
    }
    placeController.goTo(new KpiOverviewPlace(filterMetric));

  }

  private void updateFromMetricChange(ToggleButton selectedButton) {
    filterMetric = selectedButton.getData("metric");
    for (ToggleButton button : new ToggleButton[]{clicksButton, wonButton, salesButton, goalsButton}) {
      button.setValue(false, false);
    }
    selectedButton.setValue(true, false);
    kpiStore.setEnableFilters(false);
    kpiStore.setEnableFilters(true);
  }
}
