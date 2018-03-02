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
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.edash.shared.model.ReportDownload;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CssFloatLayoutContainer.CssFloatData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatementsContainer extends SimpleContainer {

  interface Style extends CssResource {
    String statementType();

    String outerWrap();

    String wrap();

    String inner();

    String thumbIcon();

    String thumbTitleContainer();

    String thumbTitle();

    String thumbTitleSmall();

    String thumbDownload();

  }

  interface Resources extends ClientBundle {
    @Source("statementsContainer.gss")
    Style style();

    @ImageOptions(width = 40)
    ImageResource pdf();

    @ImageOptions(width = 40)
    @Source("download-small.png")
    ImageResource download();
  }

  interface StatementTemplate extends XTemplates {
    @XTemplate(source = "Statement.html")
    SafeHtml createStatement(ReportDownload statement, Style style);
  }

  private VerticalLayoutContainer outerContainer = new VerticalLayoutContainer();
  private Resources resources = GWT.create(Resources.class);
  private StatementTemplate statementTemplate = GWT.create(StatementTemplate.class);

  public StatementsContainer() {
    StyleInjectorHelper.ensureInjected(resources.style(), true);

    // statementWrap
    add(outerContainer);
    addStyleName(resources.style().outerWrap());
  }


  public void setReportDownloads(List<ReportDownload> reportDownloads) {
    outerContainer.clear();

    // organize statements first by "type"
    Map<String, List<ReportDownload>> statements = new TreeMap<String, List<ReportDownload>>(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
    for (ReportDownload report : reportDownloads) {
      List<ReportDownload> list = statements.get(report.getType());
      if (list == null) {
        list = new ArrayList<ReportDownload>();
        statements.put(report.getType(), list);
      }
      list.add(report);
    }


    final CssFloatData floatData = new CssFloatData(0.5d, new Margins(0));

    ResizeHandler resizeHandler = new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent event) {
        if (event.getSource() instanceof CssFloatLayoutContainer) {
          CssFloatLayoutContainer container = (CssFloatLayoutContainer) event.getSource();

          if (container.getOffsetWidth() < 700) {
            floatData.setSize(1d);
          } else {
            floatData.setSize(0.5d);
          }
        }
      }
    };


    for (String type : statements.keySet()) {
      Label statementType = new Label(type);
      statementType.addStyleName(resources.style().statementType());
      outerContainer.add(statementType);
      CssFloatLayoutContainer container = new CssFloatLayoutContainer();
      container.addResizeHandler(resizeHandler);

      for (ReportDownload statement : statements.get(type)) {
        container.add(new HTML(statementTemplate.createStatement(statement, resources.style())), floatData);
      }

      outerContainer.add(container, new VerticalLayoutData(1, -1));
    }
  }
}
