package com.canoo.opendolphin.client.gwt;

import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.DolphinJS;

public class DolphinBaseStarter {
	public static void start(DolphinJS dolphinModule, ClientAttributeJS clientAttributeModule, String dolphinURL, DolphinStarter dolphinStarter) {
		Dolphin dolphin = new Dolphin(dolphinModule, clientAttributeModule, dolphinURL);
		dolphinStarter.start(dolphin);

	}
}
