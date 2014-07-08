package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.js.JSLogger;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.assertFalse;
import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.assertTrue;

class ClientPresentationModelTester {

	static void testAll(PMContext pmContext) {
		JSLogger.log("===== ClientPresentationModel =====");

		isDirty_test(pmContext);
	}

	private static void isDirty_test(PMContext pmContext) {

		String pmId = "isDirty_pmId";
		String propertyName = "my_prop";

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel(pmId, propertyName);
		assertFalse("pm not dirty after creation", pm.isDirty());

		pm.getAt(propertyName).setValue("new value");
		assertTrue("pm dirty after setValue", pm.isDirty());
	}

}
