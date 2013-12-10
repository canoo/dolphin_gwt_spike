
package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class DolphinMain2 {

	public final static native JavaScriptObject start(JavaScriptObject next) /*-{
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
			next(Dolphin, ClientAttribute, HttpSession);
		});

		console.log("DolphinMain2.start: returning");

	}-*/;

	public static void start(JavaScriptObject Dolphin, JavaScriptObject ClientAttribute) {
		System.out.println("com.canoo.opendolphin.client.DolphinMain2.javaMethod");
		System.out.println("Dolphin = " + Dolphin);
		System.out.println("ClientAttribute = " + ClientAttribute);
		JavaScriptObject dolphin = newDolphin(Dolphin, "http://127.0.0.1:8888/dolphin/");
		initializePresentationModels(dolphin, ClientAttribute);
		addEventHandler("textInput");
		bindGUIToPMs(dolphin);
	}

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

	public static void initializePresentationModels(JavaScriptObject dolphin, JavaScriptObject ClientAttribute) {
		// create named PM with attribute on the client side
		newPresentationModel(dolphin, "org.opendolphin.demo.Tutorial.modelId", attributesJS(newAttribute(ClientAttribute, "attrId"), newAttribute(ClientAttribute, "range")));
	}

	public static native JavaScriptObject newAttribute(JavaScriptObject ClientAttribute, String attributeId) /*-{
		return new ClientAttribute(attributeId);
	}-*/;

	public static native JavaScriptObject getClientModelStoreAttribute(String attributeId) /*-{
		var clientModelStore = dolphin.getClientDolphin().getClientModelStore();
		return clientModelStore.findAttributesByFilter(function (attr) {
			return (attr.propertyName == attributeId)
		})[0];
	}-*/;

	public static native JavaScriptObject setAttributeValue(JavaScriptObject attribute, String value) /*-{
		attribute.setValue(value);
	}-*/;

	public static void addEventHandler(String elementId){
//		Element element = DOM.getElementById(elementId);
//
//		final TextBox textBox = RootPanel.get(elementId);
//		final TextBox textBox = TextBox.wrap(element);
//		textBox.addChangeHandler(new ChangeHandler() {
//			public void onChange(final ChangeEvent event) {
//				setAttributeValue(getClientModelStoreAttribute("attrId"),textBox.getValue());
//			}
//		});
	}

	public static native JavaScriptObject bindGUIToPMs(JavaScriptObject dolphin) /*-{
		var clientModelStore = dolphin.getClientDolphin().getClientModelStore();
		var textAttribute = clientModelStore.findAttributesByFilter(function (attr) {
			return (attr.propertyName == "attrId")
		})[0];
		var rangeAttribute = clientModelStore.findAttributesByFilter(function (attr) {
			return (attr.propertyName == "range")
		})[0];

		// send echo command on button click
		var logActionButton = $doc.getElementById("logActionButton");
		logActionButton.addEventListener("click", function () {
			dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.echo");
		});

		// bind text input field to pm textAttribute bidirectionally
		var textInput = $doc.getElementById("textInput");
		textInput.addEventListener("input", function () {
			textAttribute.setValue(textInput.value);
		});
		textAttribute.on("valueChange", function (data) {
			textInput.value = data.newValue;
		});

		// bind label to textAttribute
		var label = $doc.getElementById("label");
		textAttribute.on("valueChange", function (data) {
			label.innerHTML = data.newValue;
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

	public final static native JavaScriptObject init() /*-{
		return function (Dolphin, ClientAttribute, HttpSession) {
			var httpSession = new HttpSession('http://127.0.0.1:8888/invalidatesession');
			httpSession.invalidateSession();

			@com.canoo.opendolphin.client.DolphinMain2::start(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(Dolphin, ClientAttribute);

		};

	}-*/;

}
