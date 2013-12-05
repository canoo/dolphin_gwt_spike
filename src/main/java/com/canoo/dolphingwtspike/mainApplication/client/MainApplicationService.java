package com.canoo.dolphingwtspike.mainApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MainApplicationService")
public interface MainApplicationService extends RemoteService {
	// Sample interface method of remote interface
	String getMessage(String msg);

	/**
	 * Utility/Convenience class.
	 * Use MainApplicationService.App.getInstance() to access static instance of MainApplicationServiceAsync
	 */
	public static class App {
		private static MainApplicationServiceAsync ourInstance = GWT.create(MainApplicationService.class);

		public static synchronized MainApplicationServiceAsync getInstance() {
			return ourInstance;
		}
	}
}
