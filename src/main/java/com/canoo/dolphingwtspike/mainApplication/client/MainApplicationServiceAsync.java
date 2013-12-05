package com.canoo.dolphingwtspike.mainApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MainApplicationServiceAsync {
	void getMessage(String msg, AsyncCallback<String> async);
}
