package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import com.canoo.opendolphin.client.gwt.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {


    public static final String DOLPHIN_URL = GWT.getHostPageBaseURL() + "dolphin/";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initialize();
	}

	public void initialize() {
		// 1: Bootstrap Dolphin:
		DolphinErrorHandler errorHandler = new DolphinErrorHandler() {
			@Override
			public void handleError(DolphinErrorEvent evt) {
				Window.alert("Dolphin received an error from the server: kind: '" + evt.kind + "', url: '" + evt.url + "', httpStatus: '" + evt.httpStatus + "', message: '" + evt.message + "\n\nReloading Page");
				Window.Location.reload();
			}

		};
		ClientDolphin clientDolphin = OpenDolphin.makeDolphin()
			.url(DOLPHIN_URL)
			.reset(true)
			.errorHandler(errorHandler)
			.build();

		// 2: Initialize PMs:
		PMContext pmContext = new PMContext().initialize(clientDolphin);

		// 3: Initialize View:
		MainView view = new MainView().initialize();

		// 4: Bind view and PMs:
		new Binder().bind(view, pmContext);

		// 5: Load initial data into PMs:
		new PMLoader().load(pmContext);
		pmContext.clientDolphin.send(PMConstants.CMD_LOAD_INITIAL);

	}
}
