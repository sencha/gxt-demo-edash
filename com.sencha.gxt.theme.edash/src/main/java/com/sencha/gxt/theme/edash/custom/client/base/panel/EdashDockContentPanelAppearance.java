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
