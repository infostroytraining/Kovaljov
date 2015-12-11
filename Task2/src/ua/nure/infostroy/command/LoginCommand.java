package ua.nure.infostroy.command;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;

public class LoginCommand implements Command{
	private UserDAO dao = new UserDAOImpl();
	private String login;
	private String password;
	private HttpWrapper httpHandler;
	
	public LoginCommand(HttpWrapper http, String login, String password) {
		this.login = login;
		this.password = password;
		this.httpHandler = http;
	}
	
	@Override
	public void excecute() {
		dao.getUserByEmailAndPassword(login, password);
	}

}
