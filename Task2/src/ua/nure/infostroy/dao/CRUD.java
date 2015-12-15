package ua.nure.infostroy.dao;

import ua.nure.infostroy.dao.exceptions.DAOException;

public interface CRUD<T> {
	T insert(T object) throws DAOException;
	
	T get(long objectId);
	
	boolean update(T object) throws DAOException;
	
	boolean delete(long objectId) throws DAOException;
	
}
