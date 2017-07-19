package com.sencha.gxt.theme.edash.custom.client.base.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.sencha.gxt.theme.base.client.menu.MenuItemBaseAppearance;

public class BlueMenuItemAppearance extends MenuItemBaseAppearance {

  public interface BlueMenuItemResources extends MenuItemResources, ClientBundle {
    @Source("BlueMenuItemStyle.gss")
    BlueMenuItemStyle style();


  }

  public interface BlueMenuItemStyle extends MenuItemStyle {
  }

  public BlueMenuItemAppearance() {
    this(GWT.<BlueMenuItemResources>create(BlueMenuItemResources.class),
        GWT.<MenuItemTemplate>create(MenuItemTemplate.class));
  }

  public BlueMenuItemAppearance(BlueMenuItemResources resources, MenuItemTemplate template) {
    super(resources, template);
  }
}
