package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.JSLogger;
import com.canoo.opendolphin.client.js.PresentationModelJS;

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
	public void rebase() {
		clientAttributeJS.rebase();
	}
	public void reset() {
		clientAttributeJS.reset();
	}
	public PresentationModel getPresentationModel() {
		PresentationModelJS pmJS = clientAttributeJS.getPresentationModel();
		return pmJS == null ? null : new PresentationModel(pmJS);
	}
	public void setPresentationModel(PresentationModel pm) {
		clientAttributeJS.setPresentationModel(pm.pmJS);
	}
    public void addValueChangedHandler(AttributeValueChangeHandler handler) {
        clientAttributeJS.addAttributeValueChangeHandler( handler);
    }

}
