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
package com.sencha.gxt.edash.client.widget.chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextBaseline;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.edash.shared.model.Data;
import com.sencha.gxt.edash.shared.model.DataPropertyAccess;
import com.sencha.gxt.widget.core.client.Composite;

public class GaugeChart extends Composite {

  interface GaugeStyle extends CssResource {
    String chart();
  }

  interface GaugeChartResources extends ClientBundle {
    @Source("GaugeChart.gss")
    GaugeStyle style();
  }

  private DataPropertyAccess dataPropertyAccess = GWT.create(DataPropertyAccess.class);
  ListStore<Data> store = new ListStore<Data>(dataPropertyAccess.nameKey());

  private GaugeChartResources resources = GWT.create(GaugeChartResources.class);

  public GaugeChart(String title, int percentage, Color color) {
    StyleInjectorHelper.ensureInjected(resources.style(), true);

    store.add(new Data(title, percentage, 0, 0, 0, 0, 0, 0, 0, 0));
    store.add(new Data("", (100 - percentage), 0, 0, 0, 0, 0, 0, 0, 0));

    Chart<Data> chart = new Chart<Data>();
    //chart.setDefaultInsets(10);
    chart.setStore(store);
    chart.setShadowChart(false);

    final MyPieSeries<Data> series = new MyPieSeries<Data>();
    series.setAngleField(dataPropertyAccess.data1());
    series.addColor(color);
    series.addColor(new RGB(236, 236, 236));
    series.setDonut(85d);


    TextSprite textConfig = new TextSprite();
    textConfig.setFont("Arial");
    textConfig.setTextBaseline(TextBaseline.MIDDLE);
    textConfig.setFontSize(18);
    textConfig.setTextAnchor(TextAnchor.MIDDLE);
    textConfig.setZIndex(15);


    SeriesLabelConfig<Data> labelConfig = new SeriesLabelConfig<Data>();
    labelConfig.setSpriteConfig(textConfig);
    labelConfig.getSpriteConfig().setFill(new RGB(105, 112, 138));

    labelConfig.setValueProvider(dataPropertyAccess.data1(), new LabelProvider<Double>() {
      @Override
      public String getLabel(Double item) {
        return item + "%";
      }
    });
    series.setLabelConfig(labelConfig);

    chart.addSeries(series);
    chart.addStyleName(resources.style().chart());


    final Legend<Data> legend = new Legend<Data>();
    legend.setPosition(Position.BOTTOM);
    legend.setItemHighlighting(true);
    legend.setItemHiding(true);
    legend.getBorderConfig().setStrokeWidth(0);
    legend.setChart(chart);

    initWidget(chart);
  }
}
