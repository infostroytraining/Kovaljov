package ua.nure.infostroy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.infostroy.command.URLCommand;
import ua.nure.infostroy.entity.HttpWrapper;

@WebServlet("/app/*")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpWrapper wrapper = new HttpWrapper(request, response);
		URLCommand.getCommand(request.getRequestURI().substring(request.getContextPath().length()), wrapper).excecute();
	}

}
