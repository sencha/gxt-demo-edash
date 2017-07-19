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
