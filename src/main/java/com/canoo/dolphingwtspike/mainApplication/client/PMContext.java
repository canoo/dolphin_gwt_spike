package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.ClientAttribute;
import com.canoo.opendolphin.client.gwt.Dolphin;
import com.canoo.opendolphin.client.gwt.OnFinishedHandler;
import com.canoo.opendolphin.client.gwt.PresentationModel;

public class PMContext {

	private Dolphin dolphin;
	private PresentationModel pm = null;

	public PMContext initialize(Dolphin dolphin) {
		this.dolphin = dolphin;

		// create named PM with attributes on the client side
		String type = null;
		pm = dolphin.getClientDolphin().presentationModel(PMConstants.PM_ID, type, PMConstants.TEXT_ATTR_ID, PMConstants.RANGE_ATTR_ID);
		return this;
	}

	public ClientAttribute findAttribute(Long attrId) {
		return dolphin.getClientDolphin().getClientModelStore().findAttributeById(attrId);

	}
	public ClientAttribute getTextAttribute() {
       return pm.getAt(PMConstants.TEXT_ATTR_ID);
	}

	public ClientAttribute getRangeAttribute() {
		return pm.getAt(PMConstants.RANGE_ATTR_ID);
	}

	public void sendCommand(String command) {
		dolphin.getClientDolphin().send(command);
	}
	public void sendCommand(String command, OnFinishedHandler handler) {
		dolphin.getClientDolphin().send(command, handler);
	}

}
