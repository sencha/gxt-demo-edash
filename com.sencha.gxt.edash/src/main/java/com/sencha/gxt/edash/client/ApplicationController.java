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
package com.sencha.gxt.edash.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.sencha.gxt.edash.client.place.EdashPlaceHistoryMapper;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace.PerformanceMetric;
import com.sencha.gxt.edash.client.view.ShellView;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class ApplicationController {

  @Inject
  private ShellView shellView;
  @Inject
  private PlaceController placeController;
  @Inject
  private ActivityMapper activityMapper;
  @Inject
  private EventBus eventBus;


  public void start(HasWidgets hasWidgets) {

    ActivityManager mainActivityManager = new ActivityManager(activityMapper, eventBus);
    mainActivityManager.setDisplay(shellView.getDisplay());

    EdashPlaceHistoryMapper historyMapper = GWT.create(EdashPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, new KpiOverviewPlace(PerformanceMetric.CLICKS));

    historyHandler.handleCurrentHistory();

    Viewport viewport = new Viewport();
    viewport.add(shellView);
    hasWidgets.add(viewport);
  }
}
