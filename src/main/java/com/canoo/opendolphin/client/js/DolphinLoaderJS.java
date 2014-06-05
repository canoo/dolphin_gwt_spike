
package com.canoo.opendolphin.client.js;

import com.canoo.opendolphin.client.gwt.DolphinStarter;

public class DolphinLoaderJS {

	public final static native void load(String dolphinURL, DolphinStarter dolphinStarter) /*-{
		$wnd.require.config({
			baseUrl: 'com.canoo.opendolphin.OpenDolphin'
		});

		$wnd.require([
			'comm/OpenDolphin'
		], function (OpenDolphin_js) {
			@com.canoo.opendolphin.client.gwt.DolphinBaseStarter::start(Lcom/canoo/opendolphin/client/js/OpenDolphinJS;Ljava/lang/String;Lcom/canoo/opendolphin/client/gwt/DolphinStarter;)(OpenDolphin_js, dolphinURL, dolphinStarter);
		});

	}-*/;


}
