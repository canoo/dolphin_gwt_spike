
package com.canoo.opendolphin.client.gwt;

public class DolphinLoader {

	public final static native void load(String invalidateSessionUrl, DolphinStarter dolphinStarter) /*-{
		console.log("DolphinLoader.load: entered");
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
			'Dolphin', 'comm/ClientAttribute'
		], function (dolphinModule, clientAttributeModule) {
			console.log("DolphinLoader.load: in callback");
			dolphinStarter.@com.canoo.opendolphin.client.gwt.DolphinStarter::start(Lcom/canoo/opendolphin/client/js/DolphinJS;Lcom/canoo/opendolphin/client/js/ClientAttributeJS;)(dolphinModule, clientAttributeModule);
		});

		console.log("DolphinLoader.load: returning");

	}-*/;


}
