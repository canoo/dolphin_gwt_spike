package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class DolphinJS extends JavaScriptObject
{

	protected DolphinJS() {
	}

	public static final native DolphinJS newDolphinJS(DolphinJS dolphinModule, String url) /*-{
		return new dolphinModule(url);
	}-*/;

	public final native ClientDolphinJS getClientDolphinJS() /*-{

		return this.getClientDolphin();

	}-*/;

}

