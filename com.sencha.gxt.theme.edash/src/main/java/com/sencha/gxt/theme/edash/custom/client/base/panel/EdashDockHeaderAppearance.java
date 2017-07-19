package com.sencha.gxt.theme.edash.custom.client.base.panel;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.panel.Css3HeaderAppearance;


public class EdashDockHeaderAppearance extends Css3HeaderAppearance {

  interface Style extends Css3HeaderStyle {
  }

  interface Resources extends Css3HeaderResources {
    @Override
    @Source({"com/sencha/gxt/theme/base/client/widget/Header.gss", "EdashDockHeader.gss"})
    Style style();
  }

  public EdashDockHeaderAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
