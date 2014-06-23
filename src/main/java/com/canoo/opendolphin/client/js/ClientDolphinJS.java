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
		this.send(commandName, {onFinished: function (pms)  {
			handler.@com.canoo.opendolphin.client.js.OnFinishedHandlerJS::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
			}
		});
	}-*/;
	public final native void sendEmpty(OnFinishedHandlerJS handler) /*-{
		this.sendEmpty({onFinished: function (pms)  {
			handler.@com.canoo.opendolphin.client.js.OnFinishedHandlerJS::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
		}
		});
	}-*/;

	public final native ClientAttributeJS attribute(String propertyName, String qualifier, String value) /*-{
		var result = this.attribute(propertyName, qualifier, value);
		return result;
	}-*/;
	public final native ClientAttributeJS attribute(String propertyName, String qualifier, String value, String tag) /*-{
		var result = this.attribute(propertyName, qualifier, value, tag);
		return result;
	}-*/;

	public final native PresentationModelJS presentationModel(String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
		return this.presentationModel(pmId, type, clientAttributesJS);
	}-*/;

	public final native ClientModelStoreJS getClientModelStore() /*-{
		return this.getClientModelStore();
	}-*/;
	public final native void setClientModelStore(ClientModelStoreJS clientModelStoreJS) /*-{
		return this.setClientModelStore(clientModelStoreJS);
	}-*/;
	public final native String[] listPresentationModelIds() /*-{
		return this.listPresentationModelIds();
	}-*/;
	public final native PresentationModelJS[] listPresentationModels() /*-{
		return this.listPresentationModels();
	}-*/;
	public final native PresentationModelJS[] findAllPresentationModelByType(String pmType) /*-{
		return this.findAllPresentationModelByType(pmType);
	}-*/;

}

