package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.PresentationModelJS;
import com.google.gwt.core.client.JsArray;

import java.util.ArrayList;
import java.util.List;

public class PresentationModel {
	private final PresentationModelJS pmJS;

	private final List<ClientAttribute> attributes = new ArrayList<ClientAttribute>();

	public PresentationModel(final PresentationModelJS pmJS) {
		this.pmJS = pmJS;
		JsArray<ClientAttributeJS> clientAttributesJS = pmJS.getClientAttributes();
		for (int i = 0; i < clientAttributesJS.length(); i++) {
			ClientAttributeJS clientAttributeJS = clientAttributesJS.get(i);
			attributes.add(new ClientAttribute(clientAttributeJS.getId(), clientAttributeJS));
		}
	}

	public String getPresentationModelType() {
		return pmJS.getPresentationModelType();
	}

	public List<ClientAttribute> getAttributes() {
		return attributes;
	}
}
