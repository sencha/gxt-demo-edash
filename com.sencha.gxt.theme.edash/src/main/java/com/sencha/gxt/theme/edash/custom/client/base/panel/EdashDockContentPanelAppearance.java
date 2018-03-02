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
package com.sencha.gxt.theme.edash.custom.client.base.panel;


import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.panel.Css3ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.Header.HeaderAppearance;

public class EdashDockContentPanelAppearance extends Css3ContentPanelAppearance {

  interface Style extends Css3ContentPanelStyle {

  }

  interface Resources extends Css3ContentPanelResources {
    @Override
    @Source("EdashDockContentPanel.gss")
    Style style();
  }


  public EdashDockContentPanelAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }

  @Override
  public HeaderAppearance getHeaderAppearance() {
    return new EdashDockHeaderAppearance();
  }
}
