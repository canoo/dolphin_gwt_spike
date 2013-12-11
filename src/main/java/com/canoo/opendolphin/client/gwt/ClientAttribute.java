package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;

public class ClientAttribute {

    private final String id;
    private final ClientAttributeJS attribute;

    public ClientAttribute(ClientAttributeJS ClientAttribute, String id) {
        this.id = id;
        attribute = newAttribute(ClientAttribute, id);
    }

    public ClientAttribute(String id, ClientAttributeJS attribute) {
        this.id = id;
        this.attribute = attribute;
    }

    public String getId() {
        return id;
    }

    public ClientAttributeJS getAttribute() {
        return attribute;
    }

    public void setValue(String value) {
        setAttributeValue(getAttribute(), value);
    }

    public String getValue() {
        return getValueJS(getAttribute());
    }

    public void addValueChangedHandler(AttributeValueChangeHandler handler) {
        addAttributeValueChangeHandler(getAttribute(), handler);
    }

    private native void setAttributeValue(ClientAttributeJS attribute, String value) /*-{
        attribute.setValue(value);
    }-*/;

    private native String getValueJS(ClientAttributeJS attribute) /*-{
        return attribute.getValue();
    }-*/;

    private native ClientAttributeJS newAttribute(ClientAttributeJS ClientAttribute, String attributeId) /*-{
        return new ClientAttribute(attributeId);
    }-*/;

    private native void addAttributeValueChangeHandler(ClientAttributeJS attribute, AttributeValueChangeHandler handler) /*-{
        attribute.on("valueChange", function (data) {
            handler.@com.canoo.opendolphin.client.gwt.AttributeValueChangeHandler::handleValueChange(Ljava/lang/String;)(data.newValue);
        });
    }-*/;
}
