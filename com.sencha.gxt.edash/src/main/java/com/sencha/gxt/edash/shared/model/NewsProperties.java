package com.sencha.gxt.edash.shared.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface NewsProperties extends PropertyAccess<News> {

  @Path("id")
  ModelKeyProvider<News> key();
}
