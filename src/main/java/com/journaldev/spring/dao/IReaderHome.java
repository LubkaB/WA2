package com.journaldev.spring.dao;

import com.journaldev.spring.model.Reader;

public interface IReaderHome {

	public void persist(Reader transientInstance);

	public void remove(Reader persistentInstance);

	public void update(Reader p);

	public Reader findById(Integer id);
}
