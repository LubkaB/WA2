package com.journaldev.spring.dao.impl;
// Generated Aug 18, 2016 8:13:04 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.List;

import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.dao.IStyleHome;
import com.journaldev.spring.model.Style;

/**
 * Home object for domain model class Style.
 * 
 * @see com.journaldev.spring.model.Style
 * @author Hibernate Tools
 */
@Repository
public class StyleHome implements IStyleHome {

	private static final Log log = LogFactory.getLog(StyleHome.class);

	@Inject
	private SessionFactory sessionFactory;

	public void persist(Style transientInstance) {
		log.debug("persisting Style instance");
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

	public void remove(Style persistentInstance) {
		log.debug("removing Style instance");
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Style p = (Style) session.get(Style.class, persistentInstance.getId());
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

	public void update(Style p) {
		Session session = sessionFactory.openSession();
		// Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		session.flush();
		log.debug("Style updated successfully, Style Details=" + p);
	}

	public Style findById(Integer id) {
		log.debug("getting Style instance with id: " + id);
		try {
			Session session = sessionFactory.openSession();
			// Session session = this.sessionFactory.getCurrentSession();
			Style instance = (Style) session.get(Style.class, id);
			// Book instance = session.find(Book.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Style> getAll() {
		Session session = sessionFactory.openSession();
		String i = "from Style";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Style> list = query.list();

		return list;
	}
	
	public int findByName(String n) {
		Session session = sessionFactory.openSession();
		String i = "from Style where name = '" + n + "' ";
		System.out.println(i);
		Query query = session.createQuery(i);
		List<Style> list = query.list();
		return list.get(0).getId();
	}
}
