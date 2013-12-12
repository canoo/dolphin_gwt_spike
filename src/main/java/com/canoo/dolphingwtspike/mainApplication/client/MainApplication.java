package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.gwt.DolphinLoader;
import com.canoo.opendolphin.client.gwt.DolphinStarter;
import com.canoo.opendolphin.client.js.ClientAttributeJS;
import com.canoo.opendolphin.client.js.DolphinJS;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;

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
			public void start(final JavaScriptObject Dolphin, final JavaScriptObject ClientAttribute) {
				PMContext pmContext = new PMContext();
				pmContext.initialize((DolphinJS) Dolphin, (ClientAttributeJS) ClientAttribute);

				MainView view = new MainView();
				view.initialize();

				new Binder().bind(view, pmContext);
				new PMLoader().load(pmContext);
			}
		});

	}
}
