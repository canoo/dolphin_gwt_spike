package com.canoo.dolphingwtspike.mainApplication.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * For real server mode, this servlet acts as entry point for all communication.
 */
public class InvalidateSessionServlet extends HttpServlet {

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		System.out.println("HttpSession invalidated");
	}

}
