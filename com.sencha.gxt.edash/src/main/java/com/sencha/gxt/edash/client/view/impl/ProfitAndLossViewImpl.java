package com.sencha.gxt.edash.client.view.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.edash.client.LoggingAsyncCallback;
import com.sencha.gxt.edash.client.view.ProfitAndLossView;
import com.sencha.gxt.edash.client.widget.grid.ProfitAndLossGrid;
import com.sencha.gxt.edash.shared.model.FullProfitLoss;
import com.sencha.gxt.edash.shared.model.FullProfitLossPropertyAccess;
import com.sencha.gxt.edash.shared.model.ProfitLoss;
import com.sencha.gxt.theme.edash.custom.client.base.button.BlueButtonCellAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.BlueCheckMenuItemAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.EdashBlueMenuAppearance;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent.CheckChangeHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Based off of http://dev.sencha.com/ext/5.0.1/examples/executive-dashboard/index.html#!profitloss
 */
@Singleton
public class ProfitAndLossViewImpl implements ProfitAndLossView {

  private static final String[] COLUMN_NAMES = {
      "q1_2010",
      "q2_2010",
      "q3_2010",
      "q4_2010",
      "q1_2011",
      "q2_2011",
      "q3_2011",
      "q4_2011",
      "q2_2012",
      "q1_2012"
  };

  private FullProfitLossPropertyAccess fullProfitLossPropertyAccess = GWT.create(FullProfitLossPropertyAccess.class);
  private Delegate delegate;
  private SimpleContainer widget;

  private Grid<FullProfitLoss> grid;
  private ColumnConfig<FullProfitLoss, String> groupingColumn;
  private GroupingView<FullProfitLoss> groupingView;

  private ListStore<FullProfitLoss> profitLossStore;

  @Override
  public void setDelegate(Delegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public Widget asWidget() {
    if (widget == null) {
      widget = new SimpleContainer();
      VerticalLayoutContainer container = new VerticalLayoutContainer();
      container.add(createToolbar(), new VerticalLayoutData(1, -1, new Margins(0, 0, 15, 0)));
      container.add(createGrid(), new VerticalLayoutData(1, 1));
      widget.add(container, new MarginData(20));


      delegate.getData(new LoggingAsyncCallback<ProfitLoss>() {
        @Override
        public void onSuccess(ProfitLoss result) {
          setData(result);
        }
      });
    }

    return widget;
  }

  @Override
  public void setData(final ProfitLoss data) {
    profitLossStore.clear();
    profitLossStore.addAll(data.getFullProfitLoss());
    groupingView.groupBy(groupingColumn);
  }


  private Widget createGrid() {
    profitLossStore = new ListStore<FullProfitLoss>(fullProfitLossPropertyAccess.key());
    ColumnConfig<FullProfitLoss, String> account =
            new ColumnConfig<FullProfitLoss, String>(fullProfitLossPropertyAccess.account(), 300, "Account");
    groupingColumn = account;
    ColumnConfig<FullProfitLoss, String> region =
            new ColumnConfig<FullProfitLoss, String>(fullProfitLossPropertyAccess.region(), 100, "Region");
    region.setSortable(false);

    groupingView = new GroupingView<FullProfitLoss>();
    groupingView.setShowGroupedColumn(false);

    List<ColumnConfig<FullProfitLoss, ?>> columns = new ArrayList<ColumnConfig<FullProfitLoss, ?>>();
    columns.add(account);
    columns.add(region);


    final NumberFormat numberFormat = NumberFormat.getSimpleCurrencyFormat("USD");
    for (final String columnName : COLUMN_NAMES) {
      final String columnHeader = columnName.replaceAll("_", " ");
      ColumnConfig<FullProfitLoss, FullProfitLoss> column =
              new ColumnConfig<FullProfitLoss, FullProfitLoss>(new IdentityValueProvider<FullProfitLoss>(columnName), 100, columnHeader);
      column.setCell(new SimpleSafeHtmlCell<FullProfitLoss>(new AbstractSafeHtmlRenderer<FullProfitLoss>() {
        @Override
        public SafeHtml render(FullProfitLoss object) {
          return SafeHtmlUtils.fromSafeConstant(numberFormat.format(object.getColumnData(columnName)));
        }
      }));
      column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
      columns.add(column);
    }

    ColumnModel<FullProfitLoss> columnModel = new ColumnModel<FullProfitLoss>(columns);
    // menuDisabled and sortable setters don't do anything until added to the columnModel
    for (ColumnConfig<FullProfitLoss, ?> config : columnModel.getColumns()) {
      config.setMenuDisabled(true);
      config.setSortable(false);
    }

    grid = new ProfitAndLossGrid<FullProfitLoss>(profitLossStore, columnModel, groupingView);
    return grid;
  }


  private ToolBar createToolbar() {
    ToolBar toolBar = new ToolBar();
    //toolBar.addStyleName(resources.style().toolbar());

    TextButton quarter = new TextButton(new TextButtonCell(new BlueButtonCellAppearance<String>()), "QUARTER");
    quarter.setWidth(150);
    Menu quarterMenu = new Menu(new EdashBlueMenuAppearance());

    for (final String columnName : COLUMN_NAMES) {
      String[] parts = columnName.split("_");
      CheckMenuItem checkMenuItem = new CheckMenuItem(new BlueCheckMenuItemAppearance());
      checkMenuItem.setText("Quarter " + parts[0].substring(1) + ", " + parts[1]);
      checkMenuItem.setChecked(true);

      checkMenuItem.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {
        @Override
        public void onCheckChange(CheckChangeEvent<CheckMenuItem> event) {
          setColumnVisible(columnName, CheckState.CHECKED == event.getChecked());
        }
      });
      quarterMenu.add(checkMenuItem);
    }
    quarter.setMenu(quarterMenu);


    TextButton region = new TextButton(new TextButtonCell(new BlueButtonCellAppearance<String>()), "REGION");
    region.setWidth(150);

    final Menu regionMenu = new Menu(new EdashBlueMenuAppearance());
    region.setMenu(regionMenu);

    regionMenu.addBeforeShowHandler(new BeforeShowHandler() {
      @Override
      public void onBeforeShow(BeforeShowEvent event) {
        if (regionMenu.getWidgetCount() == 0) {

          Set<String> regionSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
          for (FullProfitLoss fullProfitLoss : profitLossStore.getAll()) {
            regionSet.add(fullProfitLoss.getRegion());
          }

          for (final String regionName : regionSet) {
            CheckMenuItem checkMenuItem = new CheckMenuItem(new BlueCheckMenuItemAppearance());
            checkMenuItem.setText(regionName);
            checkMenuItem.setChecked(true);
            final StoreFilter<FullProfitLoss> filter = new StoreFilter<FullProfitLoss>() {
              @Override
              public boolean select(Store<FullProfitLoss> store, FullProfitLoss parent, FullProfitLoss item) {
                return !regionName.equals(item.getRegion());
              }
            };

            checkMenuItem.addCheckChangeHandler(new CheckChangeHandler<CheckMenuItem>() {
              @Override
              public void onCheckChange(CheckChangeEvent<CheckMenuItem> event) {
                if (CheckState.CHECKED == event.getChecked()) {
                  profitLossStore.removeFilter(filter);
                } else {
                  profitLossStore.addFilter(filter);
                }
                profitLossStore.setEnableFilters(true);
              }
            });
            regionMenu.add(checkMenuItem);
          }
        }
      }
    });

    toolBar.add(quarter);
    toolBar.add(region);

    return toolBar;
  }

  private void setColumnVisible(String columnName, boolean visible) {
    int index = 0;
    for (ColumnConfig<FullProfitLoss, ?> mColumnConfig : grid.getColumnModel().getColumns()) {
      if (columnName.equals(mColumnConfig.getPath())) {
        grid.getColumnModel().setHidden(index, !visible);
        return;
      }
      index++;
    }
  }
}
