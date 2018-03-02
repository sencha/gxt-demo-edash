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
