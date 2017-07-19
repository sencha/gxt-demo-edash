package com.sencha.gxt.theme.edash.custom.client.base.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Point;
import com.sencha.gxt.theme.edash.client.base.button.Css3ButtonCellAppearance;
import com.sencha.gxt.widget.core.client.event.XEvent;


public class WhiteButtonCellAppearance<M> extends Css3ButtonCellAppearance<M> {

  interface Style extends Css3ButtonStyle {
  }

  interface Resources extends Css3ButtonResources {
    @Override
    @Source({"com/sencha/gxt/theme/edash/client/base/button/Css3ButtonCell.gss", "WhiteButton.gss"})
    Style style();

    @Override
    @Source("switch-icon.png")
    ImageResource split();
  }

  private final Resources resources;

  public WhiteButtonCellAppearance() {
    this(GWT.<Resources>create(Resources.class));
  }

  public WhiteButtonCellAppearance(Resources resources) {
    super(resources);
    this.resources = resources;
  }

  public boolean isClickOnArrow(XElement parent, NativeEvent e) {
    Point eventXY = e.<XEvent> cast().getXY();
    XElement buttonEl = getButtonElement(parent);
    return eventXY.getX() > buttonEl.getRegion().getRight() - resources.split().getWidth();
  }
}
