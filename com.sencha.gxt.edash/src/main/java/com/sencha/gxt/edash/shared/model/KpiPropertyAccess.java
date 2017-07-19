package com.sencha.gxt.edash.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

/**
 *
 */
public interface KpiPropertyAccess extends PropertyAccess<Kpi> {

  @Path("id")
  ModelKeyProvider<Kpi> key();

  ValueProvider<Kpi, Double> data1();

  ValueProvider<Kpi, Double> data2();

  ValueProvider<Kpi, Date> date();
}
