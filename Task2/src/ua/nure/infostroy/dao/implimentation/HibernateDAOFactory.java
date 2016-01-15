package ua.nure.infostroy.dao.implimentation;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.hibernate.HibernateUserDAO;

public class HibernateDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new HibernateUserDAO();
	}

	@Override
	public LogDAO getLogDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
