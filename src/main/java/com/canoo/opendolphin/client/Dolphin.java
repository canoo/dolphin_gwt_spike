package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Dolphin extends JavaScriptObject {

	protected Dolphin() {
	}

	public final static native void newDolphin(String url) /*-{
//		$wnd.dolphinscope = {}
//		$wnd.dolphinscope.dolphin = new $wnd.Dolphin('http://127.0.0.1:8888/dolphin/');

//		new $wnd.Dolphin('http://127.0.0.1:8888/dolphin/');

//		require([
//			'Dolphin'
//		], function (Dolphin) {
//
//
//		});

		-*/;


}
