package com.sencha.gxt.theme.edash.custom.client.base.panel;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.theme.edash.client.base.panel.Css3ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel.ContentPanelAppearance;

public class ShellContentPanelAppearance extends Css3ContentPanelAppearance implements ContentPanelAppearance {

  interface Style extends Css3ContentPanelStyle {
  }

  public interface Resources extends Css3ContentPanelResources {
    @Override
    @Source({"ShellContentPanel.gss"})
    Style style();
  }

  public ShellContentPanelAppearance() {
    super(GWT.<Resources>create(Resources.class));
  }
}
