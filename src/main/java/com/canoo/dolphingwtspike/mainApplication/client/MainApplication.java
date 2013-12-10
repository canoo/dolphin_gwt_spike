package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.DolphinMain2;
import com.canoo.opendolphin.client.PresentationModelJS;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {

	Label label;
	TextBox textBox;
	TextBox range;
	Label rangeLabel;
	VerticalPanel listDiv;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DolphinMain2.boot(this, MainApplication.init());
	}

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

	public void start(JavaScriptObject Dolphin, JavaScriptObject ClientAttribute) {
		final JavaScriptObject dolphin = DolphinMain2.newDolphin(Dolphin, "http://127.0.0.1:8888/dolphin/");
		initializePresentationModels(dolphin, ClientAttribute);

		final JavaScriptObject textAttribute = DolphinMain2.getAttribute(dolphin, "attrId");
		final JavaScriptObject rangeAttribute = DolphinMain2.getAttribute(dolphin, "range");

		textBox = new TextBox();
		textBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(final KeyUpEvent event) {
				DolphinMain2.setAttributeValue(textAttribute, textBox.getText());
			}
		});
		// bind 'textBox' to 'textAttribute' bidirectionally
		// bind 'label' to 'textAttribute':
		DolphinMain2.addAttributeValueChangeHandler(textAttribute, new AttributeValueChangeHandler() {
			@Override
			public void handleValueChange(final String value) {
				label.setText(value);
				textBox.setText(value);
			}
		});

		label = new Label("--");
		// send echo command on button click:
		final Button serverModificationButton = new Button("Server Modification");
		serverModificationButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				DolphinMain2.send(dolphin, "org.opendolphin.demo.Tutorial.echo");
			}
		});
		Label helpLabel = new Label("Drag the slider to see the label being updated.");

		// bind range input field to pm rangeAttribute and label to pm
		range = new TextBox(); range.getElement().setAttribute("type", "range");
		range.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				DolphinMain2.setAttributeValue(rangeAttribute, event.getValue());
			}
		});
		rangeLabel = new Label("--");
		DolphinMain2.addAttributeValueChangeHandler(rangeAttribute, new AttributeValueChangeHandler() {
			@Override
			public void handleValueChange(final String value) {
				rangeLabel.setText(value);
			}
		});

		Label help2Label = new Label("Click to get new content from the server side, bound to a list.");
		final Button addServerDataButton = new Button("Add Server Data");
		addServerDataButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				DolphinMain2.send3(dolphin, "org.opendolphin.demo.Tutorial.add", new OnFinishedHandler() {
					@Override
					public void handlePresentationModels(final JsArray<PresentationModelJS> pms) {
						for (int i = 0; i < pms.length(); i++) {
							PresentationModelJS pm = pms.get(i);
							listDiv.add(new Label(pm.getPresentationModelType() + ": " + pm.getClientAttributes().get(0).getValue()));
						}
					}
				});
			}
		});

		listDiv = new VerticalPanel();


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
	}


}
