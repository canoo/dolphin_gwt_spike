package com.canoo.opendolphin.client.js;

import com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler;
import com.google.gwt.core.client.JavaScriptObject;

public class ClientAttributeJS extends JavaScriptObject
{
	protected ClientAttributeJS(){}

	public static final native ClientAttributeJS newClientAttributeJS(ClientAttributeJS clientAttributeModule, String propertyName) /*-{
		return new clientAttributeModule(propertyName);
	}-*/;

	public final native ClientAttributeJS copy()/*-{
		return this.copy();
	}-*/;
	public final native String getId()/*-{
		return this.id;
	}-*/;
	public final native String getPropertyName()/*-{
		return this.propertyName;
	}-*/;
	public final native String getBaseValue()/*-{
		return this.baseValue;
	}-*/;
	public final native String getQualifier()/*-{
		return this.qualifier;
	}-*/;
	public final native String getTag()/*-{
		return this.tag;
	}-*/;
	public final native String getValue()/*-{
		return this.value;
	}-*/;

	public final native void setValue(String value) /*-{
		this.setValue(value);
	}-*/;
	public final native boolean isDirty()/*-{
		return this.isDirty();
	}-*/;
	public final native void rebase()/*-{
		return this.rebase();
	}-*/;
	public final native void reset()/*-{
		return this.reset();
	}-*/;
	public final native PresentationModelJS getPresentationModel()/*-{
		var pm = this.getPresentationModel();
		return pm === undefined ? null : pm;
	}-*/;
	public final native void setPresentationModel(PresentationModelJS pmJS) /*-{
		this.setPresentationModel(pmJS);
	}-*/;

	public final native void addAttributeValueChangeHandler(AttributeValueChangeHandler handler) /*-{
		this.onValueChange(function (data) {
			handler.@com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler::handleValueChange(Ljava/lang/String;)(data.newValue);
		});
	}-*/;

}

