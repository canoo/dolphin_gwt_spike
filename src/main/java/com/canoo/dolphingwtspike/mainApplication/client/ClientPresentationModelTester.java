package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.js.JSLogger;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.*;

class ClientPresentationModelTester {

	static void testAll(PMContext pmContext) {
		JSLogger.log("===== ClientPresentationModel =====");

		isDirty_test(pmContext);
		rebase_test(pmContext);
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

}
