package com.journaldev.spring.dao.impl;
// Generated Aug 18, 2016 9:09:35 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.List;

import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.IReaderHome;
import com.journaldev.spring.model.Reader;

/**
 * Home object for domain model class Reader.
 * 
 * @see com.journaldev.spring.model.Reader
 * @author Hibernate Tools
 */
@Repository
public class ReaderHome implements IReaderHome{

	private static final Log log = LogFactory.getLog(ReaderHome.class);

	@Inject
	private SessionFactory sessionFactory;

	public void persist(Reader transientInstance) {
		log.debug("persisting Reader instance");
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			session.persist(transientInstance);
			session.flush();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Reader persistentInstance) {
		log.debug("removing Reader instance");
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Reader p = (Reader) session.get(Reader.class, persistentInstance.getId());
			if (null != p) {
				session.delete(p);
				session.flush();
			}
			// session.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public void update(Reader p) {
		Session session = sessionFactory.openSession();
		// Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		session.flush();
		log.debug("Reader updated successfully, Reader Details=" + p);
	}

	public Reader findById(Integer id) {
		log.debug("getting Reader instance with id: " + id);
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Reader instance = (Reader) session.get(Reader.class, id);
			// Book instance = session.find(Book.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public int findByName(String n) {
		Session session = sessionFactory.openSession();
		String i = "from Reader where name = '" + n + "' ";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Reader> list = query.list();
		return list.get(0).getId();
	}
}
