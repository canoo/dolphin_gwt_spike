package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Bla extends JavaScriptObject {

	protected Bla() {
	}

	public static native void info(String message, String otherarg) /*-{
		$wnd.canoo.info2(message, otherarg);
	}-*/;
}
