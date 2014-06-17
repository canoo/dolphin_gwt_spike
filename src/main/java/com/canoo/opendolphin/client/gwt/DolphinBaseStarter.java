package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.OpenDolphinJS;

public class DolphinBaseStarter {
	public static void start(OpenDolphinJS openDolphinJS, String dolphinURL, DolphinStarter dolphinStarter) {
		ClientDolphin clientDolphin = new ClientDolphin(openDolphinJS.newClientDolphinJSInstance(dolphinURL, true, 0)); //todo (Sven 04.06.14): pass in 'reset' and 'slack'
		dolphinStarter.start(clientDolphin);

	}
}
