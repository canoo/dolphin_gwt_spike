package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.BooleanChangeHandler;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.gwt.PresentationModelInvalidationHandler;
import com.canoo.opendolphin.client.js.JSLogger;

import java.util.Arrays;
import java.util.List;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.*;

class ClientPresentationModelTester {

	static void testAll(PMContext pmContext) {
		JSLogger.log("===== ClientPresentationModel =====");

		isDirty_test(pmContext);
		rebase_test(pmContext);
		onDirtyChange_test(pmContext);
		addInvalidationHandler_test(pmContext);
		findAttributeById_test(pmContext);
		findAttributeByQualifier_test(pmContext);
		findAllAttributesByPropertyName_test(pmContext);
	}

	private static void isDirty_test(PMContext pmContext) {

		JSLogger.log("--- isDirty ---");

		String pmId = "isDirty_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);
		assertFalse("pm not dirty after creation", pm.isDirty());

		pm.getAt(propertyName).setValue("new value");
		assertTrue("pm dirty after setValue", pm.isDirty());
	}

	private static void rebase_test(PMContext pmContext) {

		JSLogger.log("--- rebase ---");

		String pmId = "rebase_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);
		assertNull("value is null after creation", pm.getAt(propertyName).getValue());
		assertNull("basevalue is null after creation", pm.getAt(propertyName).getBaseValue());

		String newValue = "new value";
		pm.getAt(propertyName).setValue(newValue);
		assertEquals("value correct after setValue", newValue, pm.getAt(propertyName).getValue());
		assertNull("basevalue still null after setValue", pm.getAt(propertyName).getBaseValue());

		pm.rebase();
		assertEquals("value same after rebase", newValue, pm.getAt(propertyName).getValue());
		assertEquals("baseValue same as value after rebase", newValue, pm.getAt(propertyName).getBaseValue());
	}

	private static void onDirtyChange_test(PMContext pmContext) {
		JSLogger.log("--- onDirtyChange ---");

		String pmId = "clientPM_onDirtyChange_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);

		final Boolean[] actuals = new Boolean[2];

		pm.addDirtyChangeHandler(new BooleanChangeHandler() {
			@Override
			public void handleChange(final boolean oldValue, final boolean newValue) {
				System.out.println("hhhhh");
				actuals[0] = oldValue;
				actuals[1] = newValue;
			}
		});
		pm.getAt(propertyName).setValue("some value");
		assertEquals("old dirty false", false, actuals[0]);
		assertEquals("new dirty true", true, actuals[1]);

		pm.getAt(propertyName).setValue(null);
		assertEquals("old dirty true", true, actuals[0]);
		assertEquals("new dirty false", false, actuals[1]);
	}
	private static void addInvalidationHandler_test(PMContext pmContext) {
		JSLogger.log("--- addInvalidationHandler ---");

		String pmId = "clientPM_addInvalidationHandler_pmId";
		String pmId2 = "clientPM_addInvalidationHandler_pmId2";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);
		ClientPresentationModel pm2 = pmContext.clientDolphin.presentationModel(pmId2, propertyName);

		final ClientPresentationModel[] pms = new ClientPresentationModel[1];

		PresentationModelInvalidationHandler invalidationHandler = new PresentationModelInvalidationHandler() {

			@Override
			public void handleChange(ClientPresentationModel pm) {
				pms[0] = pm;
			}
		};
		pm.addInvalidationHandler(invalidationHandler);
		pm2.addInvalidationHandler(invalidationHandler);

		assertNull("no pm yet", pms[0]);
		assertNotNull("at found", pm.getAt(propertyName));
		pm.getAt(propertyName).setValue("some value");
		assertEquals("first pm invalidated", pm.getId(), pms[0].getId());

		pm2.getAt(propertyName).setValue("some value");
		assertEquals("second pm invalidated", pm2.getId(), pms[0].getId());
	}
	private static void findAttributeById_test(PMContext pmContext) {
		JSLogger.log("--- findAttributeById ---");

		String pmId = "clientPM_findAttributeById_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);
		ClientAttribute ca = pm.getAt(propertyName);
		String foundId = pm.findAttributeById(ca.getId()).getId();
		assertEquals("attribute findable by id", ca.getId(), foundId);
	}
	private static void findAttributeByQualifier_test(PMContext pmContext) {
		JSLogger.log("--- findAttributeByQualifier ---");

		String pmId = "clientPM_findAttributeByQualifier_pmId";
		String propertyName = "my_prop";
		String qualifier = "my_qualifier";

		ClientAttribute attribute = pmContext.clientDolphin.attribute(propertyName, qualifier, "value");
		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId);
		pmContext.clientDolphin.addAttributeToModel(pm, attribute);

		String foundId = pm.findAttributeByQualifier(qualifier).getId();
		assertEquals("attribute findable by id", attribute.getId(), foundId);
	}
	private static void findAllAttributesByPropertyName_test(PMContext pmContext) {
		JSLogger.log("--- findAllAttributesByPropertyName ---");

		String pmId = "clientPM_findAllAttributesByPropertyName_pmId";
		String propertyName = "my_prop1";
		String qualifier1 = "my_qualifier1";
		String qualifier2 = "my_qualifier2";
		String tag1 = "my_tag1";
		String tag2 = "my_tag2";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId);
		ClientAttribute attribute1 = pmContext.clientDolphin.attribute(propertyName, qualifier1, "value", tag1);
		pmContext.clientDolphin.addAttributeToModel(pm, attribute1);
		ClientAttribute attribute2 = pmContext.clientDolphin.attribute(propertyName, qualifier2, "value", tag2);
		pmContext.clientDolphin.addAttributeToModel(pm, attribute2);

		List<ClientAttribute> attributes = pm.findAllAttributesByPropertyName(propertyName);
		assertEquals("2 attributes found", 2, attributes.size());
		List<String> tags = Arrays.asList(attributes.get(0).getTag(), attributes.get(1).getTag());
		assertTrue(tag1 + " found", tags.contains(tag1));
		assertTrue(tag2 + " found", tags.contains(tag2));

	}
	private static void errorhandling_test(PMContext pmContext) {
		JSLogger.log("--- findAllAttributesByPropertyName ---");

		String pmId = "clientPM_findAllAttributesByPropertyName_pmId";
		String propertyName = "my_prop1";
		String qualifier = "my_qualifier";
		String tag1 = "my_tag1";
		String tag2 = "my_tag2";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId);
		ClientAttribute attribute1 = pmContext.clientDolphin.attribute(propertyName, qualifier, "value", tag1);
		pmContext.clientDolphin.addAttributeToModel(pm, attribute1);
		ClientAttribute attribute2 = pmContext.clientDolphin.attribute(propertyName, qualifier, "value", tag2);

		// throws error: throw new Error("There already is an attribute with qualifier: " + attribute.getQualifier() + " in presentation model with id: " + this.id);
		// todo (Sven 10.07.14): how can it be handled?
		pmContext.clientDolphin.addAttributeToModel(pm, attribute2);

	}

}
