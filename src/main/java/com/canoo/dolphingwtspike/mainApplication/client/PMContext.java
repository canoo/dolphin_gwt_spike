package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.Dolphin;
import com.canoo.opendolphin.client.gwt.OnFinishedHandler;

public class PMContext {

	public static final String PM_ID = "org.opendolphin.demo.Tutorial.modelId";
	public static final String TEXT_ATTR_ID = "attrId";
	public static final String RANGE_ATTR_ID = "range";
	public static final String ECHO_COMMAND = "org.opendolphin.demo.Tutorial.echo";
	public static final String ADD_COMMAND = "org.opendolphin.demo.Tutorial.add";
	private Dolphin dolphin;

	public PMContext initialize(Dolphin dolphin) {
		this.dolphin = dolphin;

		// create named PM with attributes on the client side
		String type = null;
		dolphin.getClientDolphin().presentationModel(PM_ID, type, TEXT_ATTR_ID, RANGE_ATTR_ID);
		return this;
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
		dolphin.getClientDolphin().send(ECHO_COMMAND);
	}
	public void sendAddCommand(OnFinishedHandler handler) {
		dolphin.getClientDolphin().send(ADD_COMMAND, handler);
	}

}
