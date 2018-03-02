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
