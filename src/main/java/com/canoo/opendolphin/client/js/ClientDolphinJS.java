package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientDolphinJS extends JavaScriptObject
{

	protected ClientDolphinJS() {
	}

	public final native void sendJS(String commandName) /*-{
		this.send(commandName);
	}-*/;

}

