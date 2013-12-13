package com.canoo.opendolphin.client.js;

import com.google.gwt.core.client.JavaScriptObject;

public class ClientDolphinJS extends JavaScriptObject
{

	protected ClientDolphinJS() {
	}

	public final native void send(String commandName) /*-{
		this.send(commandName);
	}-*/;

	public final native void send(String commandName, OnFinishedHandlerJS handler) /*-{
		this.send(commandName, function (pms) {
			handler.@com.canoo.opendolphin.client.js.OnFinishedHandlerJS::handlePresentationModels(Lcom/google/gwt/core/client/JsArray;)(pms)
		});
	}-*/;

	public final native ClientModelStoreJS getClientModelStoreJS() /*-{
		return this.getClientModelStore();
	}-*/;
	public final native void setClientModelStoreJS(ClientModelStoreJS clientModelStoreJS) /*-{
		return this.setClientModelStore(clientModelStoreJS);
	}-*/;

}

