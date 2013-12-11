package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ClientAttributeJS extends JavaScriptObject
{
	protected ClientAttributeJS(){}

	public final native String getValue()/*-{
		return this.value;
	}-*/;

}

