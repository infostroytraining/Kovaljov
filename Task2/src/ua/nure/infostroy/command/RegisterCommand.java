package ua.nure.infostroy.command;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.services.UserService;

public class RegisterCommand extends AbstractCommand implements Command {
	private User user;
	private Logger log = Logger.getLogger(RegisterCommand.class);
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
		try {
			new UserService().registerUser(getHttpWrapper());
		} catch (IOException | ServletException | NoSuchAlgorithmException | DAOException e) {
			log.error(e.getMessage());
		}
	}


}
