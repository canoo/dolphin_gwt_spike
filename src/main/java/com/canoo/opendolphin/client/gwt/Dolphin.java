package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.ClientDolphinJS;
import com.canoo.opendolphin.client.js.DolphinJS;

public class Dolphin {

    private final ClientDolphin clientDolphin;

    public Dolphin(DolphinJS dolphinModule, final ClientAttributeJS clientAttributeModule, String dolphinUrl) {
		clientDolphin = new ClientDolphin(getClientDolphinJS(newDolphinJS(dolphinModule, dolphinUrl)), clientAttributeModule);
    }

    public ClientDolphin getClientDolphin(){
        return clientDolphin;
    }

    private native ClientDolphinJS getClientDolphinJS(DolphinJS dolphinJS) /*-{

        return dolphinJS.getClientDolphin();

    }-*/;

	private native DolphinJS newDolphinJS(DolphinJS dolphinModule, String url) /*-{
		return new dolphinModule(url);
	}-*/;
}
