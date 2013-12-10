package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.DolphinMain2;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {

	Label label;
	TextBox textBox;

	public final static native JavaScriptObject init() /*-{
		return function (Dolphin, ClientAttribute, HttpSession, view) {
			var httpSession = new HttpSession('http://127.0.0.1:8888/invalidatesession');
			httpSession.invalidateSession();

			view.@com.canoo.dolphingwtspike.mainApplication.client.MainApplication::start(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(Dolphin, ClientAttribute);

		};

	}-*/;

	public static void initializePresentationModels(JavaScriptObject dolphin, JavaScriptObject ClientAttribute) {
		// create named PM with attribute on the client side
		DolphinMain2.newPresentationModel(dolphin, "org.opendolphin.demo.Tutorial.modelId",
			DolphinMain2.attributesJS(DolphinMain2.newAttribute(ClientAttribute, "attrId"),
			DolphinMain2.newAttribute(ClientAttribute, "range"))
		);
	}

	public void handleAttributeChange(String value) {
		label.setText(value);
		textBox.setText(value);
	}
	public void start(JavaScriptObject Dolphin, JavaScriptObject ClientAttribute) {
		final JavaScriptObject dolphin = DolphinMain2.newDolphin(Dolphin, "http://127.0.0.1:8888/dolphin/");
		initializePresentationModels(dolphin, ClientAttribute);

		final JavaScriptObject textAttribute = DolphinMain2.getAttribute(dolphin, "attrId");

		textBox = new TextBox(); textBox.getElement().setId("textInput");
		textBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(final KeyUpEvent event) {
				DolphinMain2.setAttributeValue(textAttribute, textBox.getText());
			}
		});
		DolphinMain2.addValueChangedHandler(this, textAttribute);

		label = new Label("--"); label.getElement().setId("label");
		final Button serverModificationButton = new Button("Server Modification"); serverModificationButton.getElement().setId("logActionButton");
		Label helpLabel = new Label("Drag the slider to see the label being updated.");
		TextBox range = new TextBox(); range.getElement().setAttribute("type", "range"); range.getElement().setId("range");
		Label rangeLabel = new Label("--"); rangeLabel.getElement().setId("rangeLabel");

		Label help2Label = new Label("Click to get new content from the server side, bound to a list.");
		final Button addServerDataButton = new Button("Add Server Data"); addServerDataButton.getElement().setId("addButton");

		VerticalPanel listDiv = new VerticalPanel(); listDiv.getElement().setId("list");


		// Assume that the host HTML has elements defined whose
		// IDs are "slot1", "slot2".  In a real app, you probably would not want
		// to hard-code IDs.  Instead, you could, for example, search for all
		// elements with a particular CSS class and replace them with widgets.
		//
		RootPanel.get("slot1").add(textBox);
		RootPanel.get("slot1").add(label);
		RootPanel.get("slot1").add(serverModificationButton);

		RootPanel.get("slot1").add(helpLabel);
		RootPanel.get("slot1").add(range);
		RootPanel.get("slot1").add(rangeLabel);

		RootPanel.get("slot1").add(help2Label);
		RootPanel.get("slot1").add(addServerDataButton);
		RootPanel.get("slot1").add(listDiv);

		DolphinMain2.bindGUIToPMs(dolphin);

	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DolphinMain2.boot(this, MainApplication.init());



	}


}
