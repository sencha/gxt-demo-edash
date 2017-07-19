package com.sencha.gxt.edash.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class LoggingAsyncCallback<T> implements AsyncCallback<T> {
  private Object instance;
  private Level level;

  public LoggingAsyncCallback() {
    this(null);
  }

  public LoggingAsyncCallback(Object instance) {
    this(instance, Level.WARNING);
  }

  public LoggingAsyncCallback(Object instance, Level level) {
    if (instance == null) {
      instance = this;
    }
    this.instance = instance;
    this.level = level;
  }

  @Override
  public void onFailure(Throwable caught) {
    Logger.getLogger(instance.getClass().getName()).log(level, "error", caught);
  }
}
