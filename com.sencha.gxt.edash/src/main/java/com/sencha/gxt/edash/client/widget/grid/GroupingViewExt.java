package com.sencha.gxt.edash.client.widget.grid;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

public abstract class GroupingViewExt<M> extends GroupingView<M> {

  public GroupingViewExt() {
    super();

    this.scrollOffset = 0;
  }

  @Override
  public void toggleGroup(int index, boolean expanded) {
    super.toggleGroup(index, expanded);
  }

  @Override
  public NodeList<Element> getGroups() {
    return super.getGroups();
  }

  @Override
  public int getGroupIndex(XElement group) {
    return super.getGroupIndex(group);
  }


  protected void onRowOut(int rowIndex) {
    super.onRowOut(getRow(rowIndex));
  }

  public void onRowOutSync(GroupingViewExt<M> view, Element row) {
    super.onRowOut(row);
    int rowIndex = findRowIndex(row);
    view.onRowOut(rowIndex);
  }


  protected void onRowOver(int rowIndex) {
    super.onRowOver(getRow(rowIndex));
  }

  public void onRowOverSync(GroupingViewExt<M> view, Element row) {
    super.onRowOver(row);
    int rowIndex = findRowIndex(row);
    view.onRowOver(rowIndex);
  }
}
