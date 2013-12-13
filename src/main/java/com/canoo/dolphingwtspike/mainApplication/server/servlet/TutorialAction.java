package com.canoo.dolphingwtspike.mainApplication.server.servlet;

import com.canoo.dolphingwtspike.mainApplication.shared.PMConstants;
import org.opendolphin.core.comm.Command;
import org.opendolphin.core.comm.ValueChangedCommand;
import org.opendolphin.core.server.DTO;
import org.opendolphin.core.server.ServerAttribute;
import org.opendolphin.core.server.ServerPresentationModel;
import org.opendolphin.core.server.Slot;
import org.opendolphin.core.server.action.DolphinServerAction;
import org.opendolphin.core.server.comm.ActionRegistry;
import org.opendolphin.core.server.comm.CommandHandler;

import java.util.List;

public class TutorialAction extends DolphinServerAction {

	private int count = 0;

	public void registerIn(ActionRegistry actionRegistry) {

        actionRegistry.register(PMConstants.CMD_ECHO, new CommandHandler<Command>() {
            public void handleCommand(Command command, List<Command> response) {
                final ServerPresentationModel presentationModel = getServerDolphin().getAt(PMConstants.PM_ID);
                final ServerAttribute attribute = presentationModel.getAt(PMConstants.TEXT_ATTR_ID);
                TutorialAction.this.changeValue(attribute, "Server: " + attribute.getValue());
            }
        });

		actionRegistry.register(ValueChangedCommand.class, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				System.out.println( command);
			}
		});

		actionRegistry.register(PMConstants.CMD_ADD, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				count++;
				presentationModel("weather." + count, "weather", new DTO(
					new Slot("temperature", String.valueOf((int) (Math.random() * 100)), "weather." + count + ".temperature"),
					new Slot("humidity",    String.valueOf((int) (Math.random() * 100)), "weather." + count + ".humidity")
				));
			}
		});
		actionRegistry.register(PMConstants.CMD_LOAD_INITIAL, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {
				System.out.println("in CMD_LOAD_INITIAL command");
				final ServerPresentationModel presentationModel = getServerDolphin().getAt(PMConstants.PM_ID);
				presentationModel.getAt(PMConstants.TEXT_ATTR_ID).setValue("12");
				presentationModel.getAt(PMConstants.RANGE_ATTR_ID).setValue("40");
			}
		});
	}

}
