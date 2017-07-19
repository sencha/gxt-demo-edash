package com.sencha.gxt.theme.edash.custom.client.base.menu;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.menu.Css3MenuAppearance;


public class EdashBlueMenuAppearance extends Css3MenuAppearance {

  interface Style extends Css3MenuStyle {

  }

  interface Resources extends Css3MenuResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/menu/Css3Menu.gss", "BlueMenu.gss"})
    Style style();
  }

  public EdashBlueMenuAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
