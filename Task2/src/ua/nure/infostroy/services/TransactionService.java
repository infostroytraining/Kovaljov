package ua.nure.infostroy.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.helper.ConnectionHolder;
import ua.nure.infostroy.dao.helper.Transaction;
import ua.nure.infostroy.dao.implimentation.DAOFactory;
import ua.nure.infostroy.dao.implimentation.PostgreDAOFactory;

public class TransactionService {
	private Logger log = LogManager.getLogger();
	public <T> T doTask(Transaction<T> transaction, int transactionIsolation, DAOFactory factory) throws DAOException {
		if (factory instanceof PostgreDAOFactory) {
			Connection connection = null;
			try {
				connection = PostgreDAOFactory.getConnection();
				ConnectionHolder.setConnection(connection);
				T value = transaction.execute();
				connection.commit();
				return value;
			} catch (SQLException | DAOException exeption) {
				PostgreDAOFactory.rollback(connection);
				throw new DAOException(exeption);
			} finally {
				PostgreDAOFactory.close(connection);
			}
		}
		return null;
	}
}
