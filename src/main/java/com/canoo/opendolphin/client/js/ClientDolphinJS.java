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

	public final native ClientPresentationModelJS presentationModel(String pmId, String[] attributeNames) /*-{
		return this.presentationModel(pmId, attributeNames);
	}-*/;
	public final native ClientPresentationModelJS presentationModel(String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
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
	public final native ClientPresentationModelJS[] listPresentationModels() /*-{
		return this.listPresentationModels();
	}-*/;
	public final native ClientPresentationModelJS[] findAllPresentationModelsByType(String pmType) /*-{
		return this.findAllPresentationModelByType(pmType);
	}-*/;
	public final native ClientPresentationModelJS getAt(String pmId) /*-{
		var pm = this.getAt(pmId);
		return (pm === undefined) ? null : pm;
	}-*/;
	public final native ClientPresentationModelJS findPresentationModelById(String pmId) /*-{
		var pm = this.findPresentationModelById(pmId);
		return (pm === undefined) ? null : pm;
	}-*/;
	public final native void deletePresentationModel(ClientPresentationModelJS pmJS) /*-{
		this.deletePresentationModel(pmJS);
	}-*/;
	public final native void deleteAllPresentationModelsOfType(String pmType) /*-{
		this.deleteAllPresentationModelOfType(pmType);
	}-*/;
	public final native void tag(ClientPresentationModelJS pmJS, String propertyName, Object value, String tag) /*-{
		this.tag(pmJS, propertyName, value, tag);
	}-*/;
	public final native ClientAttributeJS attribute(String propertyName, String qualifier, Object value, String tag) /*-{
		return this.attribute(propertyName, qualifier, value, tag);
	}-*/;
	public final native void addAttributeToModel(ClientPresentationModelJS pmJS, ClientAttributeJS attributeJS) /*-{
		this.addAttributeToModel(pmJS, attributeJS);
	}-*/;
	public final native void startPushListening(String pushActionName, String releaseActionName) /*-{
		this.startPushListening(pushActionName, releaseActionName);
	}-*/;
	public final native void stopPushListening() /*-{
		this.stopPushListening();
	}-*/;
}

