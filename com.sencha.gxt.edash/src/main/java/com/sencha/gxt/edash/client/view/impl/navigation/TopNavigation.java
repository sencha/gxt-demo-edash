package com.sencha.gxt.edash.client.view.impl.navigation;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.Anchor;
import com.sencha.gxt.core.client.Style.AnchorAlignment;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.edash.client.place.CompanyNewsPlace;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace;
import com.sencha.gxt.edash.client.place.PerformancePlace;
import com.sencha.gxt.edash.client.place.ProfitAndLossPlace;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import javax.inject.Inject;


public class TopNavigation extends BaseNavigation {

  @Inject
  private PlaceController placeController;

  private BorderLayoutContainer container;

  @Override
  public Widget asWidget() {
    if (container == null) {
      container = new BorderLayoutContainer();

      BorderLayoutData westData = new BorderLayoutData(232);

      container.setWestWidget(getBanner(), westData);
      //add(getBanner(), new HorizontalLayoutData(232, 1));

      final IconButton iconButton = new IconButton(resources.style().menuIcon());

      final Menu menu = new Menu();

      MenuItem kpiOverview = new MenuItem("KPI Overview");
      kpiOverview.setIcon(resources.navKpi());
      kpiOverview.addStyleName(resources.style().menuItem());
      MenuItem performance = new MenuItem("Performance");
      performance.setIcon(resources.navPerformance());
      performance.addStyleName(resources.style().menuItem());
      MenuItem profitAndLoss = new MenuItem("Profit & Loss");
      profitAndLoss.setIcon(resources.navProfit());
      profitAndLoss.addStyleName(resources.style().menuItem());
      MenuItem companyNews = new MenuItem("Company News");
      companyNews.setIcon(resources.navNews());
      companyNews.addStyleName(resources.style().menuItem());

      menu.add(kpiOverview);
      menu.add(performance);
      menu.add(profitAndLoss);
      menu.add(companyNews);

      kpiOverview.addSelectionHandler(new SelectionHandler<Item>() {
        @Override
        public void onSelection(SelectionEvent<Item> event) {
          placeController.goTo(new KpiOverviewPlace());
        }
      });
      performance.addSelectionHandler(new SelectionHandler<Item>() {
        @Override
        public void onSelection(SelectionEvent<Item> event) {
          placeController.goTo(new PerformancePlace());
        }
      });
      profitAndLoss.addSelectionHandler(new SelectionHandler<Item>() {
        @Override
        public void onSelection(SelectionEvent<Item> event) {
          placeController.goTo(new ProfitAndLossPlace());
        }
      });
      companyNews.addSelectionHandler(new SelectionHandler<Item>() {
        @Override
        public void onSelection(SelectionEvent<Item> event) {
          placeController.goTo(new CompanyNewsPlace());
        }
      });

      iconButton.addSelectHandler(new SelectHandler() {
        @Override
        public void onSelect(SelectEvent event) {
          XElement iconElement = iconButton.getElement();
          int offsetWidth = iconElement.getOffsetWidth();
          int offsetHeight = iconElement.getOffsetHeight();
          menu.show(iconElement, new AnchorAlignment(Anchor.TOP), 0, -(offsetHeight/2));
        }
      });

      BorderLayoutData eastData = new BorderLayoutData(120);
      container.setEastWidget(iconButton, eastData);

      container.addStyleName(resources.style().navigation());
    }
    return container;
  }
}
