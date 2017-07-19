package com.sencha.gxt.edash.client.place;


import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ProfitAndLossPlace extends BasePlace {


  @Prefix("profitloss")
  public static class Tokenizer implements PlaceTokenizer<ProfitAndLossPlace> {

    @Override
    public ProfitAndLossPlace getPlace(String token) {
      return new ProfitAndLossPlace();
    }

    @Override
    public String getToken(ProfitAndLossPlace place) {
      return "";
    }
  }
}
