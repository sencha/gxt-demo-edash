package com.sencha.gxt.theme.edash.custom.client.base.button;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.button.Css3ButtonCellAppearance;

public class EdashToggleButtonAppearance extends Css3ButtonCellAppearance<Boolean> {

  interface Style extends Css3ButtonStyle {

  }

  interface Resources extends Css3ButtonResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/button/Css3ButtonCell.gss", "EdashToggleButton.gss"})
    Style style();
  }

  public EdashToggleButtonAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
