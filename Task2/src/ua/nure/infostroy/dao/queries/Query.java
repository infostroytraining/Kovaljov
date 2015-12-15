package ua.nure.infostroy.dao.queries;

public class Query {
	public static final String INSERT_USER = "Insert into users ('user_name','user_surname',"
			+ "'user_email','user_password') values(?,?,?,?)";
}
