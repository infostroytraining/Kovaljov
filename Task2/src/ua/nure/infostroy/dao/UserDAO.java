package ua.nure.infostroy.dao;

import ua.nure.infostroy.entity.User;

public interface UserDAO extends CRUD<User> {
	
	User getUserByEmailAndPassword(String email, String password);

}
