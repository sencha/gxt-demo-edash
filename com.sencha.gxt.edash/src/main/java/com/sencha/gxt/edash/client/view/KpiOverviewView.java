package com.sencha.gxt.edash.client.view;

import com.sencha.gxt.edash.client.place.KpiOverviewPlace.PerformanceMetric;
import com.sencha.gxt.edash.shared.model.Kpi;

import java.util.List;

public interface KpiOverviewView extends View {


  interface Delegate {

  }

  void setDelegate(Delegate delegate);

  void setKpiData(List<Kpi> result);

  void setMetric(PerformanceMetric metric);
}
