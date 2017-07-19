package com.sencha.gxt.edash.client.place;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.sencha.gxt.edash.client.activity.BaseActivity;
import com.sencha.gxt.edash.client.activity.CompanyNewsActivity;
import com.sencha.gxt.edash.client.activity.KpiOverviewActivity;
import com.sencha.gxt.edash.client.activity.PerformanceActivity;
import com.sencha.gxt.edash.client.activity.ProfitAndLossActivity;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 *
 */
public class EdashActivityMapper implements ActivityMapper {

  @Inject
  private Provider<KpiOverviewActivity> kpiOverviewActivityProvider;
  @Inject
  private Provider<PerformanceActivity> performanceActivityProvider;
  @Inject
  private Provider<ProfitAndLossActivity> profitAndLossActivityProvider;
  @Inject
  private Provider<CompanyNewsActivity> companyNewsActivityProvider;

  private BasePlace currentPlace;
  private BaseActivity currentActivity;

  @Override
  public Activity getActivity(Place place) {
    BaseActivity activity = null;

    if (currentActivity != null && currentPlace != null && place != null && currentPlace.getClass().equals(place.getClass())) {
      // we're not changing places. Place data may have been updated.
      // Update current activity with new place instance and return it
      currentPlace = (BasePlace) place;
      currentActivity.setPlace(currentPlace);
      return currentActivity;
    }

    if (place instanceof KpiOverviewPlace) {
      activity = kpiOverviewActivityProvider.get();
    }
    if (place instanceof PerformancePlace) {
      activity = performanceActivityProvider.get();
    }
    if (place instanceof ProfitAndLossPlace) {
      activity = profitAndLossActivityProvider.get();
    }
    if (place instanceof CompanyNewsPlace) {
      activity = companyNewsActivityProvider.get();
    }

    if (activity == null) {
      return null;
    }

    currentPlace = (BasePlace) place;
    currentActivity = activity;
    activity.setPlace(currentPlace);
    return activity;
  }
}
