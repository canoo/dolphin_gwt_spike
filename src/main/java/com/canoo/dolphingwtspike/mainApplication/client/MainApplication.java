package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.*;
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
		DolphinMain2.boot(new DolphinStarter() {
			@Override
			public void start(final JavaScriptObject Dolphin, final JavaScriptObject ClientAttribute) {
				MainApplication.this.start(Dolphin, ClientAttribute);
			}
		});
	}

	public void start(JavaScriptObject Dolphin, JavaScriptObject ClientAttribute) {


        final Dolphin dolphin = new Dolphin(Dolphin, "http://127.0.0.1:8888/dolphin/");
        final ClientDolphin clientDolphin = dolphin.getClientDolphin();

        final ClientAttribute textAttribute = new ClientAttribute(ClientAttribute, "attrId");
        final ClientAttribute rangeAttribute = new ClientAttribute(ClientAttribute, "range");

		// create named PM with attribute on the client side
        String type = null;
        clientDolphin.presentationModel("org.opendolphin.demo.Tutorial.modelId", type, textAttribute, rangeAttribute);

		textBox = new TextBox();
		textBox.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(final KeyUpEvent event) {
                textAttribute.setValue(textBox.getText());
			}
		});
		// bind 'textBox' to 'textAttribute' bidirectionally
		// bind 'label' to 'textAttribute':
        textAttribute.addValueChangedHandler(new AttributeValueChangeHandler() {
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
				clientDolphin.send("org.opendolphin.demo.Tutorial.echo");
			}
		});
		Label helpLabel = new Label("Drag the slider to see the label being updated.");

		// bind range input field to pm rangeAttribute and label to pm
		range = new TextBox(); range.getElement().setAttribute("type", "range");
		range.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				rangeAttribute.setValue(event.getValue());
			}
		});

		rangeLabel = new Label("--");
        rangeAttribute.addValueChangedHandler(new AttributeValueChangeHandler() {
            @Override
            public void handleValueChange(final String value) {
                rangeLabel.setText(value);
            }
        });


		Label help2Label = new Label("Click to get new content from the server side, bound to a list.");
		final Button addServerDataButton = new Button("Add Server Data");
		addServerDataButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				clientDolphin.send("org.opendolphin.demo.Tutorial.add", new OnFinishedHandler() {
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
