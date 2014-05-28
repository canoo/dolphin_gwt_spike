package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class OpenDolphinJS extends JavaScriptObject
{

	protected OpenDolphinJS() {
	}

	public final native ClientDolphinJS getClientDolphinJS() /*-{

		return this.dolphin;

	}-*/;

}

