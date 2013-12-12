package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientAttributeJS extends JavaScriptObject
{
	protected ClientAttributeJS(){}

	public final native String getId()/*-{
		return this.propertyName;
	}-*/;
	public final native String getValue()/*-{
		return this.value;
	}-*/;

}

