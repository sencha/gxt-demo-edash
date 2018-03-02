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
package com.sencha.gxt.edash.client.view.impl.navigation;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.edash.client.place.CompanyNewsPlace;
import com.sencha.gxt.edash.client.place.KpiOverviewPlace;
import com.sencha.gxt.edash.client.place.PerformancePlace;
import com.sencha.gxt.edash.client.place.ProfitAndLossPlace;
import com.sencha.gxt.theme.base.client.listview.ListViewCustomAppearance;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class SideNavigation extends BaseNavigation implements PlaceChangeEvent.Handler {

  static class Navigation {
    private String title;
    private Place place;
    private String iconStyle;

    public Navigation(String title, Place place, String iconStyle) {
      this.title = title;
      this.place = place;
      this.iconStyle = iconStyle;
    }

    public String getTitle() {
      return title;
    }

    public String getIconStyle() {
      return iconStyle;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Navigation that = (Navigation) o;

      if (title != null ? !title.equals(that.title) : that.title != null) return false;

      return true;
    }

    @Override
    public int hashCode() {
      return title != null ? title.hashCode() : 0;
    }
  }

  interface Renderer extends XTemplates {
    @XTemplate(source = "SideNavigationItem.html")
    public SafeHtml renderItem(Navigation navigation, Style style);
  }


  @Inject
  private PlaceController placeController;

  private VerticalLayoutContainer container;
  private ListView<Navigation, Navigation> listView;

  @Inject
  public SideNavigation(EventBus eventBus) {
    eventBus.addHandler(PlaceChangeEvent.TYPE, this);
  }

  @Override
  public Widget asWidget() {
    if (container == null) {

      container = new VerticalLayoutContainer();

      container.add(getBanner(), new VerticalLayoutData(1, 100));

      container.addStyleName(resources.style().navigation());


      ModelKeyProvider<Navigation> keyProvider = new ModelKeyProvider<Navigation>() {
        @Override
        public String getKey(Navigation item) {
          return item.title;
        }
      };
      final ListStore<Navigation> store = new ListStore<Navigation>(keyProvider);

      store.add(new Navigation("Kpi Overview", new KpiOverviewPlace(), resources.style().kpiIcon()));
      store.add(new Navigation("Performance", new PerformancePlace(), resources.style().performanceIcon()));
      store.add(new Navigation("Profit & Loss", new ProfitAndLossPlace(), resources.style().profitLossIcon()));
      store.add(new Navigation("Company News", new CompanyNewsPlace(), resources.style().newsIcon()));

      final Renderer r = GWT.create(Renderer.class);

      ListViewCustomAppearance<Navigation> appearance = new ListViewCustomAppearance<Navigation>("." + resources.style().itemWrap(), resources.style().itemOver(), resources.style().itemSelected()) {
        @Override
        public void renderEnd(SafeHtmlBuilder builder) {
          String markup = new StringBuilder("<div class=\"").append(CommonStyles.get().clear()).append("\"></div>").toString();
          builder.appendHtmlConstant(markup);
        }

        @Override
        public void renderItem(SafeHtmlBuilder builder, SafeHtml content) {
          builder.appendHtmlConstant("<div class='" + SideNavigation.this.resources.style().itemWrap() + "'>");
          builder.append(content);
          builder.appendHtmlConstant("</div>");
        }
      };

      listView = new ListView<Navigation, Navigation>(store, new IdentityValueProvider<Navigation>(), appearance);
      listView.setBorders(false);
      listView.setCell(new SimpleSafeHtmlCell<Navigation>(new AbstractSafeHtmlRenderer<Navigation>() {

        @Override
        public SafeHtml render(Navigation object) {
          return r.renderItem(object, resources.style());
        }
      }));


      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      listView.getSelectionModel().addSelectionHandler(new SelectionHandler<Navigation>() {
        @Override
        public void onSelection(SelectionEvent<Navigation> event) {
          if (event.getSelectedItem().place.getClass() != placeController.getWhere().getClass()) {
            placeController.goTo(event.getSelectedItem().place);
          }
        }
      });
      container.add(listView, new VerticalLayoutData(1, -1));

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
        @Override
        public void execute() {
          for (Navigation n : store.getAll()) {
            if (n.place.getClass() == placeController.getWhere().getClass()) {
              listView.getSelectionModel().select(n, false);


              return;
            }
          }
        }
      });
    }

    return container;
  }


  @Override
  public void onPlaceChange(PlaceChangeEvent event) {
    if (listView == null || event.getNewPlace() == null) {
      return;
    }
    for (Navigation n : listView.getStore().getAll()) {
      if (n.place.getClass() == event.getNewPlace().getClass()) {
        listView.getSelectionModel().select(n, false);
      }
    }
  }
}
