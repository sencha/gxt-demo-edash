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
package com.sencha.gxt.edash.shared.model;

import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;
import java.util.Date;


public class Performance extends JavaScriptObject implements Serializable {
  private static double ID_COUNT = 0;
  private static double getNextId() {
    return ID_COUNT++;
  }

  public final Date getDate() {
    return new Date(new Double(getTime()).longValue());
  }

  public final native double getId() /*-{
    if ( ! this.id) {
      this.id = @com.sencha.gxt.edash.shared.model.Performance::getNextId()();
    }
    return this.id;
  }-*/;

  // should be a long, but can't get it to work as a long
  private final native double getTime() /*-{ return this.time; }-*/;
  public final native int getOpen() /*-{ return this.open; }-*/;
  public final native int getClose() /*-{ return this.close; }-*/;
  public final native int getLow() /*-{ return this.low; }-*/;
  public final native int getHigh() /*-{ return this.high; }-*/;
  public final int getLowHighDiff() {
    return Math.abs(getHigh() - getLow());
  }
  public final native String getCompany() /*-{ return this.company; }-*/;

  protected Performance() {
  }
}
