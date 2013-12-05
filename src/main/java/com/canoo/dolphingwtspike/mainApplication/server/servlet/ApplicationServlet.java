package com.canoo.dolphingwtspike.mainApplication.server.servlet;

import org.opendolphin.core.server.ServerDolphin;
//import org.opendolphin.example.hello.ApplicationRegistrar;
import org.opendolphin.server.adapter.DolphinServlet;

/**
 * For real server mode, this servlet acts as entry point for all communication.
 */
public class ApplicationServlet extends DolphinServlet{
	@Override
	protected void registerApplicationActions(ServerDolphin serverDolphin) {
//		new ApplicationRegistrar().registerActions(serverDolphin);
	}
}
