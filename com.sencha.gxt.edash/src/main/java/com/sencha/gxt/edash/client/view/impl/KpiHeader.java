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
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.widget.core.client.Composite;


public class KpiHeader extends Composite {

  interface Style extends CssResource {
    String grid();

    String cell();

    String value();
  }

  interface Resources extends ClientBundle {
    @Source("KpiHeader.gss")
    Style style();
  }

  private Resources resources = GWT.create(Resources.class);

  public KpiHeader() {
    StyleInjectorHelper.ensureInjected(resources.style(), true);

    Grid grid = new Grid(1, 5);
    grid.addStyleName(resources.style().grid());
    grid.setCellPadding(0);
    grid.setCellSpacing(0);
    grid.setWidth("100%");


    for (int i = 0; i < grid.getRowCount(); i++) {
      for (int j = 0; j < grid.getColumnCount(); j++) {
        grid.getCellFormatter()
            .addStyleName(i, j, resources.style().cell());
      }
    }

    grid.setWidget(0, 0, item("10", "CAMPAIGNS"));
    grid.setWidget(0, 1, item("20,560", "OPPORTUNITIES"));
    grid.setWidget(0, 2, item("10,000", "CLOSED WON"));
    grid.setWidget(0, 3, item("$90,200", "TOTAL SALES"));
    grid.setWidget(0, 4, item("71%", "GOALS MET"));

    initWidget(grid);
  }


  private HTML item(String value, String title) {
    SafeHtmlBuilder builder = new SafeHtmlBuilder();
    builder.appendHtmlConstant("<span class=")
        .appendHtmlConstant(resources.style().value())
        .appendHtmlConstant(">")

        .appendEscaped(value)
        .appendHtmlConstant("</span>")

        .appendEscaped(title);

    return new HTML(builder.toSafeHtml());
  }
}
