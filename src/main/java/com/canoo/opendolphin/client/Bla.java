package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Bla extends JavaScriptObject {

	protected Bla() {
	}

	public static native void info(String message, String otherarg) /*-{
		$wnd.canoo.info2(message, otherarg);
	}-*/;

	public static native void bla(String message, String otherarg) /*-{
		$wnd.require.config({
			baseUrl: 'MainApplication'
		});

		$wnd.require([
			'Dolphin'
		], function (Dolphin) {

			var dolphin = new Dolphin('http://127.0.0.1:8888/dolphin/');

		});
	}-*/;
}
