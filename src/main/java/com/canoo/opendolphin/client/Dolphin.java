package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Dolphin {

    private final JavaScriptObject dolphin;

    public Dolphin(JavaScriptObject Dolphin, String dolphinUrl) {
        this.dolphin = DolphinMain2.newDolphin(Dolphin, dolphinUrl);

    }


    public ClientDolphin getClientDolphin(){
        return new ClientDolphin(getClientDolphinJS(dolphin));
    }

    public JavaScriptObject getDolphin() {
        return dolphin;
    }

    private native JavaScriptObject getClientDolphinJS(JavaScriptObject dolphin) /*-{

        return dolphin.getClientDolphin();

    }-*/;

}
