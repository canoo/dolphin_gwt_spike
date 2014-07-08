package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.AttributeChangeHandler;
import com.canoo.opendolphin.client.gwt.BooleanChangeHandler;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.ClientPresentationModel;
import com.canoo.opendolphin.client.js.JSLogger;

import static com.canoo.dolphingwtspike.mainApplication.client.TestHelper.*;

public class ClientAttributeTester {

	static void testClientAttributeRoutines(PMContext pmContext) {
		JSLogger.log("===== ClientAttribute =====");

		client_attribute_create_test(pmContext);
		client_attribute_setValue_test(pmContext);
		client_attribute_copy_test(pmContext);
		client_attribute_isDirty_test(pmContext);
		client_attribute_baseValue_test(pmContext);
		client_attribute_presentationModel_test(pmContext);
		client_attribute_setQualifier_test(pmContext);
		client_attribute_onValueChange_test(pmContext);
		client_attribute_onQualifierChange_test(pmContext);
		client_attribute_onDirtyChange_test(pmContext);
	}

	private static void client_attribute_create_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_create ---");

		String my_property = "my_property_create";
		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		assertNotNull("new attribute created", ca);
		assertEquals("propertyName set", my_property, ca.getPropertyName());
		assertEquals("qualifier set", "qualifier", ca.getQualifier());
		assertEquals("value set", "value", ca.getValue());
		assertNotNull("id not null", ca.getId());
	}

	private static void client_attribute_setValue_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_setValue ---");

		String my_property = "my_property_setValue";
		String newValue = "newValue";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		ca.setValue(newValue);
		assertEquals("value set", newValue, ca.getValue());

		ca.reset();
		assertEquals("value has old value after reset", "value", ca.getValue());
	}

	private static void client_attribute_copy_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_copy ---");

		String my_property = "my_property_copy";
		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		ClientAttribute ca2 = ca.copy();
		assertNotNull("attribute copied", ca2);
		assertEquals("qualifier copied", ca.getQualifier(), ca2.getQualifier());
		assertEquals("value copied", ca.getValue(), ca2.getValue());
	}

	private static void client_attribute_isDirty_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_isDirty ---");

		String my_property = "my_property_isDirty";
		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		assertFalse("not dirty after creation", ca.isDirty());

		ca.setValue("newValue");
		assertTrue("dirty after setValue", ca.isDirty());
	}

	private static void client_attribute_baseValue_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_baseValue ---");

		String my_property = "my_property_baseValue";
		String newValue = "newValue";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		assertEquals("basevalue at creation", "value", ca.getBaseValue());

		ca.setValue(newValue);
		assertEquals("basevalue not modified after setValue", "value", ca.getBaseValue());

		ca.rebase();
		assertEquals("basevalue has new value after rebase", newValue, ca.getBaseValue());
	}

	private static void client_attribute_presentationModel_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_presentationModel ---");

		String my_property = "my_property_presentationModel";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		assertNull("no PM on standalone attribute", ca.getPresentationModel());

		ClientPresentationModel pm = pmContext.clientDolphin.presentationModel("pm_ClientAttribute_pm");
		ca.setPresentationModel(pm);
		ClientPresentationModel pm2 = ca.getPresentationModel();
		assertNotNull("PM set on attribute", pm2);
		assertEquals( "...and have the same ids", pm.getId(), pm2.getId());
	}

	private static void client_attribute_setQualifier_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_setQualifier ---");

		String my_property = "my_property_setQualifier";
		String newQualifer = "newQualifer";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");
		ca.setQualifier(newQualifer);
		assertEquals("qualifier set", newQualifer, ca.getQualifier());
	}

	private static void client_attribute_onValueChange_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_onValueChange ---");

		String my_property = "my_property_onValueChange";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");

		final String[] actuals = new String[2];


		ca.addValueChangedHandler(new AttributeChangeHandler() {
			@Override
			public void handleChange(final String oldValue, final String newValue) {
				actuals[0] = oldValue;
				actuals[1] = newValue;
			}
		});
		ca.setValue("new value");
		assertEquals("received old value", "value", actuals[0]);
		assertEquals("received new value", "new value", actuals[1]);
	}
	private static void client_attribute_onQualifierChange_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_onQualifierChange ---");

		String my_property = "my_property_onQualifierChange";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");

		final String[] actuals = new String[2];


		ca.addQualifierChangeHandler(new AttributeChangeHandler() {
			@Override
			public void handleChange(final String oldValue, final String newValue) {
				actuals[0] = oldValue;
				actuals[1] = newValue;
			}
		});
		ca.setQualifier("new qualifier");
		assertEquals("received old value", "qualifier", actuals[0]);
		assertEquals("received new value", "new qualifier", actuals[1]);
	}
	private static void client_attribute_onDirtyChange_test(PMContext pmContext) {
		JSLogger.log("--- client_attribute_onDirtyChange ---");

		String my_property = "my_property_onDirtyChange";

		ClientAttribute ca = pmContext.clientDolphin.attribute(my_property, "qualifier", "value");

		final Boolean[] actuals = new Boolean[2];

		ca.addDirtyChangeHandler(new BooleanChangeHandler() {
			@Override
			public void handleChange(final boolean oldValue, final boolean newValue) {
				actuals[0] = oldValue;
				actuals[1] = newValue;
			}
		});
		ca.setValue("some value");
		assertEquals("old dirty false", false, actuals[0]);
		assertEquals("new dirty true", true, actuals[1]);
	}

}
