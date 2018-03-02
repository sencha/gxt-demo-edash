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
