package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler;
import com.canoo.opendolphin.client.js.OnFinishedHandlerJS;
import com.canoo.opendolphin.client.js.PresentationModelJS;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;

public class Binder {
	public void bind(final MainView view, final PMContext pmContext) {

		view.getTextBox().addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(final KeyUpEvent event) {
				pmContext.getTextAttribute().setValue(view.getTextBox().getText());
			}
		});
		// bind 'textBox' to 'textAttribute' bidirectionally
		// bind 'label' to 'textAttribute':
		pmContext.getTextAttribute().addValueChangedHandler(new AttributeValueChangeHandler() {
			@Override
			public void handleValueChange(final String value) {
				view.getLabel().setText(value);
				view.getTextBox().setText(value);
			}
		});

		// send echo command on button click:
		view.getServerModificationButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				pmContext.sendEchoCommand();
				// Just to demonstrate findAttributeById
				System.out.println("*** Attribute Value from ModelStore = " + pmContext.findAttribute(PMContext.TEXT_ATTR_ID).getValue());
			}
		});

		// bind range input field to pm rangeAttribute and label to pm
		view.getRange().addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				pmContext.getRangeAttribute().setValue(event.getValue());
			}
		});

		pmContext.getRangeAttribute().addValueChangedHandler(new AttributeValueChangeHandler() {
			@Override
			public void handleValueChange(final String value) {
				view.getRangeLabel().setText(value);
			}
		});


		view.getAddServerDataButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				pmContext.sendAddCommand(new OnFinishedHandlerJS() {
					@Override
					public void handlePresentationModels(final JsArray<PresentationModelJS> pms) {
						for (int i = 0; i < pms.length(); i++) {
							PresentationModelJS pm = pms.get(i);
							view.getListDiv().add(new Label(pm.getPresentationModelType() + ": " + pm.getClientAttributes().get(0).getValue()));
						}
					}
				});
			}
		});



	}
}
