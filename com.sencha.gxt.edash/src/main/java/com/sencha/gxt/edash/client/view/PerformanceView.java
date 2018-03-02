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
package com.sencha.gxt.edash.client.view;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.edash.shared.model.CompanyStats;
import com.sencha.gxt.edash.shared.model.Performance;
import com.sencha.gxt.edash.shared.model.ReportDownload;

import java.util.List;

public interface PerformanceView extends View {


  interface Delegate {
    void getCompanyStats(String companyFilter, AsyncCallback<CompanyStats> callback);

    void getStatements(AsyncCallback<List<ReportDownload>> callback);
  }

  public void setDelegate(Delegate delegate);

  void setData(List<Performance> result);

  void setCompany(String company);

  void setStatements(List<ReportDownload> reportDownloads);
}
