package ua.nure.infostroy.command;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.memory.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.services.UserService;

public class LoginCommand extends AbstractCommand implements Command {
	private Logger log = Logger.getLogger(LoginCommand.class);
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
		try {
			new UserService().login(getHttpWrapper());
		} catch (ServletException | IOException | NoSuchAlgorithmException | DAOException e) {
			log.error(e.getMessage());
		}
	}

}
