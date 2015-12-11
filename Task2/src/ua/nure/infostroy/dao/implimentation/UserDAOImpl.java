package ua.nure.infostroy.dao.implimentation;

import java.util.Set;

import ua.nure.infostroy.dao.UserDAO;
import static ua.nure.infostroy.dbmock.UserContainer.*;
import ua.nure.infostroy.entity.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User insert(User object) {
		object.setUserId(getMaxId() + 1);
		getUsers().add(object);
		return object;
	}

	@Override
	public User get(long objectId) {
		Set<User> users = getUsers();
		for (User user : users) {
			if (user.getUserId() == objectId) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean update(User object) {
		Set<User> users = getUsers();
		for (User user : users) {
			if (user.getUserId() == object.getUserId()) {
				user = new User(object);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(long objectId) {
		Set<User> users = getUsers();
		for (User user : users) {
			if (user.getUserId() == objectId) {
				users.remove(user);
			}
		}
		return false;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		Set<User> users = getUsers();
		for (User user : users) {
			if (user.getEmail().intern() == email.intern() && user.getPassword().intern() == password.intern()) {
				return user;
			}
		}
		return null;
	}
}
