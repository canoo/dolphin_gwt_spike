package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ClientDolphin {

    public void presentationModel(Dolphin dolphin, String id, String type, ClientAttribute... clientAttributes) {

        JsArray jsAttributes = JavaScriptObject.createArray().cast();
        for (ClientAttribute attribute : clientAttributes) {
            jsAttributes.push(attribute.getAttribute());
        }

        DolphinMain2.newPresentationModel(dolphin.getDolphin(), id, type, jsAttributes);
    }


}
