package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientModelStoreJS extends JavaScriptObject
{

	protected ClientModelStoreJS() {
	}

	public final native ClientAttributeJS findAttributeById(String attributeId) /*-{
		var result = this.findAttributeById(attributeId);
		return (result === undefined) ? null : result;
	}-*/;

}

