package com.journaldev.spring.dao.impl;
// Generated Aug 18, 2016 8:39:24 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.List;

import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.ITitleHome;
import com.journaldev.spring.model.Title;

/**
 * Home object for domain model class Title.
 * 
 * @see com.journaldev.spring.model.Title
 * @author Hibernate Tools
 */
@Repository
public class TitleHome implements ITitleHome {

	private static final Log log = LogFactory.getLog(TitleHome.class);

	@Inject
	private SessionFactory sessionFactory;

	public void persist(Title transientInstance) {
		log.debug("persisting Title instance");
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

	public void remove(Title persistentInstance) {
		log.debug("removing Title instance");
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Title p = (Title) session.get(Title.class, persistentInstance.getId());
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

	public void update(Title p) {
		Session session = sessionFactory.openSession();
		// Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		session.flush();
		log.debug("Title updated successfully, Title Details=" + p);
	}

	public Title findById(Integer id) {
		log.debug("getting Title instance with id: " + id);
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Title instance = (Title) session.get(Title.class, id);
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
		String i = "from Title where name = '" + n + "' ";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Title> list = query.list();
		if (!list.isEmpty()) {
			return list.get(0).getId();
		} else {
			return -1;
		}
	}
	
	public List<Title> getAll() {
		Session session = sessionFactory.openSession();
		String i = "from Title";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Title> list = query.list();
		return list;
	}
}
