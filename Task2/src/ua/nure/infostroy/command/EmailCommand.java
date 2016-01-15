package ua.nure.infostroy.command;

import java.io.IOException;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.DAOFactory;
import ua.nure.infostroy.entity.User;

public class EmailCommand extends AbstractCommand {

	@Override
	public void excecute() throws DAOException, IOException {
		DAOFactory dao = (DAOFactory) getHttpWrapper().getRequest().getServletContext().getAttribute("factory");
		String parameter = getHttpWrapper().getRequest().getParameter("email");
		User user = dao.getUserDAO().getUserByEmail(parameter);
		System.out.println(user);
		if (user==null) {
			getHttpWrapper().getResponse().getWriter().print("1");
		}
		else {
			getHttpWrapper().getResponse().getWriter().print("0");
		}
	}

}
