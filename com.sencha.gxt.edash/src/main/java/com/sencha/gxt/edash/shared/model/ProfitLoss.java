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
