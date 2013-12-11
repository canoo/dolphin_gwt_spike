package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ClientDolphin {

    private final JavaScriptObject clientDolphinJS;

    public ClientDolphin(JavaScriptObject clientDolphinJS) {
        this.clientDolphinJS = clientDolphinJS;
    }

    public void presentationModel(String id, String type, ClientAttribute... clientAttributes) {

        JsArray jsAttributes = JavaScriptObject.createArray().cast();
        for (ClientAttribute attribute : clientAttributes) {
            jsAttributes.push(attribute.getAttribute());
        }

        newPresentationModel(id, type, jsAttributes);
    }

    private native JavaScriptObject newPresentationModel(String pmId, String type, JsArray<JavaScriptObject> javaScriptObjects) /*-{

        var clientDolphinLocal = this.@com.canoo.opendolphin.client.ClientDolphin::clientDolphinJS;
        return clientDolphinLocal.presentationModel( pmId, type, javaScriptObjects );
    }-*/;

}
