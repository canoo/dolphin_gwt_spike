package com.canoo.opendolphin.client.gwt;

public enum ModelStoreChangeEventType {

	ADDED("ADDED"), REMOVED("REMOVED");

	private final String typeValue;

	ModelStoreChangeEventType(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public static ModelStoreChangeEventType fromTypeValue(String typeValue) {
		return typeValue.equals(ADDED.getTypeValue()) ? ADDED : typeValue.equals(REMOVED.getTypeValue()) ? REMOVED : null;
	}
}
