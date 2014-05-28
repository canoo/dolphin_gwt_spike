package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.*;

public class PMContext {

	private ClientDolphin clientDolphin;
	private PresentationModel pm = null;

	public PMContext initialize(ClientDolphin clientDolphin) {
		this.clientDolphin = clientDolphin;

		// create named PM with attributes on the client side
		String type = null;
		pm = clientDolphin.presentationModel(PMConstants.PM_ID, type, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);
		return this;
	}

	public ClientAttribute findAttribute(Long attrId) {
		return clientDolphin.getClientModelStore().findAttributeById(attrId);

	}
	public ClientAttribute getTextAttribute() {
       return pm.getAt(PMConstants.TEXT_ATTR_ID);
	}

	public ClientAttribute getRangeAttribute() {
		return pm.getAt(PMConstants.RANGE_ATTR_ID);
	}

	public void sendCommand(String command) {
		clientDolphin.send(command);
	}
	public void sendCommand(String command, OnFinishedHandler handler) {
		clientDolphin.send(command, handler);
	}

}
