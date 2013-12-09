package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Bla extends JavaScriptObject {

	protected Bla() {
	}

	public static native void info(String message, String otherarg) /*-{
		$wnd.canoo.info2(message, otherarg);
	}-*/;

	public static native void bla(String message, String otherarg) /*-{
		$wnd.require.config({
			baseUrl: 'MainApplication',
			paths: {
				jquery : '../libs/jquery'
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
			'Dolphin', 'ClientAttribute'
		], function (Dolphin, ClientAttribute) {
			var dolphin = new Dolphin('http://127.0.0.1:8888/dolphin/');

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





		});


	}-*/;
}
