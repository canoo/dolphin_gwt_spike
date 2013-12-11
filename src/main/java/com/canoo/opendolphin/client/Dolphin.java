package com.canoo.opendolphin.client;

public class Dolphin {

    private final ClientDolphin clientDolphin;

    public Dolphin(DolphinJS Dolphin, String dolphinUrl) {
		clientDolphin = new ClientDolphin(getClientDolphinJS(newDolphin(Dolphin, dolphinUrl)));
    }

    public ClientDolphin getClientDolphin(){
        return clientDolphin;
    }

    private native ClientDolphinJS getClientDolphinJS(DolphinJS dolphin) /*-{

        return dolphin.getClientDolphin();

    }-*/;

	private native DolphinJS newDolphin(DolphinJS Dolphin, String url) /*-{
		return new Dolphin(url);
	}-*/;
}
