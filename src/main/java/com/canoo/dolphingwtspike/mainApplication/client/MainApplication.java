package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.Dolphin;
import com.canoo.opendolphin.client.js.DolphinLoaderJS;
import com.canoo.opendolphin.client.gwt.DolphinStarter;
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
		// 1: Bootstrap Dolphin:
		DolphinLoaderJS.load(Constants.getDolphinUrl(), new DolphinStarter() {
			@Override
			public void start(final Dolphin dolphin) {

				// 2: Initialize PMs:
				PMContext pmContext = new PMContext().initialize(dolphin);

				// 3: Initialize View:
				MainView view = new MainView().initialize();

				// 4: Bind view and PMs:
				new Binder().bind(view, pmContext);

				// 5: Load initial data into PMs:
				new PMLoader().load(pmContext);
//				pmContext.sendCommand(PMConstants.CMD_LOAD_INITIAL);
			}
		});

	}
}
