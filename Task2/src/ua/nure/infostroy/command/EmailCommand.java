package ua.nure.infostroy.command;

import java.io.IOException;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.DAOFactory;
import ua.nure.infostroy.entity.User;

public class EmailCommand extends AbstractCommand {
	DAOFactory dao = (DAOFactory) getHttpWrapper().getRequest().getServletContext().getAttribute("factory");
	@Override
	public void excecute() throws DAOException, IOException {
		String parameter = getHttpWrapper().getRequest().getParameter("email");
		System.out.println(parameter);
		User user = dao.getUserDAO().getUserByEmail(parameter);
		if (user==null) {
			getHttpWrapper().getResponse().getWriter().print("1");
		}
		else {
			getHttpWrapper().getResponse().getWriter().print("0");
		}
	}

}
