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

		for (String propertyName : clientAttributePropertyNames) {
			JSLogger.log("ClientDolphin.presentationModel: creating attribute for: " + propertyName);
			this.attribute(propertyName, null, null, null);
			JSLogger.log("ClientDolphin.presentationModel: creating attribute done.");
		}

/*
        JsArray jsAttributes = ClientAttributeJS.createArray().cast();
        for (String propertyName : clientAttributePropertyNames) {
            jsAttributes.push(ClientAttributeJS.newClientAttributeJS(clientAttributeModule, propertyName));
        }

		PresentationModelJS presentationModelJS = PresentationModelJS.newPresentationModelJS(clientDolphinJS, id, type, jsAttributes);
		return new PresentationModel(presentationModelJS);
*/
		return null;
    }

    public ClientModelStore getClientModelStore() {
        return new ClientModelStore(clientDolphinJS.getClientModelStoreJS());
    }


}
