package com.journaldev.spring.dao;

import org.hibernate.Session;

import com.journaldev.spring.model.Title;

public interface ITitleHome {
	
	public void persist(Title transientInstance);

	public void remove(Title persistentInstance);

	public void update(Title p);

	public Title findById(Integer id);

}
