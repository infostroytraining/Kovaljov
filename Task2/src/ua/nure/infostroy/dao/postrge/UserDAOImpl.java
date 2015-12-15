package ua.nure.infostroy.dao.postrge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.PostgreDAOFactory;
import ua.nure.infostroy.dao.queries.Query;
import ua.nure.infostroy.entity.User;

public class UserDAOImpl implements UserDAO {
	private static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public User insert(User user) throws DAOException {
		User result = new User();
		Connection con = null;
		try {
			con = PostgreDAOFactory.getConnection();
			result = insertUser(con, user);
			if (result != null) {
				con.commit();
			} else {
				PostgreDAOFactory.rollback(con);
			}
		} catch (SQLException e) {
			PostgreDAOFactory.rollback(con);
			log.error("Can not insert user.", e);
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
					user.setUserId(generatedKeys.getLong(1));
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
	public User get(long objectId) {
		Connection con = null;
		User user = null;
		try {
			con = PostgreDAOFactory.getConnection();
			user = getUser(con, objectId);
		} catch (SQLException e) {
			log.error("Can not get user.", e);
		} finally {
			PostgreDAOFactory.close(con);
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
				user.setUserId(rs.getLong(1));
				user.setUserName(rs.getString(2));
				user.setUserSurname(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPassword(rs.getString(5));
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
	public boolean update(User object) {
		return false;
	}

	@Override
	public boolean delete(long objectId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
