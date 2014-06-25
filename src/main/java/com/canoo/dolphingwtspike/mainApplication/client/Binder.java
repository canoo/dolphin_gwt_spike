package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
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

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.assertEquals;
import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.assertNull;
import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.assertTrue;
import static com.canoo.dolphingwtspike.mainApplication.shared.PMConstants.RANGE_ATTR_ID;
import static com.canoo.dolphingwtspike.mainApplication.shared.PMConstants.TEXT_ATTR_ID;

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

				createPresentationModel_test(pmContext);
				findPresentationModelById_test(pmContext);
				findAllPresentationModelByType_test(pmContext);
				deletePM_test(pmContext);
				deleteAllPresentationModelsByType_test(pmContext);
				tag_test(pmContext);

			}
		});

	}

	private void createPresentationModel_test(PMContext pmContext) {
		JSLogger.log("--- createPresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToCreate";
		String my_pm_type = "my_pm_type";
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() at start does not find anything", null, pm);

		pm = pmContext.clientDolphin.presentationModel(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId());
		assertEquals("clientDolphin.presentationModel() works (pmType)", my_pm_type, pm.getPresentationModelType());
		assertEquals("attribute's value is null", null, pm.getAt(TEXT_ATTR_ID).getValue() );

		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("newly created PM is findable", pmId, pm.getId());
	}

	private void findPresentationModelById_test(PMContext pmContext) {
		JSLogger.log("--- findPresentationModelById ---");
		PresentationModel pm = pmContext.clientDolphin.findPresentationModelById(PMConstants.PM_ID);
		assertTrue("well known pm exists", pm != null);

		pm = pmContext.clientDolphin.findPresentationModelById("nonExistingPmId");
		assertTrue("unknown pm does not exists", pm == null);
	}

	private void findAllPresentationModelByType_test(PMContext pmContext) {
		JSLogger.log("--- findAllPresentationModelByType ---");
		String pmType = "pm_type_1";

		List<PresentationModel> pms = pmContext.clientDolphin.findAllPresentationModelByType(pmType);
		assertEquals("findAllPresentationModelByType() returns one result", 1, pms.size());
		assertEquals("findAllPresentationModelByType()[0] is of correct type", pmType, pms.get(0).getPresentationModelType());
	}
	private void deletePM_test(PMContext pmContext) {
		JSLogger.log("--- deletePresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToDelete";
		pm = pmContext.clientDolphin.presentationModel(pmId, null, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deletePresentationModel(pm);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deletePresentationModel() does not find anything", null, pm);
	}
	private void deleteAllPresentationModelsByType_test(PMContext pmContext) {
		PresentationModel pm;
		JSLogger.log("--- deleteAllPresentationModelsByType ---");

		String pmId = "pmToDelete";
		String my_pm_type = "my_pm_type";

		pm = pmContext.clientDolphin.presentationModel(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deleteAllPresentationModelsOfType(my_pm_type);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deleteAllPresentationModelOfType() does not find anything", null, pm);
	}
	private void tag_test(PMContext pmContext) {
		PresentationModel pm;
		JSLogger.log("--- tag ---");

		String pmId = "pmTagId";

		pm = pmContext.clientDolphin.presentationModel(pmId, null, TEXT_ATTR_ID);
		ClientAttribute attribute = pm.getAt(TEXT_ATTR_ID);
		assertNull("new attribute's value is null", attribute.getValue());

		String tag_name = "message_tag";
		String tagValue = "some message";
		pmContext.clientDolphin.tag(pm, TEXT_ATTR_ID, tagValue, tag_name);

		assertEquals("tagging attribute worked", tagValue, pm.getAt(TEXT_ATTR_ID, tag_name).getValue());
	}

	// ----------------

}
