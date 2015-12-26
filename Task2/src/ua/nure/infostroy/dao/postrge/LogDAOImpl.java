package ua.nure.infostroy.dao.postrge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.implimentation.PostgreDAOFactory;
import ua.nure.infostroy.dao.queries.Query;
import ua.nure.infostroy.entity.Log;

public class LogDAOImpl implements LogDAO {
	private Logger log = LogManager.getLogger();

	@Override
	public Log insert(Log object) throws DAOException {
		Log result = new Log();
		Connection con = null;
		try {
			con = PostgreDAOFactory.getConnection();
			result = insert(con, object);
			if (result!=null) {
				con.commit();
			} else {
				PostgreDAOFactory.rollback(con);
			}
		} catch (SQLException e) {
			log.error("Can not insert user.", e);
			throw new DAOException(e);
		} finally{
			PostgreDAOFactory.close(con);
		}
		return result;
	}

	private Log insert(Connection con, Log object) throws SQLException {
		PreparedStatement pstmt = null;
		Log result = null;
		try {
			pstmt = con.prepareStatement(Query.INSERT_LOG, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, object.getLogText());
			if (pstmt.executeUpdate() != 1) {
				return null;
			}
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					object.setLogId(generatedKeys.getInt("log_id"));
				} else {
					throw new SQLException("Creating log failed, no ID obtained.");
				}
			}
		} finally {
			PostgreDAOFactory.closeStatement(pstmt);
		}
		return result;
	}

	@Override
	public Log get(long objectId) throws DAOException {
		return null;
	}

	@Override
	public List<Log> getAll() throws DAOException {
		Connection con = null;
		List<Log> logs = new ArrayList<Log>();
		try {
			con = PostgreDAOFactory.getConnection();
			logs = getAll(con);
		} catch (SQLException e) {
			log.error("Can not get logs.", e);
			throw new DAOException(e);
		}
		return logs;
	}

	private List<Log> getAll(Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		List<Log> logs = new ArrayList<Log>();
		try {
			pstmt = con.prepareStatement(Query.GET_LOGS);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Log log = new Log();
				log.setLogId(rs.getInt("log_id"));
				log.setLogText(rs.getString("log_message"));
				logs.add(log);
			}
			return logs;
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
	public boolean update(Log object) throws DAOException {
		return false;
	}

	@Override
	public boolean delete(long objectId) throws DAOException {
		return false;
	}

}
