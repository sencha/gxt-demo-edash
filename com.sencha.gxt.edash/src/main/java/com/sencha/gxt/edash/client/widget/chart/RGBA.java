package com.sencha.gxt.edash.client.widget.chart;


import com.sencha.gxt.chart.client.draw.RGB;

public class RGBA extends RGB {

  private double alpha;

  public RGBA(int red, int green, int blue, double alpha) {
    super(red, green, blue);
    this.alpha = alpha;
  }


  @Override
  public String toString() {
    if (color == null) {
      color = "rgba(" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + alpha + ")";
    }
    return color;
  }
}
