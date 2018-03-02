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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfitLoss implements Serializable {
  private List<FullProfitLoss> fullProfitLoss = Collections.emptyList();

  /* derived */
  private List<String> quarters;


  public List<String> getQuarters() {
    if (quarters == null) {
      Set<String> quarterSet = new HashSet<String>();
      // iterate over all data in collection and create a unique collection of quarter names
      for (FullProfitLoss item : fullProfitLoss) {
        quarterSet.addAll(Arrays.asList(item.getColumnNames()));
      }
      List<String> list = new ArrayList<String>(quarterSet);
      Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
              String[] o1P = o1.split("_");
              String[] o2P = o2.split("_");

              o1 = o1P[1] + o1P[0];
              o2 = o2P[1] + o2P[0];
              return o1.compareTo(o2);
            }
          }
      );
      quarters = Collections.unmodifiableList(list);
    }
    return quarters;
  }


  public List<FullProfitLoss> getFullProfitLoss() {
    return fullProfitLoss;
  }

  public void setFullProfitLoss(List<FullProfitLoss> fullProfitLoss) {
    this.fullProfitLoss = fullProfitLoss;
  }
}
