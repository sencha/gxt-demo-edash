package com.sencha.gxt.edash.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface FullProfitLossPropertyAccess extends PropertyAccess<FullProfitLoss> {

  @Path("id")
  ModelKeyProvider<FullProfitLoss> key();

  ValueProvider<FullProfitLoss, Integer> id();

  ValueProvider<FullProfitLoss, String> region();

  @Path("regionFilter")
  ValueProvider<FullProfitLoss, String> region_filter();

  ValueProvider<FullProfitLoss, String> account();


  /*
  ValueProvider<FullProfitLoss, Double> q3_2010();

  ValueProvider<FullProfitLoss, Double> q3_2011();

  ValueProvider<FullProfitLoss, Double> q2_2011();

  ValueProvider<FullProfitLoss, Double> q2_2012();

  ValueProvider<FullProfitLoss, Double> q2_2010();

  ValueProvider<FullProfitLoss, Double> q4_2010();

  ValueProvider<FullProfitLoss, Double> q4_2011();

  ValueProvider<FullProfitLoss, Double> q1_2010();

  ValueProvider<FullProfitLoss, Double> q1_2011();

  ValueProvider<FullProfitLoss, Double> q1_2012();
  */
}
