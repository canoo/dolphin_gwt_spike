package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientModelStore {

    private final JavaScriptObject clientModelStoreJS;

    public ClientModelStore(JavaScriptObject clientModelStoreJS) {
        this.clientModelStoreJS = clientModelStoreJS;
    }

    public ClientAttribute findAttributeById(String id) {
        return new ClientAttribute(id, getAttribute(id, clientModelStoreJS));
    }

    private native JavaScriptObject getAttribute(String attributeId, JavaScriptObject clientModelStoreJS) /*-{

        var list = clientModelStoreJS.findAttributesByFilter(function (attr) {
            return (attr.propertyName == attributeId)
        });
        var attribute = list[0];

        return attribute;
    }-*/;

}
