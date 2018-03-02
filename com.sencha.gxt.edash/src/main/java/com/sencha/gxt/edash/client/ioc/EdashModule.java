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
package com.sencha.gxt.edash.client.ioc;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Provides;
import com.sencha.gxt.edash.client.place.EdashActivityMapper;
import com.sencha.gxt.edash.client.view.CompanyNewsView;
import com.sencha.gxt.edash.client.view.KpiOverviewView;
import com.sencha.gxt.edash.client.view.PerformanceView;
import com.sencha.gxt.edash.client.view.ProfitAndLossView;
import com.sencha.gxt.edash.client.view.ShellView;
import com.sencha.gxt.edash.client.view.impl.CompanyNewsViewImpl;
import com.sencha.gxt.edash.client.view.impl.KpiOverviewViewImpl;
import com.sencha.gxt.edash.client.view.impl.PerformanceViewImpl;
import com.sencha.gxt.edash.client.view.impl.ProfitAndLossViewImpl;
import com.sencha.gxt.edash.client.view.impl.ShellViewImpl;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.mock.EdashClientAsyncStub;

import javax.inject.Singleton;

/**
 *
 */
public class EdashModule extends AbstractGinModule {
  @Override
  protected void configure() {
    bind(EdashServiceAsync.class).to(EdashClientAsyncStub.class);

    bind(ActivityMapper.class).to(EdashActivityMapper.class).asEagerSingleton();

    bind(ShellView.class).to(ShellViewImpl.class).asEagerSingleton();

    bind(KpiOverviewView.class).to(KpiOverviewViewImpl.class);
    bind(PerformanceView.class).to(PerformanceViewImpl.class);
    bind(ProfitAndLossView.class).to(ProfitAndLossViewImpl.class);
    bind(CompanyNewsView.class).to(CompanyNewsViewImpl.class);
  }

  @Provides
  @Singleton
  public EventBus provideEventBus() {
    return new SimpleEventBus();
  }

  @SuppressWarnings("deprecation")
  @Provides
  @Singleton
  public PlaceController providePlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }
}
