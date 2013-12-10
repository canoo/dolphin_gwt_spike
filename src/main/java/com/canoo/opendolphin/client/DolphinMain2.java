
package com.canoo.opendolphin.client;

import com.canoo.dolphingwtspike.mainApplication.client.MainApplication;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class DolphinMain2 {

	public final static native JavaScriptObject boot(MainApplication view, JavaScriptObject next) /*-{
		console.log("DolphinMain2.start: entered");
		$wnd.require.config({
			baseUrl: 'com.canoo.opendolphin.OpenDolphin',
			paths: {
				jquery : 'jquery'
			},

			shim: {
				'jquery': {
					exports: '$'
				}
			},

			map : {
				'*': {
					$ : 'jquery'
				}
			}
		});

		$wnd.require([
			'Dolphin', 'comm/ClientAttribute', 'comm/HttpSession'
		], function (Dolphin, ClientAttribute, HttpSession) {
			console.log("DolphinMain2.start: in callback");
			next(Dolphin, ClientAttribute, HttpSession, view);
		});

		console.log("DolphinMain2.start: returning");

	}-*/;

	public static JsArray attributesJS(JavaScriptObject...attributes) {
		JsArray result = JavaScriptObject.createArray().cast();
		for (JavaScriptObject attribute : attributes) {
			result.push(attribute);
		}
		return result;
	}

	public static native JavaScriptObject newDolphin(JavaScriptObject Dolphin, String url) /*-{
		return new Dolphin(url);
	}-*/;

	public static native JavaScriptObject newPresentationModel(JavaScriptObject dolphin, String pmId, JsArray<JavaScriptObject> javaScriptObjects) /*-{

		return dolphin.getClientDolphin().presentationModel(
			pmId, undefined, javaScriptObjects
		);

	}-*/;

	public static native JavaScriptObject newAttribute(JavaScriptObject ClientAttribute, String attributeId) /*-{
		return new ClientAttribute(attributeId);
	}-*/;

	public static native JavaScriptObject getAttribute(JavaScriptObject dolphin, String attributeId) /*-{
		var clientModelStore = dolphin.getClientDolphin().getClientModelStore();
		return clientModelStore.findAttributesByFilter(function (attr) {
			return (attr.propertyName == attributeId)
		})[0];
	}-*/;

	public static native JavaScriptObject setAttributeValue(JavaScriptObject attribute, String value) /*-{
		attribute.setValue(value);
	}-*/;
	public static native JavaScriptObject send(JavaScriptObject dolphin, String commandName) /*-{
		dolphin.getClientDolphin().send(commandName);
	}-*/;

	public static native JavaScriptObject send2(MainApplication view, JavaScriptObject dolphin, String commandName) /*-{
		dolphin.getClientDolphin().send(commandName, function (models) {
			view.@com.canoo.dolphingwtspike.mainApplication.client.MainApplication::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(models);
		});
	}-*/;

	public static native JavaScriptObject addValueChangedHandler(MainApplication view, JavaScriptObject attribute) /*-{
		attribute.on("valueChange", function (data) {
			console.log("value CHANGE");

			view.@com.canoo.dolphingwtspike.mainApplication.client.MainApplication::handleAttributeChange(Ljava/lang/String;)(data.newValue);
		});
	}-*/;
	public static native JavaScriptObject addRangeChangedHandler(MainApplication view, JavaScriptObject attribute) /*-{
		attribute.on("valueChange", function (data) {
			console.log("value CHANGE");

			view.@com.canoo.dolphingwtspike.mainApplication.client.MainApplication::handleRangeChanged(Ljava/lang/String;)(data.newValue);
		});
	}-*/;


}
