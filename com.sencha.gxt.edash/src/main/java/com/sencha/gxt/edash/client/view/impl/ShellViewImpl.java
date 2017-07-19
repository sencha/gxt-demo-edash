package com.sencha.gxt.edash.client.view.impl;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.edash.client.view.ShellView;
import com.sencha.gxt.edash.client.view.impl.navigation.SideNavigation;
import com.sencha.gxt.edash.client.view.impl.navigation.TopNavigation;
import com.sencha.gxt.theme.edash.custom.client.base.panel.ShellContentPanelAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;

import javax.inject.Inject;

public class ShellViewImpl implements ShellView, IsWidget, ResizeHandler {
  private static final int WIDTH_THRESHOLD = 743;

  private BorderLayoutContainer container;
  private ContentPanel display = new ContentPanel(new ShellContentPanelAppearance());

  @Inject
  private SideNavigation sideNavigation;

  @Inject
  private TopNavigation topNavigation;

  public ShellViewImpl() {
    Window.addResizeHandler(this);
  }

  @Override
  public Widget asWidget() {
    if (container == null) {

      container = new BorderLayoutContainer();

      display.setHeaderVisible(false);
      container.setCenterWidget(display, new MarginData(0));


      BorderLayoutData westData = new BorderLayoutData(232);
      container.setWestWidget(sideNavigation, westData);


      BorderLayoutData northData = new BorderLayoutData(100);
      container.setNorthWidget(topNavigation, northData);

      // hide both sides by default and allow window size choose
      container.hide(LayoutRegion.NORTH);
      container.hide(LayoutRegion.WEST);

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
        @Override
        public void execute() {
          handleResize(Window.getClientWidth(), Window.getClientHeight());
        }
      });
    }
    return container;
  }

  public AcceptsOneWidget getDisplay() {
    return new AcceptsOneWidget() {
      @Override
      public void setWidget(IsWidget w) {
        display.setWidget(w);
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            display.forceLayout();
          }
        });
      }
    };
  }

  @Override
  public void onResize(ResizeEvent resizeEvent) {
    if (GXT.isTablet()) {
      handleResize(resizeEvent.getWidth(), resizeEvent.getHeight());
    } else {
      handleResize(resizeEvent.getWidth(), WIDTH_THRESHOLD);
    }
  }

  private void handleResize(int width, int threshold) {
    if (width > threshold) {
      container.hide(LayoutRegion.NORTH);
      container.show(LayoutRegion.WEST);
    } else {
      container.hide(LayoutRegion.WEST);
      container.show(LayoutRegion.NORTH);
    }
  }
}
