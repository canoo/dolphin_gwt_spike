package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.*;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.List;

public class ClientDolphin {

    private final ClientDolphinJS clientDolphinJS;
    private final ClientAttributeJS clientAttributeModule;

    public ClientDolphin(ClientDolphinJS clientDolphinJS, final ClientAttributeJS clientAttributeModule) {
        this.clientDolphinJS = clientDolphinJS;
		this.clientAttributeModule = clientAttributeModule;
    }

    public void send(String commandName){
        sendJS(clientDolphinJS, commandName);
    }

    public void send(String commandName, final OnFinishedHandler handler){
        sendJS(clientDolphinJS, commandName, new OnFinishedHandlerJS() {
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

    public void presentationModel(String id, String type, String... clientAttributeIDs) {

        JsArray jsAttributes = ClientAttributeJS.createArray().cast();
        for (String attrId : clientAttributeIDs) {
            jsAttributes.push(ClientAttribute.newClientAttributeJS(clientAttributeModule, attrId));
        }

        presentationModelJS(clientDolphinJS, id, type, jsAttributes);
    }

    public ClientModelStore getClientModelStore() {
        return new ClientModelStore(getClientModelStoreJS(clientDolphinJS));
    }

    private native PresentationModelJS presentationModelJS(ClientDolphinJS clientDolphin, String pmId, String type, JsArray<ClientAttributeJS> javaScriptObjects) /*-{
        return clientDolphin.presentationModel( pmId, type, javaScriptObjects );
    }-*/;

    private native void sendJS(ClientDolphinJS clientDolphinJS, String commandName) /*-{
        clientDolphinJS.send(commandName);
    }-*/;

    private native void sendJS(ClientDolphinJS clientDolphin, String commandName, OnFinishedHandlerJS handler) /*-{
		clientDolphin.send(commandName, function (pms) {
            handler.@com.canoo.opendolphin.client.js.OnFinishedHandlerJS::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
        });
    }-*/;

    private native ClientModelStoreJS getClientModelStoreJS(ClientDolphinJS clientDolphin) /*-{
        return clientDolphin.getClientModelStore();
    }-*/;

}
