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
package com.sencha.gxt.edash.client.widget.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.sencha.gxt.core.client.Style.ScrollDirection;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.gestures.ScrollGestureRecognizer;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.event.BodyScrollEvent;
import com.sencha.gxt.widget.core.client.event.BodyScrollEvent.BodyScrollHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

public class FrozenColumnGrid<M> extends Composite {
  private static final Logger logger = Logger.getLogger(FrozenColumnGrid.class.getName());

  private static class InternalGridSelectionModel<R> extends GridSelectionModel<R> {

    public InternalGridSelectionModel() {
      setSelectionMode(SelectionMode.SINGLE);
    }

    public void selectWithoutEvent(R item) {
      super.doSelect(Collections.singletonList(item), false, true);
    }
  }

  interface Style extends CssResource {
    String grid();

    String leftGrid();

    String rightGrid();
  }

  interface Resources extends ClientBundle {
    @Source("grid.gss")
    Style style();
  }

  private final Resources resources = GWT.create(Resources.class);
  private GroupingViewExt<M> groupingViewLeft;
  private GroupingViewExt<M> groupingViewRight;

  private Grid<M> gridLeft;
  private Grid<M> gridRight;

  private ColumnConfig<M, ?> leftGroupingColumn;
  private ColumnConfig<M, ?> rightGroupingColumn;

  public FrozenColumnGrid(ListStore<M> store, ColumnModel<M> lockedColumns, ColumnModel<M> columnModel) {
    StyleInjectorHelper.ensureInjected(resources.style(), true);
    init(store, lockedColumns, columnModel);
  }

  private void init(ListStore<M> store, ColumnModel<M> lockedColumns, ColumnModel<M> columnModel) {
    for (ColumnConfig<M, ?> config : lockedColumns.getColumns()) {
      config.setMenuDisabled(true);
    }

    for (ColumnConfig<M, ?> config : columnModel.getColumns()) {
      config.setMenuDisabled(true);
      config.setSortable(false);
    }

    groupingViewLeft = new GroupingViewExt<M>() {
      @Override
      public void toggleGroup(Element group, boolean expanded) {
        super.toggleGroup(group, expanded);

        int index = groupingViewRight.getGroupIndex(XElement.as(group));
        Element groupRight = groupingViewRight.getGroups().getItem(index);
        if (isExpanded(groupRight) != expanded) {
          groupingViewRight.toggleGroup(index, expanded);
        }
      }

      @Override
      public void onRowOut(Element row) {
        onRowOutSync(groupingViewRight, row);
      }

      @Override
      public void onRowOver(Element row) {
        onRowOverSync(groupingViewRight, row);
      }
    };
    groupingViewRight = new GroupingViewExt<M>() {
      @Override
      protected SafeHtml renderGroupHeader(
          com.sencha.gxt.widget.core.client.grid.GroupingView.GroupingData<M> groupInfo) {
        return SafeHtmlUtils.fromSafeConstant("&nbsp;");
      }

      @Override
      public void toggleGroup(Element group, boolean expand) {
        super.toggleGroup(group, expand);

        int index = groupingViewLeft.getGroupIndex(XElement.as(group));
        Element groupLeft = groupingViewLeft.getGroups().getItem(index);
        if (isExpanded(groupLeft) != expand) {
          groupingViewLeft.toggleGroup(index, expand);
        }
      }

      @Override
      public void onRowOut(Element row) {
        onRowOutSync(groupingViewLeft, row);
      }

      @Override
      public void onRowOver(Element row) {
        onRowOverSync(groupingViewLeft, row);
      }
    };

    gridLeft = new Grid<M>(store, lockedColumns, groupingViewLeft);
    gridLeft.setSelectionModel(new InternalGridSelectionModel<M>());
    gridLeft.addStyleName(resources.style().grid());
    gridLeft.addStyleName(resources.style().leftGrid());
    gridLeft.getView().setForceFit(true);
    gridLeft.setBorders(false);
    // gridLeft.getView().setColumnLines(true);
    gridLeft.setColumnResize(false);

    gridRight = new Grid<M>(store, columnModel, groupingViewRight);
    gridRight.setSelectionModel(new InternalGridSelectionModel<M>());
    gridRight.addStyleName(resources.style().grid());
    gridRight.addStyleName(resources.style().rightGrid());

    // Link scrolling
    gridLeft.addBodyScrollHandler(new BodyScrollHandler() {
      @Override
      public void onBodyScroll(final BodyScrollEvent event) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            int top = gridLeft.getView().getScroller().getScrollTop();
            gridRight.getView().getScroller().scrollTo(ScrollDirection.TOP, top);
          }
        });
      }
    });
    // Link scrolling
    gridRight.addBodyScrollHandler(new BodyScrollHandler() {
      @Override
      public void onBodyScroll(final BodyScrollEvent event) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            int top = gridRight.getView().getScroller().getScrollTop();
            gridLeft.getView().getScroller().scrollTo(ScrollDirection.TOP, top);
          }
        });
      }
    });

    gridLeft.getSelectionModel().addSelectionHandler(new SelectionHandler<M>() {
      @Override
      public void onSelection(SelectionEvent<M> event) {
        ((InternalGridSelectionModel) gridRight.getSelectionModel()).selectWithoutEvent(event.getSelectedItem());
      }
    });

    gridRight.getSelectionModel().addSelectionHandler(new SelectionHandler<M>() {
      @Override
      public void onSelection(SelectionEvent<M> event) {
        ((InternalGridSelectionModel) gridLeft.getSelectionModel()).selectWithoutEvent(event.getSelectedItem());
      }
    });

    gridRight.setColumnReordering(true);

    HorizontalLayoutData lockedColumnLayoutData = new HorizontalLayoutData(300, 1);

    HorizontalLayoutContainer gridWrapper = new HorizontalLayoutContainer();
    gridWrapper.add(gridLeft, lockedColumnLayoutData);
    gridWrapper.add(gridRight, new HorizontalLayoutData(1, 1));

    initWidget(gridWrapper);
  }

  public void setGroupingColumn(ColumnConfig<M, ?> groupingColumn) {
    this.leftGroupingColumn = groupingColumn;

    ValueProvider<M, Object> valueProvider = (ValueProvider<M, Object>) groupingColumn.getValueProvider();
    this.rightGroupingColumn = new ColumnConfig<M, Object>(valueProvider, groupingColumn.getWidth(),
        groupingColumn.getHeader());

    addGroupingColumn(this.leftGroupingColumn, gridLeft);
    addGroupingColumn(this.rightGroupingColumn, gridRight);
  }

  private void addGroupingColumn(ColumnConfig groupingColumn, Grid<M> grid) {
    List<ColumnConfig<M, ?>> columns = new ArrayList<ColumnConfig<M, ?>>(grid.getColumnModel().getColumns());
    columns.add(0, groupingColumn);
    grid.reconfigure(grid.getStore(), new ColumnModel<M>(columns));

    groupingColumn.setHidden(true);
  }

  public void enableGrouping() {
    ((GroupingView) gridLeft.getView()).groupBy(leftGroupingColumn);
    ((GroupingView) gridRight.getView()).groupBy(rightGroupingColumn);
  }

  public void setColumnVisible(String columnName, boolean visible) {
    int index = 0;
    for (ColumnConfig<M, ?> mColumnConfig : gridRight.getColumnModel().getColumns()) {
      if (columnName.equals(mColumnConfig.getPath())) {
        gridRight.getColumnModel().setHidden(index, !visible);
        return;
      }
      index++;
    }
    logger.warning("Unable to find column:" + columnName);

  }
}
