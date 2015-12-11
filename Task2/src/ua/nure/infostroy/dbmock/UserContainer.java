package ua.nure.infostroy.dbmock;

import java.util.HashSet;
import java.util.Set;

import ua.nure.infostroy.entity.User;

public class UserContainer {
	private static final Set<User> USERS = new HashSet<>();
	
	static {
		USERS.add(new User(1,"Eugene","Kovaljov","didevgen@gmail.com","cff942bb0dcea6fc94cac1b936b9254c",""));
		USERS.add(new User(2,"Vsilii","Terkin","terkin@gmail.com","e10adc3949ba59abbe56e057f20f883e",""));
	}
	
	public static Set<User> getUsers() {
		return USERS;
	}
	public static long getMaxId() {
		long max = 0;
		for (User user :USERS) {
			if (user.getUserId()>max) {
				max = user.getUserId();
			}
		}
		return max;
	}
}
