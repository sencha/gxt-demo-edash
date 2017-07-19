package com.sencha.gxt.edash.shared.model;


import java.io.Serializable;

public class Statistic implements Serializable {
  private String status;
  private String description;
  private double ratio;


  public Statistic() {

  }

  public Statistic(String status, String description, double ratio) {
    this.status = status;
    this.description = description;
    this.ratio = ratio;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getRatio() {
    return ratio;
  }

  public void setRatio(double ratio) {
    this.ratio = ratio;
  }
}
