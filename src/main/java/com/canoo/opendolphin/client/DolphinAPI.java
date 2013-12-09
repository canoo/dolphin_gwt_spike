package com.canoo.opendolphin.client;

import com.google.gwt.core.client.JavaScriptObject;

public class DolphinAPI {

	public final static native JavaScriptObject newJavaScriptObject() /*-{
		return {}
	}-*/;

	public final static native void initializeConfig() /*-{
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



	}-*/;

	public final static native JavaScriptObject newDolphin(String url, JavaScriptObject next) /*-{
		console.log("DolphinAPI.newDolphin: entered");
		$wnd.require([
			'Dolphin', 'comm/ClientAttribute', 'comm/HttpSession'
		], function (Dolphin, ClientAttribute, HttpSession) {
			console.log("DolphinAPI.newDolphin: in callback");
			var httpSession = new HttpSession('http://127.0.0.1:8888/invalidatesession');
			httpSession.invalidateSession();

			console.log("DolphinAPI.newDolphin: creating dolphin");
			var dolphin = new Dolphin('http://127.0.0.1:8888/dolphin/');
			console.log("DOLPHIN: " + dolphin);
			console.log("next: " + next);
			next(dolphin);

		});

		console.log("DolphinAPI.newDolphin: returning");

	}-*/;

	public final static native JavaScriptObject bla() /*-{
		return function (dolphin) {
			console.log("DolphinAPI.bla, dolphin: " + dolphin);
		};

	}-*/;
	public final static native JavaScriptObject clientAttribute(JavaScriptObject next) /*-{
		console.log("DolphinAPI.clientAttribute: entered");
		var result = function () {
			$wnd.require([
				'comm/ClientAttribute'
			], function (ClientAttribute) {
				console.log("DolphinAPI.clientAttribute: in callback");
				next(ClientAttribute);
			});
		};
		console.log("DolphinAPI.clientAttribute: returning");
		return result;
	}-*/;
	public final static native JavaScriptObject newAttribute(String attributeId) /*-{
		return function (ClientAttribute) {
			console.log("ccc");
			// create named PM with attribute on the client side
			var textAttribute  = new ClientAttribute(attributeId);
		};

	}-*/;
}
