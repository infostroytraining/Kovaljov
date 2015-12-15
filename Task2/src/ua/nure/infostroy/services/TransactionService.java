package ua.nure.infostroy.services;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.helper.Transaction;

public class TransactionService {

	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) {
		try {
			T result = transaction.execute();
			return result;
		} catch (DAOException e) {
			return null;
		}
	}
}
