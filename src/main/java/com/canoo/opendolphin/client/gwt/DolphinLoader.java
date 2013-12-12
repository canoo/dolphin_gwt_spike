
package com.canoo.opendolphin.client.gwt;

public class DolphinLoader {

	public final static native void load(String dolphinURL, DolphinStarter dolphinStarter) /*-{
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
			@com.canoo.opendolphin.client.gwt.DolphinBaseStarter::start(Lcom/canoo/opendolphin/client/js/DolphinJS;Lcom/canoo/opendolphin/client/js/ClientAttributeJS;Ljava/lang/String;Lcom/canoo/opendolphin/client/gwt/DolphinStarter;)(dolphinModule, clientAttributeModule, dolphinURL, dolphinStarter);
		});

		console.log("DolphinLoader.load: returning");

	}-*/;


}