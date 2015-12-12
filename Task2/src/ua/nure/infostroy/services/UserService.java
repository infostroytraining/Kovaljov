package ua.nure.infostroy.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.utils.MD5Encrypter;
import ua.nure.infostroy.utils.Validator;

public class UserService {
	public void registerUser(HttpWrapper wrapper) throws IOException, ServletException, NoSuchAlgorithmException {
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
			UserDAO dao = new UserDAOImpl();
			User user = new User(-1, firstName, secondName, email, new MD5Encrypter().encryptIt(password), "");
			dao.insert(user);
			wrapper.getRequest().getSession().setAttribute("user", user);
			wrapper.getRequest().getRequestDispatcher("/main.jsp").forward(wrapper.getRequest(), wrapper.getResponse());
			return;
		} else {
			wrapper.getRequest().getSession().setAttribute("errors", errors);
			wrapper.getRequest().getRequestDispatcher("/index.jsp").forward(wrapper.getRequest(), wrapper.getResponse());
		}
	}

	public void login(HttpWrapper wrapper) throws ServletException, IOException, NoSuchAlgorithmException {
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
		UserDAO dao = new UserDAOImpl();
		
		if (errors.isEmpty()) {
			User user = dao.getUserByEmailAndPassword(email, new MD5Encrypter().encryptIt(password));
			if (user !=null) {
				wrapper.getRequest().getSession().setAttribute("user", user);
				wrapper.getRequest().getRequestDispatcher("/main.jsp").forward(wrapper.getRequest(), wrapper.getResponse());
			}
			else {
				errors.add("No such user");
				wrapper.getRequest().getSession().setAttribute("errors", errors);
				wrapper.getRequest().getRequestDispatcher("/login.jsp").forward(wrapper.getRequest(), wrapper.getResponse());
			}
		}
		else{
			wrapper.getRequest().getSession().setAttribute("errors", errors);
			wrapper.getRequest().getRequestDispatcher("/login.jsp").forward(wrapper.getRequest(), wrapper.getResponse());
		}

	}
}
