package com.sencha.gxt.edash.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.edash.shared.model.CompanyStats;
import com.sencha.gxt.edash.shared.model.FullProfitLoss;
import com.sencha.gxt.edash.shared.model.Kpi;
import com.sencha.gxt.edash.shared.model.News;
import com.sencha.gxt.edash.shared.model.Performance;
import com.sencha.gxt.edash.shared.model.ProfitLoss;
import com.sencha.gxt.edash.shared.model.ReportDownload;

import java.util.List;

/**
 *
 */
public interface EdashServiceAsync {
  void getAllNews(AsyncCallback<List<News>> async);

  void getAllPerformance(AsyncCallback<List<Performance>> async);

  void getCompanyStats(String companyName, AsyncCallback<CompanyStats> async);

  void getReportDownloads(AsyncCallback<List<ReportDownload>> async);

  void getKpiData(AsyncCallback<List<Kpi>> async);

  void getProfitLoss(AsyncCallback<ProfitLoss> callback);
}
