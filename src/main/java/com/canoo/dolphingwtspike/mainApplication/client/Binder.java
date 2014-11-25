package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.ChangeHandler;
import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.gwt.OnFinishedHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label;

import java.util.List;

public class Binder {
	public void bind(final MainView view, final PMContext pmContext) {

		// TODO: find a way to register a real ValueChangedHandler (we did not find one with GWT)
		// in HTML5 you do it as follows:
		//  textInput.addEventListener("input", function () {
		//    textAttribute.setValue(textInput.value);
	    //  });
		view.getTextBox().addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(final KeyUpEvent event) {
				pmContext.getTextAttribute().setValue(view.getTextBox().getText());
			}
		});
		// bind 'textBox' to 'textAttribute' bidirectionally
		// bind 'label' to 'textAttribute':
		pmContext.getTextAttribute().addValueChangeHandler(new ChangeHandler<String>() {
			@Override
			public void handleChange(final String oldValue, final String newValue) {
				view.getLabel().setText(newValue);
				view.getTextBox().setText(newValue);
			}
		});

		// send echo command on button click:
		view.getServerModificationButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				pmContext.clientDolphin.send(PMConstants.CMD_ECHO);
			}
		});

		// bind range input field to pm rangeAttribute and label to pm
		view.getRange().addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(final ValueChangeEvent<String> event) {
				pmContext.getRangeAttribute().setValue(event.getValue());
			}
		});

		pmContext.getRangeAttribute().addValueChangeHandler(new ChangeHandler<String>() {
			@Override
			public void handleChange(final String oldValue, final String newValue) {
				view.getRangeLabel().setText(newValue);
			}
		});

		view.getAddServerDataButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				pmContext.sendCommand(PMConstants.CMD_ADD, new OnFinishedHandler() {
					@Override
					public void handlePresentationModels(final List<ClientPresentationModel> pms) {
						for (ClientPresentationModel pm : pms) {
							view.getListDiv().add(new Label(pm.getPresentationModelType() + ": " + pm.getAttributes().get(0).getValue()));
						}
					}
				});
			}
		});
		view.getDevButton().addClickHandler(new ClickHandler() {

			public void onClick(final ClickEvent event) {

				ClientAttributeTester.testClientAttributeRoutines(pmContext);
				ClientDolphinTester.testClientDolphinRoutines(pmContext);
				ClientModelStoreTester.testAll(pmContext);
				ClientPresentationModelTester.testAll(pmContext);
				OverallTester.testAll(pmContext);

			}
		});

	}


}
