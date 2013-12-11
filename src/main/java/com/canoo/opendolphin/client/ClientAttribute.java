package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientAttribute {

    private final String id;
    private final JavaScriptObject attribute;

    public ClientAttribute(JavaScriptObject ClientAttribute, String id) {
        this.id = id;
        attribute = newAttribute(ClientAttribute, id);
    }

    public String getId() {
        return id;
    }

    public JavaScriptObject getAttribute() {
        return attribute;
    }

    public void setValue(String value) {
        setAttributeValue(getAttribute(), value);
    }

    public void addValueChangedHandler(AttributeValueChangeHandler handler) {
        addAttributeValueChangeHandler(getAttribute(), handler);
    }

    private native JavaScriptObject setAttributeValue(JavaScriptObject attribute, String value) /*-{
        attribute.setValue(value);
    }-*/;

    private native JavaScriptObject newAttribute(JavaScriptObject ClientAttribute, String attributeId) /*-{
        return new ClientAttribute(attributeId);
    }-*/;

    private native JavaScriptObject addAttributeValueChangeHandler(JavaScriptObject attribute, AttributeValueChangeHandler handler) /*-{
        attribute.on("valueChange", function (data) {
            handler.@com.canoo.opendolphin.client.AttributeValueChangeHandler::handleValueChange(Ljava/lang/String;)(data.newValue);
        });
    }-*/;
}
