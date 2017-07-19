package com.sencha.gxt.edash.shared.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Kpi extends JavaScriptObject implements Serializable {

  public final Date getDate() {
    return DateTimeFormat.getFormat("MMMM yyyy").parse(getDateString());
  }

  public final native int getId() /*-{ return this.id; }-*/;
  public final native String getType() /*-{ return this.type; }-*/;
  private final native String getDateString() /*-{ return this.date; }-*/;
  public final native double getData1() /*-{ return this.data1; }-*/;
  public final native double getData2() /*-{ return this.data2; }-*/;




  protected Kpi() {
  }
}
