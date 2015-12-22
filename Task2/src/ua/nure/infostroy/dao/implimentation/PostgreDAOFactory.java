package ua.nure.infostroy.dao.implimentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.infostroy.dao.LogDAO;
import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.dao.postrge.LogDAOImpl;
import ua.nure.infostroy.dao.postrge.UserDAOImpl;

public class PostgreDAOFactory extends DAOFactory {
	public static final String DRIVER = "org.postgresql.Driver";
	public static final String DBURL = "jdbc:postgresql://localhost:5432/Infostroy";

	private static Logger log = LogManager.getLogger(PostgreDAOFactory.class);
	public static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Infostroy?characterEncoding=utf8","postgres","admin");
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Can not get connection.", e);
		} catch (ClassNotFoundException e) {
			log.error("Can not load driver.", e);
		}
		return con;
	}

	public static void rollback(Connection con) throws DAOException {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				log.error("Can not rollback transaction.", e);
				throw new DAOException("Can not rollback transaction", e);
			}
		}
	}

	static void commit(Connection con) {
		try {
			log.debug("Try commit transaction");
			con.commit();
		} catch (SQLException e) {
			try {
				log.error("Try rollback transaction");
				con.rollback();
			} catch (SQLException e1) {
				log.error("Can not rollback transaction # " + e1.getMessage());
			}
			log.error("Can not commit transaction # " + e.getMessage());
		}
	}

	public static void close(Connection con) {
		try {
			log.debug("Try close connection");
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			log.error("Can not close connection # " + e.getMessage());
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
	}

	public static void commitAndClose(Connection con) {
		commit(con);
		close(con);
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public LogDAO getLogDAO() {
		return new LogDAOImpl();
	}

}
