package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public final class Dolphin extends JavaScriptObject {

	protected Dolphin() {
	}

	public final static native JavaScriptObject newDolphin(JavaScriptObject dolphinJS) /*-{
		$wnd.require([
			'comm/ClientAttribute'
		], function (ClientAttribute) {

			var dolphin = dolphinJS;

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




		});


	}-*/;


}
