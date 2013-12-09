package com.canoo.opendolphin.client;

public class DolphinMain {

	public final static native void initialize() /*-{


	}-*/;

	public final static native void bind() /*-{


	}-*/;

	public final static native void runDolphin() /*-{
		initialize();
		bind();
	}-*/;
}
