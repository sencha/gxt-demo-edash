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
package com.sencha.gxt.edash.shared.model;


import java.io.Serializable;

public class CompanyStats implements Serializable {
  private String label;
  private double change;
  private double changePercentag;
  private double price;
  private double max;
  private double min;
  private double volume;

  public CompanyStats() {
  }

  public CompanyStats(String label, double change, double changePercentag, double price, double max, double min, double volume) {
    this.label = label;
    this.change = change;
    this.changePercentag = changePercentag;
    this.price = price;
    this.max = max;
    this.min = min;
    this.volume = volume;
  }

  public String getLabel() {
    return label;
  }

  public double getChange() {
    return change;
  }

  public void setChange(double change) {
    this.change = change;
  }

  public double getChangePercentage() {
    return changePercentag;
  }

  public void setChangePercentag(double changePercentag) {
    this.changePercentag = changePercentag;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
  }

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
  }

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }
}
