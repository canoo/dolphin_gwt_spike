
package com.canoo.opendolphin.client.js;

public class JSLogger {

	public final static native void log(String message) /*-{
		console.log(message);
	}-*/;


}
