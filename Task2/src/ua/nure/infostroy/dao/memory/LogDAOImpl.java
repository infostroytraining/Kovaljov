package ua.nure.infostroy.dao.memory;

import java.util.List;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dbmock.LogContainer;
import ua.nure.infostroy.entity.Log;

public class LogDAOImpl implements LogDAO{

	@Override
	public Log insert(Log object) throws DAOException {
		LogContainer.getLogs().add(object);
		return object;
	}

	@Override
	public Log get(long objectId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Log object) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long objectId) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Log> getAll() throws DAOException {
		return LogContainer.getLogs();
	}

}
