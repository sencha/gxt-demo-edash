package com.sencha.gxt.edash.client.view;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.edash.shared.model.ProfitLoss;

public interface ProfitAndLossView extends View {


  interface Delegate {
    void getData(AsyncCallback<ProfitLoss> callback);
  }

  void setDelegate(Delegate delegate);

  void setData(ProfitLoss data);
}
