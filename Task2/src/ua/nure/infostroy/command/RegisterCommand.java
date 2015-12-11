package ua.nure.infostroy.command;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;

public class RegisterCommand implements Command{
	private UserDAO dao = new UserDAOImpl();
	private User user;
	private HttpWrapper httpHandler;
	
	public RegisterCommand(HttpWrapper httpHandler, User user) {
		this.user = user;
		this.httpHandler = httpHandler;
	}
	
	@Override
	public void excecute() {
		dao.insert(user);
	}

}
