package com.sencha.gxt.edash.client.place;


import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class KpiOverviewPlace extends BasePlace {

  public static enum PerformanceMetric {
    CLICKS("clicks"),
    WON("redemption"),
    SALES("sales"),
    GOALS_MET("goalsmet");

    private String type;

    private PerformanceMetric(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }
  }


  private PerformanceMetric metric;

  public KpiOverviewPlace() {
    this.metric = PerformanceMetric.CLICKS;
  }

  public KpiOverviewPlace(PerformanceMetric metric) {
    this.metric = metric;
  }

  public PerformanceMetric getMetric() {
    return metric;
  }

  @Prefix("kpi")
  public static class Tokenizer implements PlaceTokenizer<KpiOverviewPlace> {
    @Override
    public String getToken(KpiOverviewPlace place) {
      return place.getMetric().name();
    }

    @Override
    public KpiOverviewPlace getPlace(String token) {
      PerformanceMetric metric;
      try {
        metric = PerformanceMetric.valueOf(token);
      } catch (IllegalArgumentException e) {
        metric = PerformanceMetric.CLICKS;
      }

      return new KpiOverviewPlace(metric);
    }
  }

}
