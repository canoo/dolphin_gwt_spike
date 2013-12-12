package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.Dolphin;
import com.canoo.opendolphin.client.gwt.DolphinLoader;
import com.canoo.opendolphin.client.gwt.DolphinStarter;
import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.DolphinJS;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initialize();
	}

	public void initialize() {
		DolphinLoader.load(Constants.getInvalidateSessionUrl(), new DolphinStarter() {
			@Override
			public void start(final DolphinJS dolphinModule, final ClientAttributeJS clientAttributeModule) {
				// 1: Initialize Dolphin:
				Dolphin dolphin = new Dolphin(dolphinModule, clientAttributeModule, Constants.getDolphinUrl());

				// 2: Initialize PMs:
				PMContext pmContext = new PMContext().initialize(dolphin);

				// 3: Initialize View:
				MainView view = new MainView().initialize();

				// 4: Bind view and PMs:
				new Binder().bind(view, pmContext);

				// 5: Load initial data into PMs:
				new PMLoader().load(pmContext);
			}
		});

	}
}
