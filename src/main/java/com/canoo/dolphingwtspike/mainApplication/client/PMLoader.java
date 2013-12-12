package com.canoo.dolphingwtspike.mainApplication.client;

public class PMLoader {
	public void load(final PMContext pmContext) {
		pmContext.findAttribute(PMContext.TEXT_ATTR_ID).setValue("12");
		pmContext.findAttribute(PMContext.RANGE_ATTR_ID).setValue("40");
	}
}
