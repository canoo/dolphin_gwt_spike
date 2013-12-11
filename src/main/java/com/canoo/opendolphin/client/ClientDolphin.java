package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Label;

public class ClientDolphin {

    private final JavaScriptObject clientDolphinJS;

    public ClientDolphin(JavaScriptObject clientDolphinJS) {
        this.clientDolphinJS = clientDolphinJS;
    }

    public void send(String commandName){
        sendJS(commandName);
    }

    public void send(String commandName, OnFinishedHandler handler){
        sendJS(commandName, handler);
    }

    public void presentationModel(String id, String type, ClientAttribute... clientAttributes) {

        JsArray jsAttributes = JavaScriptObject.createArray().cast();
        for (ClientAttribute attribute : clientAttributes) {
            jsAttributes.push(attribute.getAttribute());
        }

        presentationModelJS(id, type, jsAttributes);
    }

    private native JavaScriptObject presentationModelJS(String pmId, String type, JsArray<JavaScriptObject> javaScriptObjects) /*-{
        var clientDolphinLocal = this.@com.canoo.opendolphin.client.ClientDolphin::clientDolphinJS;
        return clientDolphinLocal.presentationModel( pmId, type, javaScriptObjects );
    }-*/;

    private native JavaScriptObject sendJS(String commandName) /*-{
        var clientDolphinLocal = this.@com.canoo.opendolphin.client.ClientDolphin::clientDolphinJS;
        clientDolphinLocal.send(commandName);
    }-*/;

    private native JavaScriptObject sendJS(String commandName, OnFinishedHandler handler) /*-{
        var clientDolphinLocal = this.@com.canoo.opendolphin.client.ClientDolphin::clientDolphinJS;
        clientDolphinLocal.send(commandName, function (pms) {
            handler.@com.canoo.opendolphin.client.OnFinishedHandler::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
        });
    }-*/;

}
