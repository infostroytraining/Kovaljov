package ua.nure.infostroy.dao.implimentation;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.UserDAO;

public abstract class DAOFactory {
	public static final int MEMORY = 1;
	public static final int POSTRGE = 2;

	public abstract UserDAO getUserDAO();
	public abstract LogDAO getLogDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MEMORY:
			return new MemDAOFactory();
		case POSTRGE:
			return new PostgreDAOFactory();
		default:
			return null;
		}
	}
}
