package com.sencha.gxt.edash.shared.model;


import com.google.gwt.core.client.JavaScriptObject;

import java.io.Serializable;

public class News extends JavaScriptObject implements Serializable {

  public final native int getId() /*-{ return this.news_id; }-*/;
  public final native String getTitle() /*-{ return this.title; }-*/;
  public final native String getParagraph() /*-{ return this.paragraph; }-*/;
  public final native String getDate() /*-{ return this.date; }-*/;
  public final native String getTime() /*-{ return this.time; }-*/;
  public final native String getAuthor() /*-{ return this.author; }-*/;
  public final native String getGroup() /*-{ return this.group; }-*/;
  public final native String getPhoto() /*-{ return this.image; }-*/;
  public final native String getType() /*-{ return this.type; }-*/;


  protected News() {
  }
}
