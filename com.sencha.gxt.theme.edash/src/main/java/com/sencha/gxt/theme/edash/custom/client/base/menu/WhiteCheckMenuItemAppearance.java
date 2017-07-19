package com.sencha.gxt.theme.edash.custom.client.base.menu;


import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.theme.edash.client.base.menu.Css3CheckMenuItemAppearance;

public class WhiteCheckMenuItemAppearance extends Css3CheckMenuItemAppearance {

  interface Style extends Css3MenuItemStyle {

  }

  interface Resources extends Css3CheckMenuItemResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/menu/Css3MenuItem.gss", "WhiteMenuItem.gss"})
    Style style();
  }

  public WhiteCheckMenuItemAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
