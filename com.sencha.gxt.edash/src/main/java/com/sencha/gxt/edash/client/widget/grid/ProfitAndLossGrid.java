package com.sencha.gxt.edash.client.widget.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

import java.util.List;

public class ProfitAndLossGrid<M> extends Grid<M> {

  interface Style extends CssResource {
    String grid();

    // won't build if these aren't present
    String leftGrid();
    String rightGrid();
  }

  interface Resources extends ClientBundle {
    @Source("grid.gss")
    Style style();
  }

  private final Resources resources = GWT.create(Resources.class);

  public ProfitAndLossGrid(ListStore<M> listStore, ColumnModel<M> columnModel, GroupingView<M> groupingView) {
    super(listStore, columnModel, groupingView);
    StyleInjectorHelper.ensureInjected(resources.style(), true);
    addStyleName(resources.style().grid());
  }

  @Override
  protected void onResize(int width, int height) {
    super.onResize(width, height);
    ColumnModel<M> columnModel = getColumnModel();
    // we need to resize the region column
    if (columnModel != null) {
      List<ColumnConfig<M, ?>> columns = columnModel.getColumns();
      for (ColumnConfig<M, ?> column : columns) {
        if ("region".equals(column.getPath().toLowerCase())) {
          column.setWidth(300);
        }
      }
    }
  }
}
