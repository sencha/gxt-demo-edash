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
