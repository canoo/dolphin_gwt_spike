package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.PresentationModelJS;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.List;

// todo: rename as ClientPresentationModel
public class PresentationModel {
	private final PresentationModelJS pmJS;

	private final List<ClientAttribute> attributes = new ArrayList<ClientAttribute>();

	public PresentationModel(final PresentationModelJS pmJS) {
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
		for (ClientAttribute attribute : attributes) {
			if (propertyName.equals(attribute.getPropertyName())) return attribute;
		}
		return null;
	}
	public ClientAttribute getAt(String propertyName, String tag) {
		for (ClientAttribute attribute : attributes) {
			if (propertyName.equals(attribute.getPropertyName()) && tag.equals(attribute.getTag())) return attribute;
		}
		return null;
	}
}
