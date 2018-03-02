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
package com.sencha.gxt.edash.client.view.impl;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.gestures.TapGestureRecognizer;
import com.sencha.gxt.core.client.gestures.TouchData;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.edash.client.place.CompanyNewsPlace;
import com.sencha.gxt.edash.client.view.CompanyNewsView;
import com.sencha.gxt.edash.shared.model.News;
import com.sencha.gxt.edash.shared.model.NewsProperties;
import com.sencha.gxt.theme.base.client.listview.ListViewCustomAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.button.BlueButtonCellAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.BlueCheckMenuItemAppearance;
import com.sencha.gxt.theme.edash.custom.client.base.menu.EdashBlueMenuAppearance;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CheckChangeEvent;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class CompanyNewsViewImpl implements CompanyNewsView {

  @XTemplates.FormatterFactories(@XTemplates.FormatterFactory(factory = ShortenFactory.class, name = "shorten"))
  interface Renderer extends XTemplates {
    @XTemplate(source = "companyNews.html")
    public SafeHtml renderItem(News news, Style style);
  }

  public static class Shorten implements XTemplates.Formatter<String> {
    private int length;

    public Shorten(int length) {
      this.length = length;
    }

    @Override
    public String format(String data) {
      return Format.ellipse(data, length);
    }
  }

  public static class ShortenFactory {
    public static Shorten getFormat(int length) {
      return new Shorten(length);
    }
  }

  interface Resources extends ClientBundle {
    @Source("companyNews.gss")
    Style style();

    @Source("news-icon.png")
    @ImageResource.ImageOptions(width = 35)
    ImageResource newsIcon();

    @Source("forum-icon.png")
    @ImageResource.ImageOptions(width = 35)
    ImageResource forumIcon();

    @Source("cal-icon-small.png")
    ImageResource calenderIcon();

    @Source("clock-icon-small.png")
    ImageResource clockIcon();

    @Source("expand-news-small.png")
    ImageResource expandIcon();

    @Source("collapse-news-small.png")
    ImageResource collapseIcon();

    @Source("icon-line-bg.png")
    ImageResource lineBackground();
  }

  interface Style extends CssResource {
    String view();

    String cell();

    String textWrapper();

    String icon();

    String news();

    String forum();

    String newsAuthor();

    String newsToggle();

    String newsToggleCollapse();

    String newsData();

    String newsParagraph();

    String newsParagraphFull();

    String newsPicture();

    String newsContent();

    String newsTitle();

    String newsSmall();

    String calenderIcon();

    String clockIcon();

    String expandIcon();

    String collapseIcon();

    String expanded();

    String expander();

    String collapser();
  }

  @Inject
  private PlaceController placeController;

  private Delegate delegate;
  private BorderLayoutContainer container;

  private NewsProperties props = GWT.create(NewsProperties.class);
  private ListStore<News> store = new ListStore<News>(props.key());
  private Resources resources = GWT.create(Resources.class);
  private Style style = resources.style();
  private Renderer renderer = GWT.create(Renderer.class);

  private CheckMenuItem all, news, forums;

  private Store.StoreFilter filter = new Store.StoreFilter<News>() {

    @Override
    public boolean select(Store<News> store, News parent, News item) {
      return item.getType().equals(postsButton.getText().toLowerCase());
    }
  };

  private CheckChangeEvent.CheckChangeHandler checkHandler = new CheckChangeEvent.CheckChangeHandler<CheckMenuItem>() {
    @Override
    public void onCheckChange(CheckChangeEvent<CheckMenuItem> event) {
      String text = event.getItem().getText();
      if (text.equals("All Posts")) {
        placeController.goTo(new CompanyNewsPlace(CompanyNewsPlace.NewsFilter.ALL_POSTS));
      } else if (text.equals("News")) {
        placeController.goTo(new CompanyNewsPlace(CompanyNewsPlace.NewsFilter.NEWS));
      } else {
        placeController.goTo(new CompanyNewsPlace(CompanyNewsPlace.NewsFilter.FORUM));
      }
    }
  };
  private TextButton postsButton;

  public CompanyNewsViewImpl() {
    StyleInjectorHelper.ensureInjected(resources.style(), true);

    container = new BorderLayoutContainer();

    ContentPanel north = new ContentPanel();
    north.setBodyStyle("backgroundColor: #ececec");
    north.setHeaderVisible(false);
    VerticalLayoutContainer con = new VerticalLayoutContainer();
    con.add(createToolbar(), new VerticalLayoutContainer.VerticalLayoutData(1, -1, new Margins(15)));

    north.add(con);
    BorderLayoutData northLayout = new BorderLayoutData(75);
    container.setNorthWidget(north, northLayout);

    ContentPanel center = new ContentPanel();
    center.setHeaderVisible(false);

    ListViewNewsAppearance appearance = new ListViewNewsAppearance();

    final ListView<News, News> listView = new ListView<News, News>(store, new IdentityValueProvider<News>(),
        appearance);
    listView.addStyleName(style.view());
    listView.setBorders(false);
    listView.setCell(new SimpleSafeHtmlCell<News>(new AbstractSafeHtmlRenderer<News>() {
      @Override
      public SafeHtml render(News object) {
        return renderer.renderItem(object, resources.style());
      }
    }));

    listView.addGestureRecognizer(new TapGestureRecognizer() {
      @Override
      protected void onEnd(List<TouchData> touches) {
        super.onEnd(touches);
        handleExpandCollapse(touches.get(0).getLastNativeEvent());
      }
    });
    listView.addHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
        handleExpandCollapse(clickEvent.getNativeEvent());
      }
    }, ClickEvent.getType());

    center.add(listView);

    container.setCenterWidget(center);
  }

  @Override
  public void setData(List<News> result) {
    store.clear();
    if (result != null) {
      store.addAll(result);
    }
  }

  @Override
  public void setFilter(CompanyNewsPlace.NewsFilter newsFilter) {
    switch (newsFilter) {
    case ALL_POSTS:
      postsButton.setText("All Posts");
      all.setChecked(true, true);
      news.setChecked(false, true);
      forums.setChecked(false, true);
      store.removeFilters();
      break;
    case NEWS:
      postsButton.setText("News");
      all.setChecked(false, true);
      news.setChecked(true, true);
      forums.setChecked(false, true);
      store.addFilter(filter);
      store.setEnableFilters(true);
      break;
    case FORUM:
      postsButton.setText("Forum");
      all.setChecked(false, true);
      news.setChecked(false, true);
      forums.setChecked(true, true);
      store.addFilter(filter);
      store.setEnableFilters(true);
      break;
    }
  }

  @Override
  public void setDelegate(Delegate delegate) {
    this.delegate = delegate;
  }

  @Override
  public Widget asWidget() {
    return container;
  }

  private void handleExpandCollapse(NativeEvent event) {
    XElement target = event.getEventTarget().cast();
    if (target != null) {
      if (target.hasClassName(style.expander()) || target.hasClassName(style.expandIcon())) {
        target.findParent("." + resources.style().cell(), 10).addClassName(resources.style().expanded());
      } else if (target.hasClassName(style.collapser()) || target.hasClassName(style.collapseIcon())) {
        target.findParent("." + resources.style().cell(), 10).removeClassName(resources.style().expanded());
      }
    }
  }

  private final class ListViewNewsAppearance extends ListViewCustomAppearance<News> {

    public ListViewNewsAppearance() {
      super("." + CompanyNewsViewImpl.this.resources.style().cell());
    }

    @Override
    public void renderItem(SafeHtmlBuilder builder, SafeHtml content) {
      builder.appendHtmlConstant("<div class='" + CompanyNewsViewImpl.this.resources.style().cell() + "'>");
      builder.append(content);
      builder.appendHtmlConstant("</div>");
    }

    @Override
    public void renderEnd(SafeHtmlBuilder builder) {
      String markup = new StringBuilder("<div class=\"").append(CommonStyles.get().clear()).append("\"></div>")
          .toString();
      builder.appendHtmlConstant(markup);
    }
  }

  private ToolBar createToolbar() {
    ToolBar toolBar = new ToolBar();

    postsButton = new TextButton(new TextButtonCell(new BlueButtonCellAppearance<String>()), "All Posts");
    postsButton.setWidth(150);

    Menu postsMenu = new Menu(new EdashBlueMenuAppearance());

    all = new CheckMenuItem(new BlueCheckMenuItemAppearance());
    all.setText("All Posts");
    all.setChecked(true, false);
    all.addCheckChangeHandler(checkHandler);

    news = new CheckMenuItem(new BlueCheckMenuItemAppearance());
    news.setText("News");
    news.setChecked(false, false);
    news.addCheckChangeHandler(checkHandler);

    forums = new CheckMenuItem(new BlueCheckMenuItemAppearance());
    forums.setText("Forum");
    forums.setChecked(false, false);
    forums.addCheckChangeHandler(checkHandler);

    postsMenu.add(all);
    postsMenu.add(news);
    postsMenu.add(forums);
    postsButton.setMenu(postsMenu);

    toolBar.add(postsButton);

    return toolBar;
  }
}
