package ua.nure.infostroy.dao.implimentation;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.memory.LogDAOImpl;
import ua.nure.infostroy.dao.memory.UserDAOImpl;

public class MemDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public LogDAO getLogDAO() {
		return new LogDAOImpl();
	}

}
