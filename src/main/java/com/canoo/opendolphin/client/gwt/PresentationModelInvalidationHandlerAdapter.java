package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientPresentationModelJS;
import com.canoo.opendolphin.client.js.PresentationModelInvalidationHandlerJS;

public class PresentationModelInvalidationHandlerAdapter implements PresentationModelInvalidationHandlerJS {
	private final PresentationModelInvalidationHandler handler;

	public PresentationModelInvalidationHandlerAdapter(PresentationModelInvalidationHandler handler) {
		this.handler = handler;
	}

	@Override
	public void handleChange(ClientPresentationModelJS pmJS) {
		handler.handleChange(new ClientPresentationModel(pmJS));
	}
}
