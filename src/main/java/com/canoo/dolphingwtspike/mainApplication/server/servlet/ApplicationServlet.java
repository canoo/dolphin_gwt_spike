package com.canoo.dolphingwtspike.mainApplication.server.servlet;

import org.opendolphin.core.server.ServerDolphin;
//import org.opendolphin.example.hello.ApplicationRegistrar;
import org.opendolphin.server.adapter.DolphinServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * For real server mode, this servlet acts as entry point for all communication.
 */
public class ApplicationServlet extends DolphinServlet {

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Cache-Control", "no-cache"); // for development
		super.doPost(req, resp);
	}

	@Override
	protected void registerApplicationActions(ServerDolphin serverDolphin) {
		serverDolphin.registerDefaultActions();
		serverDolphin.register(new TutorialAction());
	}
}
