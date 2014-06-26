package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.js.JSLogger;

public class TestHelper {
	public static void assertEquals(String message, Object expected, Object actual) {
		boolean condition = (expected == null && actual == null) || (expected != null && expected.equals(actual)) ;
		if (condition) {
			JSLogger.log("OK: " + message);
		}
		else {
			String exp = expected == null ? "null" : expected.toString();
			String act = actual == null ? "null" : actual.toString();
			JSLogger.log("NOK: " + message + ",  expected: " + exp + ", actual: " + act);
		}
	}

	public static void assertEqualsSilent(String message, Object expected, Object actual) {
		boolean condition = (expected == null && actual == null) || expected.equals(actual);
		if (!condition) {
			JSLogger.log("NOK: " + message + ",  expected: " + expected.toString() + ", actual: " + actual.toString());
		}
	}

	public static void assertNull(String message, Object obj) {
		JSLogger.log((obj == null ? "OK" : "NOK") + ": " + message );
	}

	public static void assertNotNull(String message, Object obj) {
		JSLogger.log((obj != null ? "OK" : "NOK") + ": " + message );
	}

	public static void assertTrue(String message, Boolean condition) {
		JSLogger.log((condition ? "OK" : "NOK") + ": " + message );
	}

	public static void assertTrueSilent(String message, Boolean condition) {
		if (!condition) {
			JSLogger.log("NOK: " + message);
		}
	}
}
