package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.ClientDolphinJS;
import com.canoo.opendolphin.client.js.DolphinJS;

public class Dolphin {

    private final ClientDolphin clientDolphin;

    public Dolphin(DolphinJS dolphinModule, final ClientAttributeJS clientAttributeModule, String dolphinUrl) {
		DolphinJS dolphinJS = DolphinJS.newDolphinJS(dolphinModule, dolphinUrl);
		clientDolphin = new ClientDolphin(dolphinJS.getClientDolphinJS(), clientAttributeModule);
    }

    public ClientDolphin getClientDolphin(){
        return clientDolphin;
    }


}
