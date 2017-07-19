package com.sencha.gxt.theme.edash.custom.client.base.button;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.button.Css3ButtonCellAppearance;

public class BlueButtonCellAppearance<M> extends Css3ButtonCellAppearance<M> {

  interface Style extends Css3ButtonStyle {
  }

  interface Resources extends Css3ButtonResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/button/Css3ButtonCell.gss",
        "com/sencha/gxt/theme/edash/client/base/button/Css3ButtonCellToolBar.gss",
        "BlueButton.gss"})
    Style style();
  }

  public BlueButtonCellAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
