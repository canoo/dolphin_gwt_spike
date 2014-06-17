package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientModelStoreJS extends JavaScriptObject
{

	protected ClientModelStoreJS() {
	}

	public final native ClientAttributeJS findAttributeById(String attributeId) /*-{

		var list = this.findAttributesByFilter(function (attr) {
			return (attr.id == attributeId)
		});
		var attribute = list[0];

		return attribute;
	}-*/;

}

