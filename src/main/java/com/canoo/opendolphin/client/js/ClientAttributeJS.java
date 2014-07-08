package com.canoo.opendolphin.client.js;

import com.canoo.opendolphin.client.gwt.AttributeChangeHandler;
import com.canoo.opendolphin.client.gwt.BooleanChangeHandler;
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
	public final native ClientPresentationModelJS getPresentationModel()/*-{
		var pm = this.getPresentationModel();
		return pm === undefined ? null : pm;
	}-*/;
	public final native void setPresentationModel(ClientPresentationModelJS pmJS) /*-{
		this.setPresentationModel(pmJS);
	}-*/;
	public final native void setQualifier(String qualifier) /*-{
		this.setQualifier(qualifier);
	}-*/;

	public final native void addAttributeValueChangeHandler(AttributeChangeHandler handler) /*-{
		this.onValueChange(function (data) {
			handler.@com.canoo.opendolphin.client.gwt.AttributeChangeHandler::handleChange(Ljava/lang/String;Ljava/lang/String;)(data.oldValue, data.newValue);
		});
	}-*/;
	public final native void addQualifierChangeHandler(AttributeChangeHandler handler) /*-{
		this.onQualifierChange(function (data) {
			handler.@com.canoo.opendolphin.client.gwt.AttributeChangeHandler::handleChange(Ljava/lang/String;Ljava/lang/String;)(data.oldValue, data.newValue);
		});
	}-*/;
	public final native void addDirtyChangeHandler(BooleanChangeHandler handler) /*-{
		this.onDirty(function (data) {
			handler.@com.canoo.opendolphin.client.gwt.BooleanChangeHandler::handleChange(ZZ)(data.oldValue, data.newValue);
		});
	}-*/;

}

