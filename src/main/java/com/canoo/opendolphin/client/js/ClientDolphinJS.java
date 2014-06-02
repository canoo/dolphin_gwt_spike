package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ClientDolphinJS extends JavaScriptObject
{

	protected ClientDolphinJS() {
	}

	public final native ClientDolphinJS newInstance() /*-{
		return this();
	}-*/;

	public final native void send(String commandName) /*-{
		this.send(commandName);
	}-*/;

	public final native void send(String commandName, OnFinishedHandlerJS handler) /*-{
		this.send(commandName, function (pms) {
			handler.@com.canoo.opendolphin.client.js.OnFinishedHandlerJS::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
		});
	}-*/;

	public final native ClientAttributeJS attribute(JavaScriptObject instance, String propertyName, String qualifier, String value, String tag) /*-{
		console.log('ClientDolphinJS.attribute: 1');
		var result = this.attribute(propertyName, qualifier,  value, tag);
		console.log('ClientDolphinJS.attribute: 1');
		return result;
	}-*/;

	public final native PresentationModelJS presentationModel(String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
		console.log("***", pmId, clientAttributesJS);
//		return this.presentationModel(pmId, type, clientAttributesJS);
		return this.presentationModel(pmId, type, clientAttributesJS[0], clientAttributesJS[1]);
	}-*/;

	public final native ClientModelStoreJS getClientModelStoreJS() /*-{
		return this.getClientModelStore();
	}-*/;
	public final native void setClientModelStoreJS(ClientModelStoreJS clientModelStoreJS) /*-{
		return this.setClientModelStore(clientModelStoreJS);
	}-*/;

}

