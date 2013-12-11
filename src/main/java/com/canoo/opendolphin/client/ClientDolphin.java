package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JsArray;

public class ClientDolphin {

    private final ClientDolphinJS clientDolphinJS;

    public ClientDolphin(ClientDolphinJS clientDolphinJS) {
        this.clientDolphinJS = clientDolphinJS;
    }

    public void send(String commandName){
        sendJS(clientDolphinJS, commandName);
    }

    public void send(String commandName, OnFinishedHandler handler){
        sendJS(clientDolphinJS, commandName, handler);
    }

    public void presentationModel(String id, String type, ClientAttribute... clientAttributes) {

        JsArray jsAttributes = ClientAttributeJS.createArray().cast();
        for (ClientAttribute attribute : clientAttributes) {
            jsAttributes.push(attribute.getAttribute());
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

    private native void sendJS(ClientDolphinJS clientDolphin, String commandName, OnFinishedHandler handler) /*-{
		clientDolphin.send(commandName, function (pms) {
            handler.@com.canoo.opendolphin.client.OnFinishedHandler::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
        });
    }-*/;

    private native ClientModelStoreJS getClientModelStoreJS(ClientDolphinJS clientDolphin) /*-{
        return clientDolphin.getClientModelStore();
    }-*/;

}
