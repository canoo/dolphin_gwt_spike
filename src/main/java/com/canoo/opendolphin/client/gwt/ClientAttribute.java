package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;

public class ClientAttribute {

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
	public final  String getPropertyName() {
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

    public void addValueChangedHandler(AttributeValueChangeHandler handler) {
        clientAttributeJS.addAttributeValueChangeHandler( handler);
    }

}
