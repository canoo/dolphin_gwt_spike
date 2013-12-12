package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;

public class ClientAttribute {

    private final ClientAttributeJS clientAttributeJS;

    public ClientAttribute(ClientAttributeJS clientAttributeJS) {
        this.clientAttributeJS = clientAttributeJS;
    }

    public Long getId() {
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
        setAttributeValue(getClientAttributeJS(), value);
    }


    public void addValueChangedHandler(AttributeValueChangeHandler handler) {
        addAttributeValueChangeHandler(getClientAttributeJS(), handler);
    }

	private ClientAttributeJS getClientAttributeJS() {
		return clientAttributeJS;
	}

	// --- Javascript ---

    private native void setAttributeValue(ClientAttributeJS attribute, String value) /*-{
        attribute.setValue(value);
    }-*/;

    private native String getValueJS(ClientAttributeJS attribute) /*-{
        return attribute.getValue();
    }-*/;

    static native ClientAttributeJS newClientAttributeJS(ClientAttributeJS clientAttributeModule, String propertyName) /*-{
        return new clientAttributeModule(propertyName);
    }-*/;

    private native void addAttributeValueChangeHandler(ClientAttributeJS attribute, AttributeValueChangeHandler handler) /*-{
        attribute.on("valueChange", function (data) {
            handler.@com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler::handleValueChange(Ljava/lang/String;)(data.newValue);
        });
    }-*/;
}
