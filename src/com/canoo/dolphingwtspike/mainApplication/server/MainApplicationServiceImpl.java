package com.canoo.dolphingwtspike.mainApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.canoo.dolphingwtspike.mainApplication.client.MainApplicationService;

public class MainApplicationServiceImpl extends RemoteServiceServlet implements MainApplicationService {
	// Implementation of sample interface method
	public String getMessage(String msg) {
		return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
	}
}