package ua.nure.infostroy.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.DAOFactory;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.utils.MD5Encrypter;
import ua.nure.infostroy.utils.Validator;

public class UserService {
	private Logger log = Logger.getLogger(UserService.class);
	private TransactionService transactionService;

	public void registerUser(HttpWrapper wrapper)
			throws IOException, ServletException, NoSuchAlgorithmException, DAOException {
		List<String> errors = new ArrayList<>();
		HttpServletRequest request = wrapper.getRequest();
		Validator validator = new Validator();
		String firstName = request.getParameter("firstName");
		String secondName = request.getParameter("surname");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String email = request.getParameter("email");
		String kaptchaExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String kaptchaReceived = request.getParameter("kaptcha");
		if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
			errors.add("Invalid kaptcha");
		}
		if (!validator.passwordEquality(password, passwordConfirm)) {
			errors.add("Passwords are not equals");
		}
		if (!validator.validateEmail(email)) {
			errors.add("Email is invalid");
		}
		if (!validator.validateUserName(firstName) || !validator.validateUserName(secondName)) {
			errors.add("User name is invalid");
		}
		if (errors.isEmpty()) {
			UserDAO dao = DAOFactory.getDAOFactory(DAOFactory.POSTRGE).getUserDAO();
			final User user = new User(-1, firstName, secondName, email, new MD5Encrypter().encryptIt(password), "");
			transactionService.doTask(() -> dao.insert(user), 1, DAOFactory.getDAOFactory(DAOFactory.POSTRGE));
			log.info("User " + user.getUserSurname() + " has been successfuly registered");
			wrapper.getRequest().getSession().setAttribute("user", user);
			wrapper.getResponse().sendRedirect("../main.jsp");
			return;
		} else {
			wrapper.getRequest().getSession().setAttribute("errors", errors);
			wrapper.getResponse().sendRedirect("../index.jsp");
		}
	}

	public void login(HttpWrapper wrapper)
			throws ServletException, IOException, NoSuchAlgorithmException, DAOException {
		List<String> errors = new ArrayList<>();
		HttpServletRequest request = wrapper.getRequest();
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		if (password == null) {
			errors.add("password is empty");
		}
		if (email == null) {
			errors.add("email is empty");
		}
		UserDAO dao = DAOFactory.getDAOFactory(DAOFactory.POSTRGE).getUserDAO();

		if (errors.isEmpty()) {
			User user = transactionService.doTask(() -> dao.getUserByEmailAndPassword(email, password), 1,
					DAOFactory.getDAOFactory(DAOFactory.POSTRGE));
			if (user != null) {
				log.info("User " + user.getUserSurname() + "has been entered to the system");
				wrapper.getRequest().getSession().setAttribute("user", user);
				wrapper.getResponse().sendRedirect("../main.jsp");
			} else {
				errors.add("No such user");
				wrapper.getRequest().getSession().setAttribute("errors", errors);
				wrapper.getResponse().sendRedirect("../login.jsp");
			}
		} else {
			wrapper.getRequest().getSession().setAttribute("errors", errors);
			wrapper.getResponse().sendRedirect("../login.jsp");
		}

	}
}
