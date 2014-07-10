package com.canoo.opendolphin.client.js;

import com.canoo.opendolphin.client.gwt.BooleanChangeHandler;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ClientPresentationModelJS extends JavaScriptObject
{
	protected ClientPresentationModelJS(){}

	public static final native ClientPresentationModelJS newPresentationModelJS(ClientDolphinJS clientDolphin, String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
		return clientDolphin.presentationModel(pmId, type, clientAttributesJS);
	}-*/;

	public final native String getId()/*-{
		return this.id;
	}-*/;

	public final native String getPresentationModelType()/*-{
		return this.presentationModelType;
	}-*/;

	public final native JsArray<ClientAttributeJS> getClientAttributes()/*-{
		return this.attributes;
	}-*/;
	public final native ClientAttributeJS getAt(String propertyName)/*-{
		var js = this.getAt(propertyName, undefined);
		return js === undefined ? null : js;
	}-*/;
	public final native ClientAttributeJS getAt(String propertyName, String tag)/*-{
		return this.getAt(propertyName, tag);
	}-*/;
	public final native ClientAttributeJS findAttributeById(String id)/*-{
		return this.findAttributeById(id);
	}-*/;
	public final native ClientAttributeJS findAttributeByQualifier(String qualifier)/*-{
		return this.findAttributeByQualifier(qualifier);
	}-*/;
	public final native JsArray<ClientAttributeJS> findAllAttributesByPropertyName(String propertyName)/*-{
		return this.findAllAttributesByPropertyName(propertyName);
	}-*/;
	public final native boolean isDirty()/*-{
		return this.isDirty();
	}-*/;
	public final native boolean rebase()/*-{
		return this.rebase();
	}-*/;
	public final native void addDirtyChangeHandler(BooleanChangeHandler handler) /*-{
		this.onDirty(function (data) {
			handler.@com.canoo.opendolphin.client.gwt.BooleanChangeHandler::handleChange(ZZ)(data.oldValue, data.newValue);
		});
	}-*/;
	public final native void addInvalidationHandler(PresentationModelInvalidationHandlerJS handler) /*-{
		this.onInvalidated(function (data) {
			handler.@com.canoo.opendolphin.client.js.PresentationModelInvalidationHandlerJS::handleChange(Lcom/canoo/opendolphin/client/js/ClientPresentationModelJS;)(data.source);
		});
	}-*/;

	public final native void syncWith(ClientPresentationModelJS pmJS) /*-{
		this.syncWith(pmJS);
	}-*/;
}