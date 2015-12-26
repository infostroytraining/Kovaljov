package ua.nure.infostroy.command;

import java.io.IOException;

import com.google.gson.Gson;

import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.DAOFactory;

public class GetLogCommand extends AbstractCommand {
	DAOFactory dao = (DAOFactory) getHttpWrapper().getRequest().getServletContext().getAttribute("factory");

	@Override
	public void excecute() throws DAOException, IOException {
		String parameter = getHttpWrapper().getRequest().getParameter("format");
		if (parameter != null && !parameter.isEmpty() && parameter.equals("pretty")) {
			getHttpWrapper().getResponse().getWriter().print(new Gson().toJson(dao.getLogDAO().getAll()));
		}
	}

}
