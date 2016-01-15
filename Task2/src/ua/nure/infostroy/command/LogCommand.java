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
		DAOFactory dao = (DAOFactory) getHttpWrapper().getRequest().getServletContext().getAttribute("factory");
		if (dao==null) {
			System.out.println("null");
		}
		String logMessage = getHttpWrapper().getRequest().getParameter("logEvent");
		Log log = new Log();
		log.setLogText(logMessage);
		dao.getLogDAO().insert(log);
	}

}
