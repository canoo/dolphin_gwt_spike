package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.ClientPresentationModelJS;

public class ClientAttribute {

	// todo (Sven 01.07.14): should the following routines be part of the public API? :
	// public void setBaseValue(Object baseValue)

	private final ClientAttributeJS clientAttributeJS;

    public ClientAttribute(ClientAttributeJS clientAttributeJS) {
        this.clientAttributeJS = clientAttributeJS;
    }

	public ClientAttributeJS getClientAttributeJS() {
		return clientAttributeJS;
	}

	public ClientAttribute copy() {
        return new ClientAttribute( clientAttributeJS.copy() );
    }

	public String getId() {
        return clientAttributeJS.getId();
    }
	public final String getPropertyName() {
		return clientAttributeJS.getPropertyName();
	}
	public final  String getBaseValue() {
		return clientAttributeJS.getBaseValue();
	}
	public final  String getQualifier() {
		return clientAttributeJS.getQualifier();
	}
	public final  String getTag() {
		return clientAttributeJS.getTag();
	}
	public String getValue() {
		return clientAttributeJS.getValue();
	}

    public void setValue(String value) {
        clientAttributeJS.setValue(value);
    }

	public boolean isDirty() {
		return clientAttributeJS.isDirty();
	}
	public void rebase() {
		clientAttributeJS.rebase();
	}
	public void reset() {
		clientAttributeJS.reset();
	}
	public ClientPresentationModel getPresentationModel() {
		ClientPresentationModelJS pmJS = clientAttributeJS.getPresentationModel();
		return pmJS == null ? null : new ClientPresentationModel(pmJS);
	}
	public void setPresentationModel(ClientPresentationModel pm) {
		clientAttributeJS.setPresentationModel(pm.pmJS);
	}
	public void setQualifier(String qualifier) {
		clientAttributeJS.setQualifier(qualifier);
	}
    public void addValueChangedHandler(AttributeChangeHandler handler) {
        clientAttributeJS.addAttributeValueChangeHandler(handler);
    }
    public void addQualifierChangeHandler(AttributeChangeHandler handler) {
        clientAttributeJS.addQualifierChangeHandler(handler);
    }
    public void addDirtyChangeHandler(BooleanChangeHandler handler) {
        clientAttributeJS.addDirtyChangeHandler(handler);
    }

}
