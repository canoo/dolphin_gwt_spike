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
        DolphinMain2.setAttributeValue(getAttribute(), value);
    }

    private native JavaScriptObject newAttribute(JavaScriptObject ClientAttribute, String attributeId) /*-{
        return new ClientAttribute(attributeId);
    }-*/;
}
