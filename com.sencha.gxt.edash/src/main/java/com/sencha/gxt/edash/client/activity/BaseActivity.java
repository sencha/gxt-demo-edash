package com.sencha.gxt.edash.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.sencha.gxt.edash.client.place.BasePlace;

import java.util.logging.Logger;


public abstract class BaseActivity<P extends BasePlace> extends AbstractActivity {
  protected static final Logger logger = Logger.getLogger(BaseActivity.class.getName());

  private P place;

  public P getPlace() {
    return place;
  }

  public void setPlace(P place) {
    this.place = place;
  }
}
