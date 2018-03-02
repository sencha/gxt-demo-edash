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
