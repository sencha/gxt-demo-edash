package com.sencha.gxt.edash.shared.mock;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.edash.shared.EdashServiceAsync;
import com.sencha.gxt.edash.shared.model.CompanyStats;
import com.sencha.gxt.edash.shared.model.FullProfitLoss;
import com.sencha.gxt.edash.shared.model.Kpi;
import com.sencha.gxt.edash.shared.model.News;
import com.sencha.gxt.edash.shared.model.Performance;
import com.sencha.gxt.edash.shared.model.ProfitLoss;
import com.sencha.gxt.edash.shared.model.ReportDownload;

import java.util.List;

/**
 * Stub implementation of the EdashServiceAsync to keep everything client side
 */
public class EdashClientAsyncStub extends RESTClient implements EdashServiceAsync {


  @Override
  public void getAllNews(final AsyncCallback<List<News>> callback) {
    fetchData("news_data.json", new AsyncCallback<List<News>>() {
      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(caught);
      }

      @Override
      public void onSuccess(List<News> result) {
        if (result == null) {
          callback.onSuccess(null);
        } else {
          callback.onSuccess(result);
        }
      }
    });
  }

  @Override
  public void getAllPerformance(final AsyncCallback<List<Performance>> callback) {
    fetchData("qs_data.json", new AsyncCallback<List<Performance>>() {
      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(caught);
      }

      @Override
      public void onSuccess(List<Performance> result) {
        callback.onSuccess(result);
      }
    });
  }

  @Override
  public void getCompanyStats(String companyName, AsyncCallback<CompanyStats> async) {
    if ("AAPL".equals(companyName)) {

      /*
          change: "+0.12 (+1.29%)",
          price: '9.38',
          maxMin: '9.39/9.30',
          volume: '154.4 m',
          label: 'APPLE INC'
      */
      async.onSuccess(new CompanyStats("APPLE, INC", 0.12, 1.29, 9.38, 9.39, 9.30, 154.4));
      return;
    } else if ("GOOG".equals(companyName)) {
      /*
          change: "+13.25 (2.40%)",
          price: '565.95',
          maxMin: '566.00/554.35',
          volume: '171.1 m',
          label: 'GOOGLE, INC'
       */
      async.onSuccess(new CompanyStats("GOOGLE, INC", 13.25, 2.40, 565.95, 566.00, 554.35, 171.1));
    } else {
      async.onFailure(new RuntimeException("Unknown company " + companyName));
    }
  }

  @Override
  public void getReportDownloads(final AsyncCallback<List<ReportDownload>> callback) {
    fetchData("dv_data.json", callback);
  }


  @Override
  public void getKpiData(AsyncCallback<List<Kpi>> callback) {
    fetchData("kpi_data.json", callback);
  }

  @Override
  public void getProfitLoss(final AsyncCallback<ProfitLoss> callback) {
    fetchData("full_data.json", new AsyncCallback<List<FullProfitLoss>>() {
      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(caught);
      }

      @Override
      public void onSuccess(List<FullProfitLoss> result) {
        ProfitLoss profitLoss = new ProfitLoss();
        profitLoss.setFullProfitLoss(result);
        callback.onSuccess(profitLoss);
      }
    });
  }
}
