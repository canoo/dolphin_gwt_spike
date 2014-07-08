package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.*;

public class ClientModelStore {

    private final ClientModelStoreJS clientModelStoreJS;

    public ClientModelStore(ClientModelStoreJS clientModelStoreJS) {
        this.clientModelStoreJS = clientModelStoreJS;
    }

    public ClientAttribute findAttributeById(String id) {
		ClientAttributeJS clientAttributeJS = clientModelStoreJS.findAttributeById(id);
		return clientAttributeJS == null ? null : new ClientAttribute(clientAttributeJS);
    }


	public void addModelStoreListener(ModelStoreChangeHandler handler) {
		clientModelStoreJS.addModelStoreListener(new ModelStoreChangeHandlerAdapter(handler));
	}

// === private stuff ===

	private static class ModelStoreChangeHandlerAdapter implements ModelStoreChangeHandlerJS {
		private final ModelStoreChangeHandler handler;

		public ModelStoreChangeHandlerAdapter(ModelStoreChangeHandler handler) {
			this.handler = handler;
		}

		@Override
		public void handleChange(String changeType, ClientPresentationModelJS pmJS) {
			ModelStoreChangeEventType ct = ModelStoreChangeEventType.fromTypeValue(changeType);
			handler.handleChange(ct, new ClientPresentationModel(pmJS));
		}
	}
}
