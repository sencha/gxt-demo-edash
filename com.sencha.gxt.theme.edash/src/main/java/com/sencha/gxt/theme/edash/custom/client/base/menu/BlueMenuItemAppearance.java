/**
 * Sencha GXT 1.0.0-SNAPSHOT - Sencha for GWT
 * Copyright (c) 2006-2018, Sencha Inc.
 *
 * licensing@sencha.com
 * http://www.sencha.com/products/gxt/license/
 *
 * ================================================================================
 * Commercial License
 * ================================================================================
 * This version of Sencha GXT is licensed commercially and is the appropriate
 * option for the vast majority of use cases.
 *
 * Please see the Sencha GXT Licensing page at:
 * http://www.sencha.com/products/gxt/license/
 *
 * For clarification or additional options, please contact:
 * licensing@sencha.com
 * ================================================================================
 *
 *
 *
 *
 *
 *
 *
 *
 * ================================================================================
 * Disclaimer
 * ================================================================================
 * THIS SOFTWARE IS DISTRIBUTED "AS-IS" WITHOUT ANY WARRANTIES, CONDITIONS AND
 * REPRESENTATIONS WHETHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, MERCHANTABLE QUALITY,
 * FITNESS FOR A PARTICULAR PURPOSE, DURABILITY, NON-INFRINGEMENT, PERFORMANCE AND
 * THOSE ARISING BY STATUTE OR FROM CUSTOM OR USAGE OF TRADE OR COURSE OF DEALING.
 * ================================================================================
 */
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
