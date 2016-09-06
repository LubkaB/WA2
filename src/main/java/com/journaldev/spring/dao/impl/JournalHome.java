package com.journaldev.spring.dao.impl;
// Generated Aug 18, 2016 9:22:00 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.List;

import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.IJournalHome;
import com.journaldev.spring.model.Journal;

/**
 * Home object for domain model class Journal.
 * 
 * @see com.journaldev.spring.model.Journal
 * @author Hibernate Tools
 */
@Repository
public class JournalHome implements IJournalHome {

	private static final Log log = LogFactory.getLog(JournalHome.class);

	@Inject
	private SessionFactory sessionFactory;

	public void persist(Journal transientInstance) {
		log.debug("persisting Journal instance");
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

	public List<Journal> getJournalsForReaderId(int id) {
		Session session = sessionFactory.openSession();
		String i = "from Journal where readerId = '" + String.valueOf(id) + "' ";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Journal> list = query.list();

		return list;
	}

	public void remove(Journal persistentInstance) {
		log.debug("removing Journal instance");
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Journal p = (Journal) session.get(Journal.class, persistentInstance.getId());
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

	public void update(Journal p) {
		Session session = sessionFactory.openSession();
		// Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		session.flush();
		log.debug("Reader updated successfully, Reader Details=" + p);
	}

	public Journal findById(Integer id) {
		log.debug("getting Journal instance with id: " + id);
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Journal instance = (Journal) session.get(Journal.class, id);
			// Book instance = session.find(Book.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Journal> getByTitleId(int id) {
		Session session = sessionFactory.openSession();
		String i = "from Journal where titleId = '" + String.valueOf(id) + "' ";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Journal> list = query.list();

		return list;
	}
}
