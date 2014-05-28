package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.OpenDolphinJS;

public class DolphinBaseStarter {
	public static void start(OpenDolphinJS openDolphinJS, String dolphinURL, DolphinStarter dolphinStarter) {
		ClientDolphin clientDolphin = new ClientDolphin(openDolphinJS.getClientDolphinJS());
		dolphinStarter.start(clientDolphin);

	}
}
