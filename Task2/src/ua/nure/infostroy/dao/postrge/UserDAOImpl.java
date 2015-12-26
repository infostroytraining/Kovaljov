package ua.nure.infostroy.dao.postrge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.PostgreDAOFactory;
import ua.nure.infostroy.dao.queries.Query;
import ua.nure.infostroy.entity.User;

public class UserDAOImpl implements UserDAO {
	private static final String USER_PASSWORD = "user_password";
	private static final String USER_EMAIL = "user_email";
	private static final String USER_SURNAME = "user_surname";
	private static final String USER_NAME = "user_name";
	private static final String USER_ID = "user_id";
	private static Logger log = LogManager.getLogger(UserDAOImpl.class);

	@Override
	public User insert(User user) throws DAOException {
		User result = new User();
		Connection con = null;
		try {
			con = PostgreDAOFactory.getConnection();
			result = insertUser(con, user);
			if (result !=null) {
				con.commit();
			} else {
				PostgreDAOFactory.rollback(con);
			}
		} catch (SQLException e) {
			log.error("Can not insert user.", e);
			throw new DAOException(e);
		} finally {
			PostgreDAOFactory.close(con);
		}
		return result;
	}

	private User insertUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		User result = null;
		try {
			pstmt = con.prepareStatement(Query.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getUserSurname());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			if (pstmt.executeUpdate() != 1)
				return null;
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserId(generatedKeys.getInt(USER_ID));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} finally {
			PostgreDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	@Override
	public User get(long objectId) throws DAOException {
		Connection con = null;
		User user = null;
		try {
			con = PostgreDAOFactory.getConnection();
			user = getUser(con, objectId);
		} catch (SQLException e) {
			log.error("Can not get user.", e);
			throw new DAOException(e);
		}
		return user;
	}

	private User getUser(Connection con, long objectId) throws SQLException {
		PreparedStatement pstmt = null;
		User user = new User();
		try {
			pstmt = con.prepareStatement(Query.GET_USER_BY_ID);
			pstmt.setLong(1, objectId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getLong(USER_ID));
				user.setUserName(rs.getString(USER_NAME));
				user.setUserSurname(rs.getString(USER_SURNAME));
				user.setEmail(rs.getString(USER_EMAIL));
				user.setPassword(rs.getString(USER_PASSWORD));
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("Can not close statement.", e);
				}
			}
		}
	}

	@Override
	public boolean update(User object) throws DAOException {
		Connection con = null;
		boolean updateResult = false;
		try {
			con = PostgreDAOFactory.getConnection();
			updateResult = updateUser(con, object);
		} catch (SQLException e) {
			log.error("Can not update user.", e);
			throw new DAOException(e);
		}
		return updateResult;
	}

	private boolean updateUser(Connection con, User object) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Query.UPDATE_USER);
			pstmt.setString(1, object.getUserName());
			pstmt.setString(2, object.getUserSurname());
			pstmt.setString(3, object.getEmail());
			pstmt.setString(4, object.getPassword());
			pstmt.setLong(5, object.getUserId());
			int updatedRows = pstmt.executeUpdate();
			result = updatedRows == 1;
		} catch (SQLException e) {
			throw e;
		} finally {
			PostgreDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	@Override
	public boolean delete(long objectId) throws DAOException {
		boolean result = false;
		Connection con = null;
		try {
			con = PostgreDAOFactory.getConnection();
			result = deleteUser(con, objectId);
		} catch (SQLException e) {
			log.error("Can not delete holiday.", e);
			throw new DAOException(e);
		}
		return result;
	}

	private boolean deleteUser(Connection con, long objectId) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Query.DELETE_USER);
			pstmt.setLong(1, objectId);
			result = pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			throw e;
		} finally {
			PostgreDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws DAOException {
		Connection con = null;
		User user = null;
		try {
			con = PostgreDAOFactory.getConnection();
			user = getUserByEmailAndPassword(con, email, password);
		} catch (SQLException e) {
			log.error("Can not get user.", e);
			throw new DAOException(e);
		}
		return user;
	}

	private User getUserByEmailAndPassword(Connection con, String email, String password) throws SQLException {
		PreparedStatement pstmt = null;
		User user = new User();
		try {
			pstmt = con.prepareStatement(Query.GET_USER_BY_EMAIL_PASSWORD);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getLong(USER_ID));
				user.setUserName(rs.getString(USER_NAME));
				user.setUserSurname(rs.getString(USER_SURNAME));
				user.setEmail(rs.getString(USER_EMAIL));
				user.setPassword(rs.getString(USER_PASSWORD));
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("Can not close statement.", e);
				}
			}
		}
	}

	@Override
	public User getUserByEmail(String email) throws DAOException {
		Connection con = null;
		User user = null;
		try {
			con = PostgreDAOFactory.getConnection();
			user = getUserByEmail(con, email);
		} catch (SQLException e) {
			log.error("Can not get user.", e);
			throw new DAOException(e);
		}
		return user;
	}

	private User getUserByEmail(Connection con, String email) throws SQLException {
		PreparedStatement pstmt = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(Query.GET_USER_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getLong(USER_ID));
				user.setUserName(rs.getString(USER_NAME));
				user.setUserSurname(rs.getString(USER_SURNAME));
				user.setEmail(rs.getString(USER_EMAIL));
				user.setPassword(rs.getString(USER_PASSWORD));
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("Can not close statement.", e);
				}
			}
		}
	}

}
