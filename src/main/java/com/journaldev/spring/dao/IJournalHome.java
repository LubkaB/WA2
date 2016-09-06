package com.journaldev.spring.dao;

import org.hibernate.Session;

import com.journaldev.spring.model.Journal;

public interface IJournalHome {

	public void persist(Journal transientInstance);

	public void remove(Journal persistentInstance);

	public void update(Journal p);

	public Journal findById(Integer id);

}
