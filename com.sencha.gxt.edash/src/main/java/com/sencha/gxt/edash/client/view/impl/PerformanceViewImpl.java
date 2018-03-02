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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SplitButtonCell;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent.StoreFilterHandler;
import com.sencha.gxt.edash.client.LoggingAsyncCallback;
import com.sencha.gxt.edash.client.place.PerformancePlace;
import com.sencha.gxt.edash.client.view.PerformanceView;
import com.sencha.gxt.edash.client.widget.chart.CandleStickSeries;
import com.sencha.gxt.edash.shared.model.CompanyStats;
import com.sencha.gxt.edash.shared.model.Performance;
import com.sencha.gxt.edash.shared.model.PerformancePropertyAccess;
import com.sencha.gxt.edash.shared.model.ReportDownload;
import com.sencha.gxt.theme.edash.custom.client.base.button.WhiteButtonCellAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.WhiteCheckMenuItemAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.WhiteMenuAppearance;
import com.sencha.gxt.widget.core.client.button.SplitButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public class PerformanceViewImpl implements PerformanceView {

  public interface MyUiBinder extends UiBinder<Widget, PerformanceViewImpl> {
  }

  @Inject
  private PlaceController placeController;

  private MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

  private PerformancePropertyAccess performanceProps = GWT.create(PerformancePropertyAccess.class);
  private final ListStore<Performance> store = new ListStore<Performance>(performanceProps.key());

  @UiField(provided = true)
  SplitButton companyButton;
  @UiField
  Label companyNameLabel;
  @UiField
  Label changeLabel;
  @UiField
  Label priceLabel;
  @UiField
  Label maxminLabel;
  @UiField
  Label volumeLabel;
  @UiField
  StatementsContainer statementsContainer;

  private Widget widget;

  private Delegate delegate;
  private String companyFilter = "";
  private Set<String> uniqueCompanies = Collections.EMPTY_SET;

  public PerformanceViewImpl() {
    store.addStoreFilterHandler(new StoreFilterHandler<Performance>() {
      @Override
      public void onFilter(StoreFilterEvent<Performance> event) {
        // this basically applies to all uibinder widgets
        if (changeLabel == null || companyFilter == null || "".equals(companyFilter)) {
          return;
        }

        delegate.getCompanyStats(companyFilter, new LoggingAsyncCallback<CompanyStats>(this) {
          @Override
          public void onSuccess(CompanyStats result) {
            NumberFormat format = NumberFormat.getFormat("0.00");
            NumberFormat plusMinusFormat = NumberFormat.getFormat("+0.00;-0.00");
            companyNameLabel.setText(result.getLabel());
            changeLabel.setText(plusMinusFormat.format(result.getChange())
                + " (" + plusMinusFormat.format(result.getChangePercentage()) + "%)");
            priceLabel.setText("" + result.getPrice());
            maxminLabel.setText(format.format(result.getMax()) + "/" + format.format(result.getMin()));
            volumeLabel.setText(result.getVolume() + " m");
          }
        });
      }
    });
  }

  @Override
  public Widget asWidget() {
    if (widget == null) {
      companyButton = new SplitButton(new SplitButtonCell(new WhiteButtonCellAppearance<String>()) {
        @Override
        public boolean isClickOnArrow(XElement p, NativeEvent e) {
          return ((WhiteButtonCellAppearance) getAppearance()).isClickOnArrow(p, e);
        }
      });

      widget = uiBinder.createAndBindUi(this);

      delegate.getStatements(new LoggingAsyncCallback<List<ReportDownload>>(this) {
        @Override
        public void onSuccess(List<ReportDownload> result) {
          setStatements(result);
        }
      });
    }
    return widget;
  }

  @Override
  public void setCompany(String company) {
    if (company == null || "".equals(company)) {
      if (!uniqueCompanies.isEmpty()) {
        placeController.goTo(new PerformancePlace(uniqueCompanies.iterator().next()));
        return;
      }
    }
    companyFilter = company;
    if (companyButton != null) {
      companyButton.setText(company);
    }
    updateCheckBoxes();
    updateStoreFilters();
  }


  @Override
  public void setData(List<Performance> data) {
    uniqueCompanies = getUniqueCompanies(data);

    store.clear();
    store.addAll(data);
    store.applySort(true);
    updateStoreFilters();
  }

  @Override
  public void setDelegate(Delegate delegate) {
    this.delegate = delegate;
  }


  @UiFactory
  protected Chart<Performance> createChart() {
    final Chart chart = new Chart();

    StoreSortInfo<Performance> sortInfo = new StoreSortInfo<Performance>(performanceProps.date(), SortDir.ASC);
    store.addSortInfo(sortInfo);
    store.addFilter(new StoreFilter<Performance>() {
      @Override
      public boolean select(Store<Performance> store, Performance parent, Performance item) {
        return companyFilter.equals(item.getCompany());
      }
    });
    store.setEnableFilters(true);


    final NumericAxis<Performance> yAxis = new NumericAxis<Performance>();
    yAxis.setPosition(Position.RIGHT);
    yAxis.addField(performanceProps.low());
    yAxis.addField(performanceProps.high());
    yAxis.addField(performanceProps.open());
    yAxis.addField(performanceProps.close());
    yAxis.setDisplayGrid(true);
    yAxis.setAdjustMaximumByMajorUnit(true);
    yAxis.setAdjustMinimumByMajorUnit(true);


    chart.addAxis(yAxis);


    CategoryAxis<Performance, Date> catAxis = new CategoryAxis<Performance, Date>();
    catAxis.setPosition(Position.BOTTOM);
    catAxis.setField(performanceProps.date());
    catAxis.setLabelProvider(new LabelProvider<Date>() {
      @Override
      public String getLabel(Date item) {
        return DateTimeFormat.getFormat("MMM yyyy").format(item);
      }
    });
    catAxis.setLabelStepRatio(4);
    chart.addAxis(catAxis);


    CandleStickSeries<Performance> highSeries = new CandleStickSeries<Performance>();
    highSeries.setYAxisPosition(Position.LEFT);


    highSeries.addYField(performanceProps.open());
    highSeries.addYField(performanceProps.close());
    highSeries.addYField(performanceProps.low());
    highSeries.addYField(performanceProps.high());
    highSeries.addColor(new RGB(34, 198, 239));
    highSeries.addColor(new RGB(241, 73, 91));


    chart.bindStore(store);

    chart.addSeries(highSeries);

    chart.setBackground(new RGB(250, 250, 250));


    return chart;
  }


  @UiHandler("companyButton")
  public void onCompanyChange(SelectEvent event) {
    // go to next item in list of companies
    String currentCompany = companyButton.getText();
    String goTo = null;

    for (Iterator<String> iter = uniqueCompanies.iterator(); ; iter.hasNext()) {
      if (currentCompany.equals(iter.next())) {
        if (iter.hasNext()) {
          goTo = iter.next();
          break;
        } else {
          goTo = uniqueCompanies.iterator().next();
          break;
        }
      }
    }
    companyButton.setText(goTo);
    updateCheckBoxes();
    placeController.goTo(new PerformancePlace(goTo));
  }


  @Override
  public void setStatements(List<ReportDownload> reportDownloads) {
    statementsContainer.setReportDownloads(reportDownloads);
  }

  private Set<String> getUniqueCompanies(List<Performance> performanceList) {
    Set<String> companies = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
    for (Performance p : performanceList) {
      companies.add(p.getCompany());
    }

    Menu companyMenu = new Menu(new WhiteMenuAppearance());

    final List<CheckMenuItem> menuItems = new ArrayList<CheckMenuItem>();

    for (final String companyName : companies) {
      final CheckMenuItem item = new CheckMenuItem(new WhiteCheckMenuItemAppearance());
      item.setText(companyName);
      menuItems.add(item);
      if (companyFilter.equals(companyName)) {
        item.setChecked(true);
      }
      item.addSelectionHandler(new SelectionHandler<Item>() {
        @Override
        public void onSelection(SelectionEvent<Item> event) {
          for (CheckMenuItem menuItem : menuItems) {
            menuItem.setChecked(false);
          }
          item.setChecked(true);
          companyButton.setText(companyName);
          placeController.goTo(new PerformancePlace(companyName));
        }
      });
      companyMenu.add(item);
    }

    companyButton.setText(companyFilter);
    companyButton.setMenu(companyMenu);

    return companies;
  }

  private void updateStoreFilters() {
    store.setEnableFilters(false);
    store.setEnableFilters(true);
  }

  private void updateCheckBoxes() {
    if (companyButton != null) {
      for (int i = 0; i < companyButton.getMenu().getWidgetCount(); i++) {
        CheckMenuItem item = (CheckMenuItem) companyButton.getMenu().getWidget(i);
        item.setChecked(companyFilter.equals(item.getText()));
      }
    }
  }
}
