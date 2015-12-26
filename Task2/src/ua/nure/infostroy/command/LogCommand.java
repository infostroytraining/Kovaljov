package ua.nure.infostroy.command;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.DAOFactory;
import ua.nure.infostroy.entity.Log;
import ua.nure.infostroy.services.TransactionService;

public class LogCommand extends AbstractCommand{
	private TransactionService service = new TransactionService();
	@Override
	public void excecute() throws DAOException {
		String logMessage = getHttpWrapper().getRequest().getParameter("logEvent");
		LogDAO dao = DAOFactory.getDAOFactory(DAOFactory.POSTRGE).getLogDAO();
		Log log = new Log();
		log.setLogText(logMessage);
		dao.insert(log);
	}

}
