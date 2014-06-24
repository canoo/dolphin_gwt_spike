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

				createPresentationModel(pmContext);
				findPresentationModelById(pmContext);
				findAllPresentationModelByType(pmContext);
				deletePM(pmContext);
				deleteAllPresentationModelsByType(pmContext);

			}
		});

	}

	private void createPresentationModel(PMContext pmContext) {
		JSLogger.log("--- createPresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToCreate";
		String my_pm_type = "my_pm_type";
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() at start does not find anything", null, pm);

		pm = pmContext.clientDolphin.presentationModel(pmId, my_pm_type, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId() );
		assertEquals("clientDolphin.presentationModel() works (pmType)", my_pm_type, pm.getPresentationModelType() );

		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("newly created PM is findable", pmId, pm.getId());
	}

	private void findPresentationModelById(PMContext pmContext) {
		JSLogger.log("--- findPresentationModelById ---");
		PresentationModel pm = pmContext.clientDolphin.findPresentationModelById(PMConstants.PM_ID);
		assertTrue("well known pm exists", pm != null);

		pm = pmContext.clientDolphin.findPresentationModelById("nonExistingPmId");
		assertTrue("unknown pm does not exists", pm == null);
	}

	private void findAllPresentationModelByType(PMContext pmContext) {
		JSLogger.log("--- findAllPresentationModelByType ---");
		String pmType = "pm_type_1";

		List<PresentationModel> pms = pmContext.clientDolphin.findAllPresentationModelByType(pmType);
		assertEquals("findAllPresentationModelByType() returns one result", 1, pms.size() );
		assertEquals("findAllPresentationModelByType()[0] is of correct type", pmType, pms.get(0).getPresentationModelType() );
	}
	private void deletePM(PMContext pmContext) {
		JSLogger.log("--- deletePresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToDelete";
		pm = pmContext.clientDolphin.presentationModel(pmId, null, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);

		pmContext.clientDolphin.deletePresentationModel(pm);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deletePresentationModel() does not find anything", null, pm);
	}
	private void deleteAllPresentationModelsByType(PMContext pmContext) {
		PresentationModel pm;
		JSLogger.log("--- deleteAllPresentationModelOfType ---");

		String pmId = "pmToDelete";
		String my_pm_type = "my_pm_type";

		pm = pmContext.clientDolphin.presentationModel(pmId, my_pm_type, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);

		pmContext.clientDolphin.deleteAllPresentationModelsOfType(my_pm_type);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deleteAllPresentationModelOfType() does not find anything", null, pm);
	}

	private void assertEquals(String message, Object expected, Object actual) {
		boolean condition = (expected == null && actual == null) || (expected != null && expected.equals(actual)) ;
		if (condition) {
			JSLogger.log("OK: " + message );
		}
		else {
			String exp = expected == null ? "null" : expected.toString();
			String act = actual == null ? "null" : actual.toString();
			JSLogger.log("NOK: " + message + ",  expected: " + exp + ", actual: " + act);
		}
	}
	private void assertEqualsSilent(String message, Object expected, Object actual) {
		boolean condition = (expected == null && actual == null) || expected.equals(actual);
		if (!condition) {
			JSLogger.log("NOK: " + message + ",  expected: " + expected.toString() + ", actual: " + actual.toString());
		}
	}
	private void assertTrue(String message, Boolean condition) {
		JSLogger.log((condition ? "OK" : "NOK") + ": " + message );
	}
	private void assertTrueSilent(String message, Boolean condition) {
		if (!condition) {
			JSLogger.log("NOK: " + message);
		}
	}
}
