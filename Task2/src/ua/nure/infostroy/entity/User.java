package ua.nure.infostroy.entity;

public class User {
	private long userId;

	private String userName;

	private String userSurname;

	private String email;

	private String password;

	private String pathToAvatar;

	public User() {
	}

	public User(long userId, String userName, String userSurname, String email, String password, String pathToAvatar) {
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.email = email;
		this.password = password;
		this.pathToAvatar = pathToAvatar;
	}

	public User (User anotherUser) {
		this.userName = anotherUser.userName;
		this.userSurname = anotherUser.userSurname;
		this.email = anotherUser.email;
		this.password = anotherUser.password;
		this.pathToAvatar = anotherUser.pathToAvatar;
	}
	@Override 
	public String toString() {
		return "User [userName=" + userName + ", userSurname=" + userSurname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPathToAvatar() {
		return pathToAvatar;
	}

	public void setPathToAvatar(String pathToAvatar) {
		this.pathToAvatar = pathToAvatar;
	}

}
