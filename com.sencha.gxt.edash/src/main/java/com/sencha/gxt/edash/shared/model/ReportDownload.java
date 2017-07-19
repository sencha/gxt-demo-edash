package com.sencha.gxt.edash.shared.model;

import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;

public class ReportDownload extends JavaScriptObject implements Serializable {

  public final native String getName() /*-{ return this.name; }-*/;
  public final native String getTitle() /*-{ return this.title; }-*/;
  public final native String getThumb() /*-{ return this.thumb; }-*/;
  public final native String getUrlString() /*-{ return this.url; }-*/;
  public final native String getType() /*-{ return this.type; }-*/;
  public final native String getUploaded() /*-{ return this.uploaded; }-*/;

  protected ReportDownload() {
  }
}
