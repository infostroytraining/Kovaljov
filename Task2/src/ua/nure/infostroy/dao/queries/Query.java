package ua.nure.infostroy.dao.queries;

public class Query {
	public static final String INSERT_USER = "Insert into users ('user_name','user_surname',"
			+ "'user_email','user_password') values(?,?,?,?)";

	public static final String GET_USER_BY_ID = "Select * From users where user_id =  ?";
	public static final String UPDATE_USER = "Update users set 'user_name' =?, 'user_surname"
			+ "'=?,'user_email'=?,'user_password'=? where 'user_id'=?";
	public static final String DELETE_USER = "Delete from users where user_id =?";

	public static final String GET_USER_BY_EMAIL_PASSWORD = "Select * From users where 'user_email'=? AND 'user_password'=?";
}
