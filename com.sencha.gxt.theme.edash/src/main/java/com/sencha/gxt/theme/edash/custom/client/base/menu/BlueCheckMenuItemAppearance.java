package com.sencha.gxt.theme.edash.custom.client.base.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem.CheckMenuItemAppearance;


public class BlueCheckMenuItemAppearance extends BlueMenuItemAppearance implements CheckMenuItemAppearance {

  public interface BlueCheckMenuItemResources extends BlueMenuItemResources {
    @Source("whiteChecked.png")
    ImageResource checked();

    ImageResource unchecked();
  }

  private final BlueCheckMenuItemResources resources;

  public BlueCheckMenuItemAppearance() {
    this(GWT.<BlueCheckMenuItemResources>create(BlueCheckMenuItemResources.class));
  }

  public BlueCheckMenuItemAppearance(BlueCheckMenuItemResources resources) {
    this.resources = resources;
  }

  @Override
  public void applyChecked(XElement parent, boolean state) {
  }

  @Override
  public XElement getCheckIcon(XElement parent) {
    return parent.<XElement>cast().selectNode("." + resources.style().menuItemIcon());
  }

  @Override
  public ImageResource checked() {
    return resources.checked();
  }

  @Override
  public ImageResource unchecked() {
    return resources.unchecked();
  }

  @Override
  public ImageResource radio() {
    return resources.checked();
  }
}
