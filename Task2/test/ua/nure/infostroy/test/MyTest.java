package ua.nure.infostroy.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import java.io.IOException;

import org.apache.catalina.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.infostroy.command.Command;
import ua.nure.infostroy.command.URLCommand;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.services.TransactionService;
import ua.nure.infostroy.services.UserService;
import ua.nure.infostroy.servlets.MainServlet;

@RunWith(MockitoJUnitRunner.class)
public class MyTest {
	private MainServlet servlet = new MainServlet();
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

	@Before
	public void init() {
		when(request.getServletContext()).thenReturn(context);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(request.getRequestURI()).thenReturn("/Task2/app/register");
		when(request.getContextPath()).thenReturn("/Task2");
		when(request.getSession()).thenReturn(session);
	}

	@Test
	public void testDoGet() throws ServletException, IOException {
		servlet.doGet(request, response);
	}
	
	@Test
	public void testDoGetWithParams() throws ServletException, IOException {
		mockGetRequestParams("firstName","Eugene");
		mockGetRequestParams("surname","Kovaljov");
		mockGetRequestParams("password","12345");
		mockGetRequestParams("passwordConfirm","12345");
		mockGetRequestParams("email","didevgen@gmail.com");
		mockGetRequestParams("kaptcha","123456");
		when(session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)).thenReturn("123456");
		servlet.doPost(request, response);
		verify(session).setAttribute(eq("user"), any(User.class));
	}
	@Test
	public void testDoGetWithWrongParams() throws ServletException, IOException {
		mockGetRequestParams("firstName","Eugene1212");
		mockGetRequestParams("surname","Kovaljov");
		mockGetRequestParams("password","12345");
		mockGetRequestParams("passwordConfirm","12345");
		mockGetRequestParams("email","didevgen@gmail.com");
		mockGetRequestParams("kaptcha","12345611");
		when(session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)).thenReturn("123456");
		servlet.doPost(request, response);
		verify(session).setAttribute(eq("errors"), anyList());
	}
	@Test
	public void throwDAOException() throws ServletException, IOException, DAOException {
		doThrow(new DAOException()).when(command).excecute();
	}
	private void mockGetRequestParams(String name, String value){
		when(request.getParameter(name)).thenReturn(value);
	}

}
