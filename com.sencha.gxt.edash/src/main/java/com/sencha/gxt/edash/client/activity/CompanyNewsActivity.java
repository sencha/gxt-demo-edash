package com.sencha.gxt.edash.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.sencha.gxt.edash.client.place.CompanyNewsPlace;
import com.sencha.gxt.edash.client.place.PerformancePlace;
import com.sencha.gxt.edash.client.view.CompanyNewsView;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.model.News;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;

public class CompanyNewsActivity extends BaseActivity<CompanyNewsPlace> implements CompanyNewsView.Delegate {

  @Inject
  private CompanyNewsView view;

  @Inject
  private EdashServiceAsync service;

  @Override
  public void start(AcceptsOneWidget acceptsOneWidget, EventBus eventBus) {
    view.setDelegate(this);

    service.getAllNews(new AsyncCallback<List<News>>() {
      @Override
      public void onFailure(Throwable caught) {
        logger.log(Level.WARNING, "error on return", caught);
      }

      @Override
      public void onSuccess(List<News> result) {
        view.setData(result);
      }
    });
    acceptsOneWidget.setWidget(view);
  }

  @Override
  public void setPlace(CompanyNewsPlace place) {
    super.setPlace(place);
    if (place.getFilter() == null || "".equals(place.getFilter())) {
      return;
    }
    view.setFilter(place.getFilter());
  }
}
