package com.sencha.gxt.theme.edash.custom.client.base.menu;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.menu.Css3MenuAppearance;


public class WhiteMenuAppearance extends Css3MenuAppearance {

  interface Style extends Css3MenuStyle {
  }

  interface Resources extends Css3MenuResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/menu/Css3Menu.gss", "WhiteMenu.gss"})
    Style style();
  }

  public WhiteMenuAppearance() {
    super(GWT.<Resources>create(Resources.class),
        GWT.<BaseMenuTemplate>create(BaseMenuTemplate.class));
  }
}
