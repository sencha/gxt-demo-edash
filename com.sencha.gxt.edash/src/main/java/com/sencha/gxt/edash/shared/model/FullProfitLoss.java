package com.sencha.gxt.edash.shared.model;

import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;


public class FullProfitLoss extends JavaScriptObject implements Serializable {
  protected FullProfitLoss() {
  }

  public final native int getId() /*-{
    return this.id;
  }-*/;

  public final native String account() /*-{
    return this.account;
  }-*/;

  public final native String getRegion() /*-{
    return this.region;
  }-*/;

  public final native String getRegionFilter() /*-{
    return this.region_filter;
  }-*/;


  public final native double getColumnData(String columnName) /*-{
    return this[columnName];
  }-*/;

  public final native String[] getColumnNames() /*-{
    var items = new Array();
    for (m in this) {
      if (m[0] == "q") {
        items.push(m);
      }
    }
    return items;
  }-*/;
}
