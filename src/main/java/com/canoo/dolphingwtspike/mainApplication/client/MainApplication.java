package com.canoo.dolphingwtspike.mainApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		TextBox textBox = new TextBox();
		textBox.getElement().setId("textInput");

		Label label = new Label("--"); label.getElement().setId("label");
//		Label helpLabel = new Label("Drag the slider to see the label being updated."); label.getElement().setId("label");

		final Button button = new Button("Click me");
		button.getElement().setId("logActionButton");
//		final Label label = new Label();

		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//					MainApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
					hello();
			}
		});

		// Assume that the host HTML has elements defined whose
		// IDs are "slot1", "slot2".  In a real app, you probably would not want
		// to hard-code IDs.  Instead, you could, for example, search for all
		// elements with a particular CSS class and replace them with widgets.
		//
		RootPanel.get("slot1").add(textBox);
		RootPanel.get("slot1").add(label);
		RootPanel.get("slot1").add(button);
//		RootPanel.get("slot2").add(label);
	}

	public static native void hello() /*-{
//		$doc.hello2('bla');
		$wnd.hello2('bla');
	}-*/;

	private static class MyAsyncCallback implements AsyncCallback<String> {
		private Label label;

		public MyAsyncCallback(Label label) {
			this.label = label;
		}

		public void onSuccess(String result) {
			label.getElement().setInnerHTML(result);
		}

		public void onFailure(Throwable throwable) {
			label.setText("Failed to receive answer from server!");
		}
	}
}
