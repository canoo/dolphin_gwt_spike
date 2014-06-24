package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler;
import com.canoo.opendolphin.client.gwt.OnFinishedHandler;
import com.canoo.opendolphin.client.gwt.PresentationModel;
import com.canoo.opendolphin.client.js.JSLogger;
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
				pmContext.clientDolphin.send(PMConstants.CMD_ECHO);
				// Just to demonstrate findAttributeById
				JSLogger.log("*** Attribute Value from ModelStore = " + pmContext.findAttribute(pmContext.getTextAttribute().getId()).getValue());
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
				pmContext.sendCommand(PMConstants.CMD_ADD, new OnFinishedHandler() {
					@Override
					public void handlePresentationModels(final List<PresentationModel> pms) {
						for (PresentationModel pm : pms) {
							view.getListDiv().add(new Label(pm.getPresentationModelType() + ": " + pm.getAttributes().get(0).getValue()));
						}
					}
				});
			}
		});
		view.getDevButton().addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {

				findAllPresentationModelByType(pmContext);
				findPresentationModelById(pmContext);
				deletePM(pmContext);

			}
		});

	}

	private void findAllPresentationModelByType(PMContext pmContext) {
		List<PresentationModel> pms = pmContext.clientDolphin.findAllPresentationModelByType("pm_type_1");
		JSLogger.log("** findAllPresentationModelByType: number of pmIds: " + pms.size());
		for (int i = 0; i < pms.size(); i++) {
			PresentationModel pm = pms.get(i);
			JSLogger.log("   pm " + i + ": id: " + pm.getId() + ", type: " + pm.getPresentationModelType() );
		}
	}

	private void findPresentationModelById(PMContext pmContext) {
		PresentationModel pm = pmContext.clientDolphin.findPresentationModelById(PMConstants.PM_ID);
		JSLogger.log("** findPresentationModelById('" + PMConstants.PM_ID + "'): " + (pm == null ? "-" : pm.getId()));
		pm = pmContext.clientDolphin.findPresentationModelById("nonExistingPmId");
		JSLogger.log("** findPresentationModelById('nonExistingPmId'): " + (pm == null ? "-" : pm.getId()) );
	}

	private void deletePM(PMContext pmContext) {
		PresentationModel pm;
		JSLogger.log("** deletePresentationModel: create presentationModel 'pmToDelete':");
		pm = pmContext.clientDolphin.findPresentationModelById("pmToDelete");
		JSLogger.log("   findPresentationModelById('pmToDelete'): " + (pm == null ? "-" : pm.getId()) );

		JSLogger.log("   creating presentationModel('pmToDelete')" );
		pm = pmContext.clientDolphin.presentationModel("pmToDelete", null, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);

		pm = pmContext.clientDolphin.findPresentationModelById("pmToDelete");
		JSLogger.log("   findPresentationModelById('pmToDelete'): " + (pm == null ? "-" : pm.getId()) );

		JSLogger.log("   deletePresentationModel('pmToDelete'): " );
		pmContext.clientDolphin.deletePresentationModel(pm);
		pm = pmContext.clientDolphin.findPresentationModelById("pmToDelete");
		JSLogger.log("   findPresentationModelById('pmToDelete'): " + (pm == null ? "-" : pm.getId()) );
	}
}
