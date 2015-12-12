package ua.nure.infostroy.command;

import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.services.UserService;

public class RegisterCommand extends AbstractCommand implements Command {
	private User user;

	public RegisterCommand() {
	}

	public RegisterCommand(HttpWrapper http) {
		super.setHttpWrapper(http);
	}

	public RegisterCommand(User user) {
		this.user = user;
	}

	public RegisterCommand(HttpWrapper httpHandler, User user) {
		this.user = user;
		super.setHttpWrapper(httpHandler);
	}

	@Override
	public void excecute() {
		new UserService().login(getHttpWrapper());
	}


}
