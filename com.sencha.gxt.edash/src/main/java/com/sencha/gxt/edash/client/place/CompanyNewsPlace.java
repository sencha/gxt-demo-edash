package com.sencha.gxt.edash.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

/**
 *
 */
public class CompanyNewsPlace extends BasePlace {
  public static enum NewsFilter {
    ALL_POSTS("All Posts"),
    NEWS("News"),
    FORUM("Forum");

    public String name;

    NewsFilter(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  private NewsFilter filter;

  public CompanyNewsPlace() {
    this(NewsFilter.ALL_POSTS);
  }

  public CompanyNewsPlace(NewsFilter filter) {
    this.filter = filter;
  }

  public NewsFilter getFilter() {
    return filter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CompanyNewsPlace that = (CompanyNewsPlace) o;

    if (filter != null ? filter != that.filter : that.filter != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return filter != null ? filter.hashCode() : 0;
  }

  @Override
  public String toString() {
    return super.toString() + ":" + filter.toString();
  }

  @Prefix("news")
  public static class Tokenizer implements PlaceTokenizer<CompanyNewsPlace> {

    @Override
    public CompanyNewsPlace getPlace(String token) {
      try {
        return new CompanyNewsPlace(NewsFilter.valueOf(token));
      } catch (IllegalArgumentException e) {
        return new CompanyNewsPlace();
      }
    }

    @Override
    public String getToken(CompanyNewsPlace place) {
      return place.filter.name();
    }
  }
}
