package ua.nure.infostroy.command;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;

public class LoginCommand extends AbstractCommand implements Command {
	private UserDAO dao = new UserDAOImpl();
	private String login;
	private String password;

	public LoginCommand() {
	}

	public LoginCommand(HttpWrapper http) {
		super.setHttpWrapper(http);
	}

	public LoginCommand(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public LoginCommand(HttpWrapper http, String login, String password) {
		this.login = login;
		this.password = password;
		super.setHttpWrapper(http);
	}

	@Override
	public void excecute() {
		dao.getUserByEmailAndPassword(login, password);
	}


}
