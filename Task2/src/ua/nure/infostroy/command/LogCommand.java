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
		System.out.println(logMessage);
		LogDAO dao = DAOFactory.getDAOFactory(DAOFactory.POSTRGE).getLogDAO();
		Log log = new Log();
		log.setLogText(logMessage);
		service.doTask(() -> dao.insert(log), 1, DAOFactory.getDAOFactory(DAOFactory.POSTRGE));
		dao.insert(log);
	}

}
