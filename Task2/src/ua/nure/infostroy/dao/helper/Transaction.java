package ua.nure.infostroy.dao.helper;

import ua.nure.infostroy.dao.exceptions.DAOException;

public interface Transaction<T> {
	public T execute() throws DAOException;
}
