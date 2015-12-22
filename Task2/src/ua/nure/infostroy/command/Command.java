package ua.nure.infostroy.command;

import java.io.IOException;

import ua.nure.infostroy.dao.exceptions.DAOException;

public interface Command {
	void excecute() throws DAOException, IOException;
}
