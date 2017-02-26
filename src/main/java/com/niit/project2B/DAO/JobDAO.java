package com.niit.project2B.DAO;

import java.util.List;

import com.niit.project2B.model.Job;

public interface JobDAO {
	public boolean saveorupdate(Job job);
	public boolean delete(Job job);
    public List<Job> list();

}
