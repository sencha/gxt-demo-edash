package com.sencha.gxt.edash.shared.model;


import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

public interface PerformancePropertyAccess extends PropertyAccess<Performance> {

  @Path("id")
  ModelKeyProvider<Performance> key();


  ValueProvider<Performance, Date> date();

  ValueProvider<Performance, Integer> open();

  ValueProvider<Performance, Integer> close();

  ValueProvider<Performance, Integer> low();

  ValueProvider<Performance, Integer> high();

  ValueProvider<Performance, Integer> lowHighDiff();

  ValueProvider<Performance, String> company();
}
