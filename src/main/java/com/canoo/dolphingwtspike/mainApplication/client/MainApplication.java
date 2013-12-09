package com.canoo.dolphingwtspike.mainApplication.client;

import com.canoo.opendolphin.client.DolphinAPI;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MainApplication implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
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
		RootPanel.get("slot1").add(textBox);
		RootPanel.get("slot1").add(label);
		RootPanel.get("slot1").add(serverModificationButton);

		RootPanel.get("slot1").add(helpLabel);
		RootPanel.get("slot1").add(range);
		RootPanel.get("slot1").add(rangeLabel);

		RootPanel.get("slot1").add(help2Label);
		RootPanel.get("slot1").add(addServerDataButton);
		RootPanel.get("slot1").add(listDiv);

//		Canoo._info("hallo");

		DolphinAPI.initializeConfig();
//		DolphinAPI.newDolphin("http://127.0.0.1:8888/dolphin/", DolphinAPI.bla());
		DolphinAPI.newDolphin("http://127.0.0.1:8888/dolphin/",
			DolphinAPI.clientAttribute(
				DolphinAPI.newAttribute("attrId")
			)
		);


//		System.out.println("dolphinJS = " + dolphinJS);
//		Dolphin.newDolphin(dolphinJS);
	}

}
