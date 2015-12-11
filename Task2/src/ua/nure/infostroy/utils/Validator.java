package ua.nure.infostroy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String NAME_PATTERN = "[a-zA-Zà-ÿÀ-ß]{3,30}";

	public boolean validateEmail(String email) {
		if (email==null) {
			return false;
		}
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public boolean validateUserName(String firstName) {
		if (firstName==null) {
			return false;
		}
		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(firstName);
		return matcher.matches();
	}
	
	public boolean passwordEquality(String password, String passwordConfirmation) {
		if (password==null || passwordConfirmation==null) {
			return false;
		}
		return (password.equals(passwordConfirmation));
	}
}
