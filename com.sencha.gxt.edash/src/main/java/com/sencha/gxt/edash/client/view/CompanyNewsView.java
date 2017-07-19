package com.sencha.gxt.edash.client.view;

import com.sencha.gxt.edash.client.place.CompanyNewsPlace;
import com.sencha.gxt.edash.shared.model.News;

import java.util.List;

public interface CompanyNewsView extends View {

  interface Delegate {

  }

  void setDelegate(Delegate delegate);

  void setData(List<News> result);

  void setFilter(CompanyNewsPlace.NewsFilter filter);
}
