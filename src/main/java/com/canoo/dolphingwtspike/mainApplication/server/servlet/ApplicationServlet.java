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
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==> com.canoo.dolphingwtspike.mainApplication.server.servlet.ApplicationServlet.doGet");
	}

	@Override
	protected void registerApplicationActions(ServerDolphin serverDolphin) {
		serverDolphin.registerDefaultActions();
		serverDolphin.register(new TutorialAction());
	}
}
