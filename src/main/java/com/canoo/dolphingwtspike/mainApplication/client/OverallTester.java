package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.*;
import com.canoo.opendolphin.client.js.JSLogger;

import java.util.Arrays;
import java.util.List;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.*;

public class OverallTester {


	public static void testAll(PMContext pmContext) {
		findAllPresentationModelByType_2_test(pmContext);
	}

	/**
	 * Create two PMs with a type on the server by sending the command CMD_CREATE_PMS_WITH_TYPE. Then
	 * verify that the two PMs can be found on the client side via 'clientDolphin.findAllPresentationModelsByType(...)'
	 */
	public static void findAllPresentationModelByType_2_test(final PMContext pmContext) {
		JSLogger.log("--- findAllPresentationModelsByType 2 ---");
		final String pmType = "my-type";

		pmContext.clientDolphin.send(PMConstants.CMD_CREATE_PMS_WITH_TYPE, new OnFinishedHandler() {
			@Override
			public void handlePresentationModels(List<ClientPresentationModel> pms) {
				assertEquals("CMD_CREATE_PMS_WITH_TYPE returns 2 results", 2, pms.size());

				List<ClientPresentationModel> foundPMs = pmContext.clientDolphin.findAllPresentationModelsByType(pmType);
				assertEquals("findAllPresentationModelsByType() returns two results", 2, foundPMs.size());
				assertEquals("findAllPresentationModelsByType()[0] is of correct type", pmType, foundPMs.get(0).getPresentationModelType());
				List<String> pmIds = Arrays.asList(foundPMs.get(0).getId(), foundPMs.get(1).getId());
				assertTrue("'pm-type-id-1' in foundPMs", pmIds.contains("pm-type-id-1"));
				assertTrue("'pm-type-id-2' in foundPMs", pmIds.contains("pm-type-id-2"));
			}
		});
	}
}
