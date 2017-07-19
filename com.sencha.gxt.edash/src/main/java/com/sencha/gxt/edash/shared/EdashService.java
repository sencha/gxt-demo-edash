package com.sencha.gxt.edash.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
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
@RemoteServiceRelativePath("EdashService")
public interface EdashService extends RemoteService {
  public List<News> getAllNews();

  public List<Performance> getAllPerformance();

  public CompanyStats getCompanyStats(String companyName);

  public List<ReportDownload> getReportDownloads();

  public List<Kpi> getKpiData();

  public ProfitLoss getProfitLoss();
}
