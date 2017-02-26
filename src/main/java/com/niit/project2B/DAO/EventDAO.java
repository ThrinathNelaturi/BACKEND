package com.niit.project2B.DAO;

import com.niit.project2B.model.Event;

public interface EventDAO {
	public boolean saveorupdate(Event event);
	public boolean delete(Event event);

}
