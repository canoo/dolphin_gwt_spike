package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.ClientPresentationModelJS;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.List;

public class ClientPresentationModel {
	final ClientPresentationModelJS pmJS;

	private final List<ClientAttribute> attributes = new ArrayList<ClientAttribute>();

	public ClientPresentationModel(final ClientPresentationModelJS pmJS) {
		this.pmJS = pmJS;
		JsArray<ClientAttributeJS> clientAttributesJS = pmJS.getClientAttributes();
		for (int i = 0; i < clientAttributesJS.length(); i++) {
			ClientAttributeJS clientAttributeJS = clientAttributesJS.get(i);
			attributes.add(new ClientAttribute(clientAttributeJS));
		}
	}

	public String getId() {
		return pmJS.getId();
	}

	public String getPresentationModelType() {
		return pmJS.getPresentationModelType();
	}

	public List<ClientAttribute> getAttributes() {
		return attributes;
	}

	public ClientAttribute getAt(String propertyName) {
		ClientAttributeJS attributeJS = pmJS.getAt(propertyName);
		return attributeJS == null ? null : new ClientAttribute(attributeJS); // TODO: is it OK to return a new instance of ClientAttribute? pro: reuse of open-dolphin code, contra: new instance
	}

	public ClientAttribute getAt(String propertyName, String tag) {
		ClientAttributeJS attributeJS = pmJS.getAt(propertyName, tag);
		return new ClientAttribute(attributeJS); // TODO: see comment in getAt(String propertyName)
	}
	public ClientAttribute findAttributeByPropertyName(String propertyName) {
		return getAt(propertyName);
	}
	public ClientAttribute findAttributeByPropertyNameAndTag(String propertyName, String tag) {
		return getAt(propertyName, tag);
	}
	public ClientAttribute findAttributeById(String id) {
		ClientAttributeJS attributeJS = pmJS.findAttributeById(id);
		return new ClientAttribute(attributeJS); // TODO: see comment in getAt(String propertyName)
	}
	public ClientAttribute findAttributeByQualifier(String qualifier) {
		ClientAttributeJS attributeJS = pmJS.findAttributeByQualifier(qualifier);
		return new ClientAttribute(attributeJS); // TODO: see comment in getAt(String propertyName)
	}
	public List<ClientAttribute> findAllAttributesByPropertyName(String propertyName) {
		JsArray<ClientAttributeJS> jsAttributes = pmJS.findAllAttributesByPropertyName(propertyName);
		return clientAttributesFromJSAttributes(jsAttributes);
	}
	public boolean isDirty() {
		return pmJS.isDirty();
	}
	public void rebase() {
		pmJS.rebase();
	}

	public void syncWith(ClientPresentationModel pm) {
		pmJS.syncWith(pm.pmJS);
	}

	public void addDirtyChangeHandler(BooleanChangeHandler booleanChangeHandler) {
		pmJS.addDirtyChangeHandler(booleanChangeHandler);
	}
	public void addInvalidationHandler(PresentationModelInvalidationHandler handler) {
		pmJS.addInvalidationHandler(new PresentationModelInvalidationHandlerAdapter(handler));
	}

	// === private ===

	private List<ClientAttribute> clientAttributesFromJSAttributes(JsArray<ClientAttributeJS> jsAttributes) {
		final List<ClientAttribute> result = new ArrayList<ClientAttribute>();
		for (int i = 0; i < jsAttributes.length(); i++) {
			ClientAttributeJS clientAttributeJS = jsAttributes.get(i);
			result.add(new ClientAttribute(clientAttributeJS));
		}
		return result;
	}

}
