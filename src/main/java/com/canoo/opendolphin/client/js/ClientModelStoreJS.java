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

	public final native void addModelStoreListener(ModelStoreChangeHandlerJS handler) /*-{
		this.onModelStoreChange(function (event)  {
			handler.@com.canoo.opendolphin.client.js.ModelStoreChangeHandlerJS::handleChange(Ljava/lang/String;Lcom/canoo/opendolphin/client/js/ClientPresentationModelJS;)(event.eventType, event.clientPresentationModel);
		} );
	}-*/;
}

