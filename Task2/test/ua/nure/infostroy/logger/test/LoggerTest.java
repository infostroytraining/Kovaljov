package ua.nure.infostroy.logger.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.LogEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ua.nure.infostroy.command.Command;
import ua.nure.infostroy.log4j.appender.CustomAppender;
import ua.nure.infostroy.services.UserService;
import ua.nure.infostroy.servlets.MainServlet;

public class LoggerTest {
	private MainServlet servlet = new MainServlet();
	private CustomAppender appender = new CustomAppender();
	
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private ServletContext context;
	@Mock
	private UserService service;
	@Mock
	private RequestDispatcher dispatcher;
	@Mock
	private HttpSession session;
	@Mock
	private Command command;
	@Mock
	private LogEvent event;

	@Test
	public void test() {
		appender.append(event);
	}

}
