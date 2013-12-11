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

//    var clientModelStoreLocal = this.@com.canoo.opendolphin.client.ClientModelStore::clientModelStoreJS;
    private native JavaScriptObject getAttribute(String attributeId, JavaScriptObject clientModelStoreJS) /*-{

        console.log("in CMS: attributeId", attributeId);
        var list = clientModelStoreJS.findAttributesByFilter(function (attr) {
            return (attr.propertyName == attributeId)
        });
        console.log("list: ", list);
        var count = list.length;
        console.log("length: " + count);
        var attribute = list[0];
        console.log("attribute: ", attribute);

        return attribute;

//        return clientModelStoreLocal.findAttributesByFilter(function (attr) {
//            return (attr.propertyName == attributeId)
//        })[0];
    }-*/;

}
