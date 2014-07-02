package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.PresentationModel;
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
		createPresentationModel_test(pmContext);
		findPresentationModelById_test(pmContext);
		findAllPresentationModelByType_test(pmContext);
		deletePM_test(pmContext);
		deleteAllPresentationModelsByType_test(pmContext);
		tag_test(pmContext);
	}

	private static void presentation_model_getAt_test(PMContext pmContext) {
		JSLogger.log("--- clientDolphin.getAt() ---");

		PresentationModel pm = pmContext.clientDolphin.getAt(PMConstants.PM_ID);
		assertNotNull("getAt(PMConstants.PM_ID) finds PM", pm);
		assertEquals("getAt(PMConstants.PM_ID) with expected id", PMConstants.PM_ID, pm.getId());

		pm = pmContext.clientDolphin.getAt("non existing pmid");
		assertNull("getAt(PMConstants.PM_ID) does not find non existing PM", pm);
	}

	private static void createClientAttribute_test(PMContext pmContext) {
		JSLogger.log("--- attribute ---");

		String attributeId = "attribute_id";
		String propertyName = "my_propertyName";
		String qualifier = "my_qualifier";
		String tag = "my_tag";
		String value = "my_value";

		ClientAttribute attribute = pmContext.clientDolphin.getClientModelStore().findAttributeById(attributeId);
		assertNull("findAttributeById() at start does not find anything", attribute);

		attribute = pmContext.clientDolphin.attribute(propertyName, qualifier, value, tag);
		assertNotNull("attribute() returns not null", attribute);
		assertNotNull("attribute.id is not null", attribute.getId());
		assertEquals("attribute.value correct", value, attribute.getValue());
		assertEquals("attribute.qualifier correct", qualifier, attribute.getQualifier());
		assertEquals("attribute.tag correct", tag, attribute.getTag());
	}

	private static void createPresentationModel_test(PMContext pmContext) {
		JSLogger.log("--- createPresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToCreate";
		String my_pm_type = "my_pm_type";
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() at start does not find anything", null, pm);

		pm = pmContext.clientDolphin.presentationModelWithType(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId());
		assertEquals("clientDolphin.presentationModel() works (pmType)", my_pm_type, pm.getPresentationModelType());
		assertEquals("attribute's value is null", null, pm.getAt(TEXT_ATTR_ID).getValue() );

		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("newly created PM is findable", pmId, pm.getId());

		pmId = "pm_2";
		pm = pmContext.clientDolphin.presentationModel(pmId, TEXT_ATTR_ID, RANGE_ATTR_ID);
		assertEquals("clientDolphin.presentationModel() works (pmId)", pmId, pm.getId());
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("newly created PM is findable", pmId, pm.getId());
	}

	private static void findPresentationModelById_test(PMContext pmContext) {
		JSLogger.log("--- findPresentationModelById ---");
		PresentationModel pm = pmContext.clientDolphin.findPresentationModelById(PMConstants.PM_ID);
		assertTrue("well known pm exists", pm != null);

		pm = pmContext.clientDolphin.findPresentationModelById("nonExistingPmId");
		assertTrue("unknown pm does not exists", pm == null);
	}

	private static void findAllPresentationModelByType_test(PMContext pmContext) {
		JSLogger.log("--- findAllPresentationModelsByType ---");
		String pmType = "pm_type_1";

		List<PresentationModel> pms = pmContext.clientDolphin.findAllPresentationModelsByType(pmType);
		assertEquals("findAllPresentationModelsByType() returns one result", 1, pms.size());
		assertEquals("findAllPresentationModelsByType()[0] is of correct type", pmType, pms.get(0).getPresentationModelType());
	}

	private static void deletePM_test(PMContext pmContext) {
		JSLogger.log("--- deletePresentationModel ---");

		PresentationModel pm;
		String pmId = "pmToDelete";
		pm = pmContext.clientDolphin.presentationModelWithType(pmId, null, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deletePresentationModel(pm);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deletePresentationModel() does not find anything", null, pm);
	}

	private static void deleteAllPresentationModelsByType_test(PMContext pmContext) {
		PresentationModel pm;
		JSLogger.log("--- deleteAllPresentationModelsByType ---");

		String pmId = "pmToDelete";
		String my_pm_type = "my_pm_type";

		pm = pmContext.clientDolphin.presentationModelWithType(pmId, my_pm_type, TEXT_ATTR_ID, RANGE_ATTR_ID);

		pmContext.clientDolphin.deleteAllPresentationModelsOfType(my_pm_type);
		pm = pmContext.clientDolphin.findPresentationModelById(pmId);
		assertEquals("findPresentationModelById() after deleteAllPresentationModelOfType() does not find anything", null, pm);
	}

	private static void tag_test(PMContext pmContext) {
		PresentationModel pm;
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

}
