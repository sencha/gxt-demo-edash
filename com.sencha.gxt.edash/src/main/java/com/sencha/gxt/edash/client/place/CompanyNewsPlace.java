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
