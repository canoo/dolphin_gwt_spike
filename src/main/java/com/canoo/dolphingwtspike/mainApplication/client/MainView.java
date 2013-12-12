package com.canoo.dolphingwtspike.mainApplication.client;

import com.google.gwt.user.client.ui.*;

public class MainView {
	Label label;
	TextBox textBox;
	TextBox range;
	Label rangeLabel;
	VerticalPanel listDiv;
	private Button serverModificationButton;
	private Label helpLabel;
	private Button addServerDataButton;

	public void initialize() {
		textBox = new TextBox();
		label = new Label("--");
		serverModificationButton = new Button("Server Modification");
		helpLabel = new Label("Drag the slider to see the label being updated.");
		range = new TextBox(); range.getElement().setAttribute("type", "range");
		rangeLabel = new Label();
		listDiv = new VerticalPanel();

		Label help2Label = new Label("Click to get new content from the server side, bound to a list.");
		addServerDataButton = new Button("Add Server Data");

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
	}

	public Label getLabel() {
		return label;
	}

	public TextBox getTextBox() {
		return textBox;
	}

	public TextBox getRange() {
		return range;
	}

	public Label getRangeLabel() {
		return rangeLabel;
	}

	public VerticalPanel getListDiv() {
		return listDiv;
	}

	public Button getServerModificationButton() {
		return serverModificationButton;
	}

	public Button getAddServerDataButton() {
		return addServerDataButton;
	}
}
