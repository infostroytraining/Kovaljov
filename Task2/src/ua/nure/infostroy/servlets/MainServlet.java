package ua.nure.infostroy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.infostroy.command.URLCommand;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.entity.HttpWrapper;

@WebServlet("/app/*")
public class MainServlet extends HttpServlet {
	
	private Logger log = LogManager.getLogger(MainServlet.class);
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpWrapper wrapper = new HttpWrapper(request, response);
		try {
			URLCommand.getCommand(request.getRequestURI().substring(request.getContextPath().length()), wrapper).excecute();
		} catch (DAOException e) {
			log.error(e);
		}
	}
	
	

}
