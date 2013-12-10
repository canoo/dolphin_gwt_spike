package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.PresentationModelJS;
import com.google.gwt.core.client.JsArray;

public interface OnFinishedHandler {
	public void handlePresentationModels(JsArray<PresentationModelJS> pms);
}
