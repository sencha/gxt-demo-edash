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
