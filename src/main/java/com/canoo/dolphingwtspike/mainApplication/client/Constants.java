package com.canoo.dolphingwtspike.mainApplication.client;

public class Constants {
	public static final String DOLPHIN_URL = "http://127.0.0.1:8080/dolphin/";
	public static final String INVALIDATE_SESSION_URL = "http://127.0.0.1:8080/invalidatesession";
//	private static final String DOLPHIN_URL = "http://127.0.0.1:8080/dolphin_gwt_spike/dolphin/";
//	private static final String INVALIDATE_SESSION_URL = "http://127.0.0.1:8080/dolphin_gwt_spike/invalidatesession";

	public static String getDolphinUrl() {
		return DOLPHIN_URL;
	}

	public static String getInvalidateSessionUrl() {
		return INVALIDATE_SESSION_URL;
	}

}
