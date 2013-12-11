package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientAttribute {

    private final String id;
    private final JavaScriptObject attribute;

    public ClientAttribute(JavaScriptObject ClientAttribute, String id) {
        this.id = id;
        attribute = DolphinMain2.newAttribute(ClientAttribute, id);
    }

    public String getId() {
        return id;
    }

    public JavaScriptObject getAttribute() {
        return attribute;
    }
}
