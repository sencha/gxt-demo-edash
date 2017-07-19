package com.sencha.gxt.edash.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers({
    KpiOverviewPlace.Tokenizer.class,
    PerformancePlace.Tokenizer.class,
    ProfitAndLossPlace.Tokenizer.class,
    CompanyNewsPlace.Tokenizer.class
})
public interface EdashPlaceHistoryMapper extends PlaceHistoryMapper {
}
