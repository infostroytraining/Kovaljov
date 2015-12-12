package ua.nure.infostroy.command;

import java.util.HashMap;
import java.util.Map;

import ua.nure.infostroy.entity.HttpWrapper;

public class URLCommand {
	private static final Map<String,Command> URL_TO_COMMAND = new HashMap<>();
	static {
		URL_TO_COMMAND.put("/app/register", new RegisterCommand());
		URL_TO_COMMAND.put("/app/login", new LoginCommand());
	}
	public static Command getCommand(String url, HttpWrapper http) {
		AbstractCommand command = (AbstractCommand) URL_TO_COMMAND.get(url);
		command.setHttpWrapper(http);
		return command;
	}
}
