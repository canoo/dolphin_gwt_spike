
package com.canoo.opendolphin.client;

import com.canoo.dolphingwtspike.mainApplication.client.AttributeValueChangeHandler;
import com.canoo.dolphingwtspike.mainApplication.client.DolphinStarter;
import com.canoo.dolphingwtspike.mainApplication.client.MainApplication;
import com.canoo.dolphingwtspike.mainApplication.client.OnFinishedHandler;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class DolphinMain2 {

	public final static native JavaScriptObject boot(DolphinStarter dolphinStarter) /*-{
		console.log("DolphinMain2.boot: entered");
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
			var httpSession = new HttpSession('http://127.0.0.1:8888/invalidatesession');
			httpSession.invalidateSession();
			dolphinStarter.@com.canoo.dolphingwtspike.mainApplication.client.DolphinStarter::start(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(Dolphin, ClientAttribute);
		});

		console.log("DolphinMain2.boot: returning");

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

	public static native JavaScriptObject send(JavaScriptObject dolphin, String commandName, OnFinishedHandler handler) /*-{
		dolphin.getClientDolphin().send(commandName, function (pms) {
			handler.@com.canoo.dolphingwtspike.mainApplication.client.OnFinishedHandler::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
		});
	}-*/;

	public static native JavaScriptObject addAttributeValueChangeHandler(JavaScriptObject attribute, AttributeValueChangeHandler handler) /*-{
		attribute.on("valueChange", function (data) {
			handler.@com.canoo.dolphingwtspike.mainApplication.client.AttributeValueChangeHandler::handleValueChange(Ljava/lang/String;)(data.newValue);
		});
	}-*/;


}
