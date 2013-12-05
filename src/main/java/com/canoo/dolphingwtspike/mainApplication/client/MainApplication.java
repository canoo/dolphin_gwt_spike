package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.OpenDolphin;
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
		final Button button = new Button("Click me");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//					MainApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
//				hello();
//				OpenDolphin._info("canoo");
				OpenDolphin._info2("canoo", "other");
			}
		});

		TextBox textBox = new TextBox(); textBox.getElement().setId("textInput");

		Label label = new Label("--"); label.getElement().setId("label");
		final Button serverModificationButton = new Button("Server Modification"); serverModificationButton.getElement().setId("logActionButton");
		Label helpLabel = new Label("Drag the slider to see the label being updated.");
		TextBox range = new TextBox(); range.getElement().setAttribute("type", "range"); range.getElement().setId("range");
		Label rangeLabel = new Label("--"); rangeLabel.getElement().setId("rangeLabel");

		Label help2Label = new Label("Click to get new content from the server side, bound to a list.");
		final Button addServerDataButton = new Button("Add Server Data"); addServerDataButton.getElement().setId("addButton");

		VerticalPanel listDiv = new VerticalPanel(); listDiv.getElement().setId("list");


		// Assume that the host HTML has elements defined whose
		// IDs are "slot1", "slot2".  In a real app, you probably would not want
		// to hard-code IDs.  Instead, you could, for example, search for all
		// elements with a particular CSS class and replace them with widgets.
		//
		RootPanel.get("slot1").add(button);

		RootPanel.get("slot1").add(textBox);
		RootPanel.get("slot1").add(label);
		RootPanel.get("slot1").add(serverModificationButton);

		RootPanel.get("slot1").add(helpLabel);
		RootPanel.get("slot1").add(range);
		RootPanel.get("slot1").add(rangeLabel);

		RootPanel.get("slot1").add(help2Label);
		RootPanel.get("slot1").add(addServerDataButton);
		RootPanel.get("slot1").add(listDiv);
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
