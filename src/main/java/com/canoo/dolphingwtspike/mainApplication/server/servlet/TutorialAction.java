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

import java.util.Arrays;
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

				final ServerPresentationModel presentationModel = getServerDolphin().getAt(PMConstants.PM_ID);

				presentationModel.getAt(PMConstants.TEXT_ATTR_ID).setValue("12");
				presentationModel.getAt(PMConstants.RANGE_ATTR_ID).setValue("40");
			}
		});

		actionRegistry.register(PMConstants.CMD_CREATE_PM, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {

				DTO dto = new DTO(Arrays.asList(new Slot("p1", "v1"), new Slot("p2", "v2") ));
				final ServerPresentationModel presentationModel = getServerDolphin().presentationModel(PMConstants.PM_SERVER_SIDE_CREATED, null, dto);
			}
		});

		actionRegistry.register(PMConstants.CMD_CREATE_PMS_WITH_TYPE, new CommandHandler<Command>() {
			public void handleCommand(Command command, List<Command> response) {

				DTO dto = new DTO(Arrays.asList(new Slot("p1", "v1"), new Slot("p2", "v2") ));
				ServerPresentationModel presentationModel = getServerDolphin().presentationModel("pm-type-id-1", "my-type", dto);
				presentationModel = getServerDolphin().presentationModel("pm-type-id-2", "my-type", dto);
			}
		});


	}

}
