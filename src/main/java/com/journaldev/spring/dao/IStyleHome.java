package com.journaldev.spring.dao;

import com.journaldev.spring.model.Style;

public interface IStyleHome {
	
	public void persist(Style transientInstance);

	public void remove(Style persistentInstance);

	public void update(Style p);

	public Style findById(Integer id);
}
