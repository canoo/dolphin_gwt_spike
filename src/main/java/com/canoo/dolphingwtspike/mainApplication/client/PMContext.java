package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.ClientDolphin;
import com.canoo.opendolphin.client.gwt.Dolphin;
import com.canoo.opendolphin.client.gwt.OnFinishedHandler;
import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.DolphinJS;

public class PMContext {

	public static final String PM_ID = "org.opendolphin.demo.Tutorial.modelId";
	public static final String TEXT_ATTR_ID = "attrId";
	public static final String RANGE_ATTR_ID = "range";
	private ClientAttribute textAttribute;
	private ClientAttribute rangeAttribute;
	private Dolphin dolphin;

	public void initialize(DolphinJS DolphinJS, ClientAttributeJS ClientAttributeJS) {

		dolphin = new Dolphin(DolphinJS, Constants.getDolphinUrl());
		final ClientDolphin clientDolphin = dolphin.getClientDolphin();

		textAttribute = new ClientAttribute(ClientAttributeJS, TEXT_ATTR_ID);
		rangeAttribute = new ClientAttribute(ClientAttributeJS, RANGE_ATTR_ID);

		// create named PM with attribute on the client side
		String type = null;
		clientDolphin.presentationModel(PM_ID, type, textAttribute, rangeAttribute);

	}

	public ClientAttribute findAttribute(String attrId) {
		return dolphin.getClientDolphin().getClientModelStore().findAttributeById(attrId);

	}
	public ClientAttribute getTextAttribute() {
		return textAttribute;
	}

	public ClientAttribute getRangeAttribute() {
		return rangeAttribute;
	}

	public void sendEchoCommand() {
		dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.echo");
	}
	public void sendAddCommand(OnFinishedHandler handler) {
		dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.add", handler);
	}

}
