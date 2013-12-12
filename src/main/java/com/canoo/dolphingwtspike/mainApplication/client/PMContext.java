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
	private Dolphin dolphin;

	public void initialize(DolphinJS dolphinModule, ClientAttributeJS clientAttributeModule) {

		dolphin = new Dolphin(dolphinModule, clientAttributeModule, Constants.getDolphinUrl());
		final ClientDolphin clientDolphin = dolphin.getClientDolphin();

		// create named PM with attribute on the client side
		String type = null;
		clientDolphin.presentationModel(PM_ID, type, new ClientAttribute(clientAttributeModule, TEXT_ATTR_ID), new ClientAttribute(clientAttributeModule, RANGE_ATTR_ID));

	}

	public ClientAttribute findAttribute(String attrId) {
		return dolphin.getClientDolphin().getClientModelStore().findAttributeById(attrId);

	}
	public ClientAttribute getTextAttribute() {
       return findAttribute(PMContext.TEXT_ATTR_ID);
	}

	public ClientAttribute getRangeAttribute() {
        return findAttribute(PMContext.RANGE_ATTR_ID);
	}

	public void sendEchoCommand() {
		dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.echo");
	}
	public void sendAddCommand(OnFinishedHandler handler) {
		dolphin.getClientDolphin().send("org.opendolphin.demo.Tutorial.add", handler);
	}

}
