package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.gwt.ModelStoreChangeEventType;
import com.canoo.opendolphin.client.gwt.ModelStoreChangeHandler;
import com.canoo.opendolphin.client.js.JSLogger;

import java.util.List;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.*;
import static com.canoo.dolphingwtspike.mainApplication.shared.PMConstants.RANGE_ATTR_ID;
import static com.canoo.dolphingwtspike.mainApplication.shared.PMConstants.TEXT_ATTR_ID;

public class ClientDolphinTester {

	static void testClientDolphinRoutines(PMContext pmContext) {
		JSLogger.log("===== ClientDolphin =====");

		presentation_model_getAt_test(pmContext);
		createClientAttribute_test(pmContext);
		attribute_identity_test(pmContext);
		createPresentationModel_test(pmContext);
		pm_identity_test(pmContext);
		findPresentationModelById_test(pmContext);
		findAllPresentationModelByType_test(pmContext);
		deletePM_test(pmContext);
		deleteAllPresentationModelsByType_test(pmContext);
		tag_test(pmContext);
		addAttributeToModel_test(pmContext);
		addModelStoreListener_test(pmContext);
		addModelStoreListenerForType_test(pmContext);
	}

	private static void presentation_model_getAt_test(PMContext pmContext) {
		JSLogger.log("--- clientDolphin.getAt() ---");

		ClientPresentationModel pm = pmContext.clientDolphin.getAt(PMConstants.PM_ID);
		assertNotNull("getAt(PMConstants.PM_ID) finds PM", pm);
		assertEquals("getAt(PMConstants.PM_ID) with expected id", PMConstants.PM_ID, pm.getId());

		pm = pmContext.clientDolphin.getAt("non existing pmid");
		assertNull("getAt(PMConstants.PM_ID) does not find non existing PM", pm);
	}

	private static void createClientAttribute_test(PMContext pmContext) {
		JSLogger.log("--- attribute ---");

		String propertyName = "my_propertyName";
		String qualifier = "my_qualifier";
		String tag = "my_tag";
		String value = "my_value";

		ClientAttribute attribute = pmContext.clientDolphin.attribute(propertyName, qualifier, value, tag);
		assertNotNull("attribute() returns not null", attribute);
		assertNotNull("attribute.id is not null", attribute.getId());
		assertEquals("attribute.value correct", value, attribute.getValue());
		assertEquals("attribute.qualifier correct", qualifier, attribute.getQualifier());
		assertEquals("attribute.tag correct", tag, attribute.getTag());
	}
	private static void attribute_identity_test(PMContext pmContext) {
		JSLogger.log("--- attribute_identity ---");

		String pmId = "attribute_identity_pmId";
		String propertyName = "my_propertyName";
		String qualifier = "my_qualifier";
		String tag = "my_tag";
		String value = "my_value";

		ClientAttribute attribute0 = pmContext.clientDolphin.attribute(propertyName, qualifier, value, tag);
		ClientAttribute foundAttribute = pmContext.clientDolphin.findAttributeById(attribute0.getId());
		assertNull("findAttributeById() not successful (bc. not bound to pm yet)", foundAttribute);

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModelWithType(pmId, null, attribute0);
		foundAttribute = pmContext.clientDolphin.findAttributeById(attribute0.getId());
		assertTrue("foundAttribute same instance as original attribute", foundAttribute == attribute0);
	}

	private static void pm_identity_test(PMContext pmContext) {
		JSLogger.log("--- pm_identity ---");

		String pmId = "pm_identity_pmId";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		ClientPresentationModel foundPM = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertTrue("foundPM same instance as original pm", pm == foundPM);
	}

	private static void createPresentationModel_test(PMContext pmContext) {
		JSLogger.log("--- createPresentationModel ---");

		String pmId = "presentationModelWithType_pmId";
		String my_pm_type = "my_pm_type";

		ClientPresentationModel pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() at start does not find anything", null, pm);

		pm = pmContext.clientDolphin.presentationModelWithType(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId());
		assertEquals("clientDolphin.presentationModel() works (pmType)", my_pm_type, pm.getPresentationModelType());
		ClientAttribute foundAttribute = pm.getAt(TEXT_ATTR_ID);
		assertEquals("attribute's value is null", null, foundAttribute.getValue() );
		assertNotNull("attribute's id is not null", foundAttribute.getId());

		pmId = "presentationModel_pmId";
		pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId());

		foundAttribute = pm.getAt(TEXT_ATTR_ID);
		assertNull("attribute's value is null", foundAttribute.getValue());
		assertNotNull("attribute's id is not null", foundAttribute.getId());
	}

	private static void findPresentationModelById_test(PMContext pmContext) {
		JSLogger.log("--- findPresentationModelById ---");
		String pmId = "findPresentationModelById_pmId";
		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		ClientPresentationModel foundPm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertTrue("created pm is findable", foundPm != null);

		pm = pmContext.clientDolphin.findPresentationModelById("nonExistingPmId");
		assertTrue("unknown pm does not exists", pm == null);
	}

	private static void findAllPresentationModelByType_test(PMContext pmContext) {
		JSLogger.log("--- findAllPresentationModelsByType ---");
		String pmType = "pm_type_1";

		List<ClientPresentationModel> pms = pmContext.clientDolphin.findAllPresentationModelsByType(pmType);
		assertEquals("findAllPresentationModelsByType() returns one result", 1, pms.size());
		assertEquals("findAllPresentationModelsByType()[0] is of correct type", pmType, pms.get(0).getPresentationModelType());
	}

	private static void deletePM_test(PMContext pmContext) {
		JSLogger.log("--- deletePresentationModel ---");

		ClientPresentationModel pm;
		String pmId = "pmToDelete";
		pm = pmContext.clientDolphin.presentationModelWithType(pmId, null, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deletePresentationModel(pm);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deletePresentationModel() does not find anything", null, pm);
	}

	private static void deleteAllPresentationModelsByType_test(PMContext pmContext) {
		ClientPresentationModel pm;
		JSLogger.log("--- deleteAllPresentationModelsByType ---");

		String pmId = "pmToDelete";
		String my_pm_type = "my_pm_type";

		pm = pmContext.clientDolphin.presentationModelWithType(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deleteAllPresentationModelsOfType(my_pm_type);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deleteAllPresentationModelOfType() does not find anything", null, pm);
	}

	private static void tag_test(PMContext pmContext) {
		ClientPresentationModel pm;
		JSLogger.log("--- tag ---");

		String pmId = "pmTagId";

		pm = pmContext.clientDolphin.presentationModelWithType(pmId, null, TEXT_ATTR_ID);
		ClientAttribute attribute = pm.getAt(TEXT_ATTR_ID);
		assertNull("new attribute's value is null", attribute.getValue());

		String tag_name = "message_tag";
		String tagValue = "some message";
		pmContext.clientDolphin.tag(pm, TEXT_ATTR_ID, tagValue, tag_name);

		assertEquals("tagging attribute worked", tagValue, pm.getAt(TEXT_ATTR_ID, tag_name).getValue());
	}

	private static void addAttributeToModel_test(PMContext pmContext) {
		JSLogger.log("--- addAttributeToModel ---");

		String pmId = "addAttributeToModel_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		ClientAttribute foundAttribute = pm.getAt(propertyName);
		assertNull("attribute not on pm yet", foundAttribute);

		ClientAttribute ca = pmContext.clientDolphin.attribute(propertyName, "qualifier", "value");
		pmContext.clientDolphin.addAttributeToModel(pm, ca);
		foundAttribute = pm.getAt(propertyName);
		assertNotNull("attribute attached to pm", foundAttribute);
		assertNotNull("attribute has Id", foundAttribute.getId());
	}

	private static void addModelStoreListener_test(PMContext pmContext) {
		JSLogger.log("--- addModelStoreListener ---");

		String pmId = "addModelStoreListener_pmId";
		String propertyName = "my_prop";

		final String[] actuals = new String[2];

		pmContext.clientDolphin.addModelStoreListener(new ModelStoreChangeHandler() {
			@Override
			public void handleChange(ModelStoreChangeEventType changeType, ClientPresentationModel pm) {
				actuals[0] = changeType.getTypeValue();
				actuals[1] = pm.getId();
			}
		});
		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("ADDED event received", "ADDED", actuals[0]);
		assertEquals("added pm received", pmId, actuals[1]);

		// clean actuals for next test:
		actuals[0] = "";
		actuals[1] = "";

		pmContext.clientDolphin.deletePresentationModel(pm);
		assertEquals("REMOVED event received", "REMOVED", actuals[0]);
		assertEquals("removed pm received", pmId, actuals[1]);
	}
	private static void addModelStoreListenerForType_test(PMContext pmContext) {
		JSLogger.log("--- addModelStoreListenerForType ---");

		String pmId = "addModelStoreListenerForType_pmId";
		String pmType = "myType";
		String propertyName = "my_prop";

		final String[] actuals = new String[2];

		pmContext.clientDolphin.addModelStoreListenerForType(pmType, new ModelStoreChangeHandler() {
			@Override
			public void handleChange(ModelStoreChangeEventType changeType, ClientPresentationModel pm) {
				actuals[0] = changeType.getTypeValue();
				actuals[1] = pm.getId();
			}
		});
		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertNull("no ADDED event received", actuals[0]);
		assertNull("no added pm received", actuals[1]);

		String pmId2 = pmId + "2";
		pm = pmContext.clientDolphin.presentationModelWithType(pmId2, "unknownType", TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertNull("no ADDED event received", actuals[0]);
		assertNull("no added pm received", actuals[1]);

		String pmId3 = pmId + "3";
		pm = pmContext.clientDolphin.presentationModelWithType(pmId3, pmType, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("ADDED event received", "ADDED", actuals[0]);
		assertEquals("added pm received", pmId3, actuals[1]);

		// clean actuals for next test:
		actuals[0] = null;
		actuals[1] = null;

		pmContext.clientDolphin.deletePresentationModel(pm);
		assertEquals("REMOVED event received", "REMOVED", actuals[0]);
		assertEquals("removed pm received", pmId3, actuals[1]);
	}
}
