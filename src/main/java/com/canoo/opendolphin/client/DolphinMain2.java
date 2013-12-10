
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

	public static native JavaScriptObject addValueChangedHandler(MainApplication view, JavaScriptObject attribute) /*-{
		attribute.on("valueChange", function (data) {
			console.log("value CHANGE");

			view.@com.canoo.dolphingwtspike.mainApplication.client.MainApplication::handleAttributeChange(Ljava/lang/String;)(data.newValue);
		});
	}-*/;

	public static native JavaScriptObject bindGUIToPMs(JavaScriptObject dolphin) /*-{
		var clientModelStore = dolphin.getClientDolphin().getClientModelStore();
		var rangeAttribute = clientModelStore.findAttributesByFilter(function (attr) {
			return (attr.propertyName == "range")
		})[0];

		// send echo command on button click
		var logActionButton = $doc.getElementById("logActionButton");
		logActionButton.addEventListener("click", function () {
			dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.echo");
		});

		// bind range input field to pm rangeAttribute and label to pm
		var rangeInput  = $doc.getElementById("range");
		var rangeOutput = $doc.getElementById("rangeLabel");
		rangeInput.addEventListener("input", function () {
			rangeAttribute.setValue(rangeInput.value);
		});
		rangeAttribute.on("valueChange", function (data) {
			rangeOutput.innerHTML = data.newValue;
		});

		// send add command on button click and add a div for each received model
		var addButton = $doc.getElementById("addButton");
		var list      = $doc.getElementById("list");
		addButton.addEventListener("click", function () {
			dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.add", function (models) {
				console.log("NEW models", models);
				models.forEach(function (model) {
					var element = $doc.createElement("div");
					element.innerHTML = model.presentationModelType + ": " + model.attributes[0].value;
					list.appendChild(element);
				})
			});
		});
	}-*/;


}
