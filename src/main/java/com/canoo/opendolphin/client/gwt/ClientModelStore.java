package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.ClientModelStoreJS;

public class ClientModelStore {

    private final ClientModelStoreJS clientModelStoreJS;

    public ClientModelStore(ClientModelStoreJS clientModelStoreJS) {
        this.clientModelStoreJS = clientModelStoreJS;
    }

    public ClientAttribute findAttributeById(Long id) {
		ClientAttributeJS clientAttributeJS = clientModelStoreJS.findAttributeById(id);
		return new ClientAttribute(clientAttributeJS);
    }

}
