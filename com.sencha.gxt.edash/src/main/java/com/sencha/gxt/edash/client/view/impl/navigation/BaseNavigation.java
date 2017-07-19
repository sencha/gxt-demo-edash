package com.sencha.gxt.edash.client.view.impl.navigation;


import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;

public abstract class BaseNavigation implements IsWidget {

  interface Style extends CssResource {
    String banner();

    String bannerInner();

    String logo();

    String bannerText();

    String menuItem();

    String menuIcon();

    String navigation();

    String itemWrap();

    String itemOver();

    String itemSelected();

    String tabWrap();
    String tabButtonNavigation();
    String icon();
    String text();
    String kpiIcon();
    String performanceIcon();
    String profitLossIcon();
    String newsIcon();
  }

  interface Resource extends ClientBundle {
    @Source("base.gss")
    Style style();

    @Source("mybiz.png")
    @ImageOptions(width = 32)
    ImageResource logo();

    @Source("menu-disclosure.png")
    @ImageOptions(repeatStyle = RepeatStyle.None, width = 32)
    ImageResource disclosure();

    @Source("kpi.png")
    ImageResource navKpi();

    @Source("quarterly.png")
    ImageResource navPerformance();

    @Source("pl.png")
    ImageResource navProfit();

    @Source("news.png")
    ImageResource navNews();
  }

  interface Templates extends XTemplates {
    @XTemplate("<div class='{style.bannerInner}'><div class='{style.logo}'></div><div class='{style.bannerText}'>MyBiz</div></div>")
    SafeHtml banner(Style style);

    @XTemplate("<div class='{style.menuIcon}'><div>")
    SafeHtml mrLiney(Style style);
  }


  protected Resource resources;
  protected Templates templates = GWT.create(Templates.class);

  public BaseNavigation() {
    resources = GWT.create(Resource.class);
    StyleInjectorHelper.ensureInjected(resources.style(), true);
  }


  protected Widget getBanner() {
    HTML html = new HTML(templates.banner(resources.style()));
    html.addStyleName(resources.style().banner());
    return html;
  }
}
