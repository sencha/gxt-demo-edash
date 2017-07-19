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
