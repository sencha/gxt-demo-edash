package com.sencha.gxt.edash.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sencha.gxt.edash.client.LoggingAsyncCallback;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace;
import com.sencha.gxt.edash.client.view.KpiOverviewView;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.model.Kpi;

import javax.inject.Inject;
import java.util.List;

public class KpiOverviewActivity extends BaseActivity<KpiOverviewPlace> implements KpiOverviewView.Delegate {


  @Inject
  private KpiOverviewView view;

  @Inject
  private EdashServiceAsync service;

  @Override
  public void start(AcceptsOneWidget acceptsOneWidget, EventBus eventBus) {
    logger.fine("test from activity");
    view.setDelegate(this);
    acceptsOneWidget.setWidget(view);

    service.getKpiData(new LoggingAsyncCallback<List<Kpi>>() {
      @Override
      public void onSuccess(List<Kpi> result) {
        view.setKpiData(result);
      }
    });
  }


  @Override
  public void setPlace(KpiOverviewPlace place) {
    super.setPlace(place);

    view.setMetric(place.getMetric());
  }
}
