package com.canoo.dolphingwtspike.mainApplication.client;

import com.google.gwt.core.client.GWT;

public class Constants {
	public static final String DOLPHIN_URL = "http://127.0.0.1:8080/dolphin/";
    public static final String DOLPHIN_URL_PROD = "http://127.0.0.1:8080/dolphin_gwt_spike/dolphin/";

	public static final String INVALIDATE_SESSION_URL = "http://127.0.0.1:8080/invalidatesession";
	public static final String INVALIDATE_SESSION_URL_PROD = "http://127.0.0.1:8080/dolphin_gwt_spike/invalidatesession";

	public static String getDolphinUrl() {

        if (GWT.isProdMode()){
            return DOLPHIN_URL_PROD;
        }else {
            return DOLPHIN_URL;
        }
	}

	public static String getInvalidateSessionUrl() {
        if (GWT.isProdMode()){
            return INVALIDATE_SESSION_URL_PROD;
        }else {
            return INVALIDATE_SESSION_URL;
        }
	}

}
