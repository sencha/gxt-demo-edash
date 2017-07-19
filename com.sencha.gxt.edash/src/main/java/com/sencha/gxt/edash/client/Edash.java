package com.sencha.gxt.edash.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.edash.client.ioc.EdashGuiGinjector;

public class Edash implements EntryPoint {

  private static final Logger logger = Logger.getLogger(Edash.class.getName());

  @Override
  public void onModuleLoad() {
    GWT.runAsync(new RunAsyncCallback() {
      @Override
      public void onFailure(Throwable reason) {
        logger.log(Level.SEVERE, "Unable to start application", reason);
        Window.alert("Some error occurred while starting application");
      }

      @Override
      public void onSuccess() {

        EdashGuiGinjector ginjector = GWT.create(EdashGuiGinjector.class);
        ginjector.applicationController().start(RootPanel.get());

      }
    });
  }
}
