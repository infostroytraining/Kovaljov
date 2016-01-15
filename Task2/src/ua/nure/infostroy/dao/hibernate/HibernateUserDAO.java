package ua.nure.infostroy.dao.hibernate;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ua.nure.infostroy.dao.UserDAO;
import ua.nure.infostroy.dao.exceptions.DAOException;
import ua.nure.infostroy.entity.User;
import ua.nure.infostroy.utils.HibernateUtil;

public class HibernateUserDAO implements UserDAO {
	private Logger log = LogManager.getLogger(HibernateUserDAO.class);

	@Override
	public User insert(User object) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
			return object;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public User get(long objectId) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			User users = (User) session.load(User.class, objectId);
			return users;
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean update(User object) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public boolean delete(long objectId) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User myObject = (User) session.load(User.class, objectId);
			session.delete(myObject);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			List<User> users = session.createCriteria(User.class).add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password)).list();
			if (users.isEmpty()) {
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public User getUserByEmail(String email) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			List<User> users = session.createCriteria(User.class).add(Restrictions.eq("email", email)).list();
			if (users.isEmpty()) {
				return null;
			}
			return users.get(0);
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			session.close();
		}
	}

}
