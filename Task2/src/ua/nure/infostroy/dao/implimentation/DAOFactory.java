package ua.nure.infostroy.dao.implimentation;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.UserDAO;

public abstract class DAOFactory {
	public static final String MEMORY = "Memory";
	public static final String POSTRGE = "Postgre";
	public static final String HIBERNATE = "Hibernate";

	public abstract UserDAO getUserDAO();
	public abstract LogDAO getLogDAO();

	public static DAOFactory getDAOFactory(String whichFactory) {
		switch (whichFactory) {
		case MEMORY:
			return new MemDAOFactory();
		case POSTRGE:
			return new PostgreDAOFactory();
		case HIBERNATE: {
			return new HibernateDAOFactory();
		}
		default:
			return null;
		}
	}
}
