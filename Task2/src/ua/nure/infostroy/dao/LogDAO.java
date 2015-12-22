package ua.nure.infostroy.dao;

import java.util.List;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.entity.Log;

public interface LogDAO extends CRUD<Log>{
	
	List<Log> getAll() throws DAOException;

}
