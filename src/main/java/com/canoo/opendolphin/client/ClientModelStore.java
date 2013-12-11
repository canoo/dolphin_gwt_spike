package com.canoo.opendolphin.client;

public class ClientModelStore {

    private final ClientModelStoreJS clientModelStoreJS;

    public ClientModelStore(ClientModelStoreJS clientModelStoreJS) {
        this.clientModelStoreJS = clientModelStoreJS;
    }

    public ClientAttribute findAttributeById(String id) {
        return new ClientAttribute(id, getAttribute(id, clientModelStoreJS));
    }

    private native ClientAttributeJS getAttribute(String attributeId, ClientModelStoreJS clientModelStoreJS) /*-{

        var list = clientModelStoreJS.findAttributesByFilter(function (attr) {
            return (attr.propertyName == attributeId)
        });
        var attribute = list[0];

        return attribute;
    }-*/;

}
