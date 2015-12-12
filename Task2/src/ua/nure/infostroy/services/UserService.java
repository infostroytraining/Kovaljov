package ua.nure.infostroy.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.implimentation.UserDAOImpl;
import ua.nure.infostroy.entity.HttpWrapper;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.utils.Validator;

public class UserService {
	public void registerUser(HttpWrapper wrapper) throws IOException {
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
			User user = new User(-1, firstName, secondName, email, passwordConfirm, "");
			dao.insert(user);
			wrapper.getRequest().setAttribute("user", user);
			return;
		}
	}

	public void login(HttpWrapper wrapper) {

	}
}
