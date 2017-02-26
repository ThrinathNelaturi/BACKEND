package com.niit.project2B.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.project2B.DAO.JobDAO;
import com.niit.project2B.model.Job;

@Repository(value="jobDAO")
public class JobDAOImpl implements JobDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveorupdate(Job job) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			System.out.println("save");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean delete(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<Job> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Job.class);
		List<Job> list=c.list();
		return list;
	}
	

}
