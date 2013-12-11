
package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class DolphinMain2 {

	public final static native JavaScriptObject boot(String invalidateSessionUrl, DolphinStarter dolphinStarter) /*-{
		console.log("DolphinMain2.boot: entered");
		$wnd.require.config({
			baseUrl: 'com.canoo.opendolphin.OpenDolphin',
			paths: {
				jquery : 'jquery'
			},

			shim: {
				'jquery': {
					exports: '$'
				}
			},

			map : {
				'*': {
					$ : 'jquery'
				}
			}
		});

		$wnd.require([
			'Dolphin', 'comm/ClientAttribute', 'comm/HttpSession'
		], function (Dolphin, ClientAttribute, HttpSession) {
			console.log("DolphinMain2.start: in callback");
			var httpSession = new HttpSession(invalidateSessionUrl);
			httpSession.invalidateSession();
			dolphinStarter.@com.canoo.opendolphin.client.DolphinStarter::start(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(Dolphin, ClientAttribute);
		});

		console.log("DolphinMain2.boot: returning");

	}-*/;


}
