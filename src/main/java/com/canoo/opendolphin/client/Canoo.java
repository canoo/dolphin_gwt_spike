package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Canoo extends JavaScriptObject {

	protected Canoo() {
	}

	public static native void _info(String message) /*-{
		$wnd.canoo.info(message);
	}-*/;

	public static native void _info2(String message, String otherarg) /*-{
//		$wnd.canoo.info2({message: message, otherarg: otherarg});
		$wnd.canoo.info2(message, otherarg);
	}-*/;
}