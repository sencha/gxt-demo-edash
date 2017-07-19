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
