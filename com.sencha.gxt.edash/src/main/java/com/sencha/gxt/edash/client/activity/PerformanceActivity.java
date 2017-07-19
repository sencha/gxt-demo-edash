package com.sencha.gxt.edash.client.activity;


import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sencha.gxt.edash.client.LoggingAsyncCallback;
import com.sencha.gxt.edash.client.place.PerformancePlace;
import com.sencha.gxt.edash.client.view.PerformanceView;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.model.CompanyStats;
import com.sencha.gxt.edash.shared.model.Performance;
import com.sencha.gxt.edash.shared.model.ReportDownload;

public class PerformanceActivity extends BaseActivity<PerformancePlace> implements PerformanceView.Delegate {

  @Inject
  private PerformanceView view;

  @Inject
  private EdashServiceAsync service;

  @Override
  public void start(final AcceptsOneWidget acceptsOneWidget, EventBus eventBus) {
    view.setDelegate(this);
    acceptsOneWidget.setWidget(view);

    service.getAllPerformance(new LoggingAsyncCallback<List<Performance>>() {
      @Override
      public void onSuccess(List<Performance> result) {
        view.setData(result);
        view.setCompany(getPlace().getCompany());
      }
    });

  }

  @Override
  public void getStatements(final AsyncCallback<List<ReportDownload>> callback) {
    service.getReportDownloads(callback);
  }

  @Override
  public void getCompanyStats(String companyFilter, AsyncCallback<CompanyStats> callback) {
    service.getCompanyStats(companyFilter, callback);
  }

  @Override
  public void setPlace(PerformancePlace place) {
    super.setPlace(place);
    if (place.getCompany() == null || "".equals(place.getCompany())) {
      return;
    }
    view.setCompany(place.getCompany());
  }
}
