package com.sencha.gxt.edash.client.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.sencha.gxt.edash.client.ApplicationController;


/**
 *
 */
@GinModules(EdashModule.class)
public interface EdashGuiGinjector extends Ginjector {

  ApplicationController applicationController();
}
