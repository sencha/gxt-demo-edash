package com.sencha.gxt.edash.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class PerformancePlace extends BasePlace {

  @Prefix("quarterly")
  public static class Tokenizer implements PlaceTokenizer<PerformancePlace> {

    @Override
    public PerformancePlace getPlace(String token) {
      return new PerformancePlace(token);
    }

    @Override
    public String getToken(PerformancePlace place) {
      return place.company;
    }
  }

  private String company;

  public PerformancePlace() {
    this("");
  }

  public PerformancePlace(String company) {
    this.company = company;
  }

  public String getCompany() {
    return company;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PerformancePlace that = (PerformancePlace) o;

    if (company != null ? !company.equals(that.company) : that.company != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return company != null ? company.hashCode() : 0;
  }

  @Override
  public String toString() {
    return super.toString() + ":" + company;
  }
}
