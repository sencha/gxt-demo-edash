package com.sencha.gxt.edash.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sencha.gxt.edash.client.LoggingAsyncCallback;
import com.sencha.gxt.edash.client.place.ProfitAndLossPlace;
import com.sencha.gxt.edash.client.view.ProfitAndLossView;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.model.ProfitLoss;

import javax.inject.Inject;

public class ProfitAndLossActivity extends BaseActivity<ProfitAndLossPlace> implements ProfitAndLossView.Delegate {

  @Inject
  private ProfitAndLossView view;


  @Inject
  private EdashServiceAsync service;

  @Override
  public void start(AcceptsOneWidget acceptsOneWidget, EventBus eventBus) {
    view.setDelegate(this);
    acceptsOneWidget.setWidget(view);
  }

  @Override
  public void getData(AsyncCallback<ProfitLoss> callback) {
    service.getProfitLoss(callback);
  }
}
