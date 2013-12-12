package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class PresentationModelJS extends JavaScriptObject
{
	protected PresentationModelJS(){}

	public static final native PresentationModelJS newPresentationModelJS(ClientDolphinJS clientDolphin, String pmId, String type, JsArray<ClientAttributeJS> clientAttributesJS) /*-{
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

}