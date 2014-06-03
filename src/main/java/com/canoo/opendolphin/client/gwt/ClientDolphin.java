package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.*;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.List;

public class ClientDolphin {

    private final ClientDolphinJS clientDolphinJS;

    public ClientDolphin(ClientDolphinJS clientDolphinJSModule) {
		clientDolphinJS = clientDolphinJSModule.newInstance();
    }

    public void send(String commandName){
		clientDolphinJS.send(commandName);
    }

    public void send(String commandName, final OnFinishedHandler handler){
		clientDolphinJS.send(commandName, new OnFinishedHandlerJS() {
			@Override
			public void handlePresentationModels(final JsArray<PresentationModelJS> jsPMs) {
				List<PresentationModel> pms = new ArrayList<PresentationModel>(jsPMs.length());
				for (int i = 0; i < jsPMs.length(); i++) {
					pms.add(new PresentationModel(jsPMs.get(i)));
				}

				handler.handlePresentationModels(pms);
			}
		});
    }

    public ClientAttribute attribute(String propertyName, String qualifier, String value, String tag) {
		ClientAttributeJS clientAttributeJS = clientDolphinJS.attribute(clientDolphinJS, propertyName, qualifier, value, tag);
		return new ClientAttribute(clientAttributeJS);
	}

    public PresentationModel presentationModel(String id, String type, String... clientAttributePropertyNames) {

		List<ClientAttribute> clientAttributes = new ArrayList<ClientAttribute>();
		for (String propertyName : clientAttributePropertyNames) {
			ClientAttribute clientAttribute = this.attribute(propertyName, null, null, null);
			clientAttributes.add(clientAttribute);
		}

        JsArray jsAttributes = ClientAttributeJS.createArray().cast();
		for (ClientAttribute clientAttribute : clientAttributes) {
            jsAttributes.push(clientAttribute.getClientAttributeJS());
		}

		PresentationModelJS presentationModelJS = clientDolphinJS.presentationModel(id, type, jsAttributes);
		return new PresentationModel(presentationModelJS);
    }

    public ClientModelStore getClientModelStore() {
        return new ClientModelStore(clientDolphinJS.getClientModelStoreJS());
    }


}
