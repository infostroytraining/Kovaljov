package ua.nure.infostroy.command;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;

public class RegisterCommand extends AbstractCommand implements Command {
	private UserDAO dao = new UserDAOImpl();
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
		dao.insert(user);
	}


}
