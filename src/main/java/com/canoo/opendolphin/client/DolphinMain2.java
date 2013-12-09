package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

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

	public static void javaMethod(JavaScriptObject Dolphin, JavaScriptObject ClientAttribute) {
		System.out.println("com.canoo.opendolphin.client.DolphinMain2.javaMethod");
		System.out.println("Dolphin = " + Dolphin);
		System.out.println("ClientAttribute = " + ClientAttribute);
	}

	public final static native JavaScriptObject init() /*-{
		return function (Dolphin, ClientAttribute, HttpSession) {
			console.log("DolphinMain2.init, dolphin: " + Dolphin);

			var httpSession = new HttpSession('http://127.0.0.1:8888/invalidatesession');
			httpSession.invalidateSession();

			@com.canoo.opendolphin.client.DolphinMain2::javaMethod(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(Dolphin, ClientAttribute);

			var dolphin = new Dolphin("http://127.0.0.1:8888/dolphin/");

			// create named PM with attribute on the client side
			var textAttribute  = new ClientAttribute("attrId");
			var rangeAttribute = new ClientAttribute("range");
			console.log("INIT PM");
			dolphin.getClientDolphin().presentationModel(
				"org.opendolphin.demo.Tutorial.modelId", undefined,
				textAttribute, rangeAttribute
			);

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



		};

	}-*/;

}
