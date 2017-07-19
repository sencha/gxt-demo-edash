package com.sencha.gxt.edash.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
*
*/
public interface DataPropertyAccess extends PropertyAccess<Data> {
  ValueProvider<Data, Double> data1();

  ValueProvider<Data, String> name();

  @Path("id")
  ModelKeyProvider<Data> nameKey();
}
