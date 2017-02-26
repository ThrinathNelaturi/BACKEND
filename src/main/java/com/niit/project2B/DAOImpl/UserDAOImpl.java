package com.niit.project2B.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.project2B.DAO.UserDAO;
import com.niit.project2B.model.User;
@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<User> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(User.class);
		List<User> list=c.list();
		return list;
	}
   
	@Transactional
	public boolean saveorupdate(User user) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean delete(User user) {
		try{
		sessionFactory.getCurrentSession().delete(user);
		return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		return false;
		}
		
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public List<User> getuser(int id) {
		String hql="from User where id= "+ "'"+ id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<User>list= query.list();
		if(list==null)
		{
			return null;
		}
		else
		{
		return list;
		}
	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Transactional
	public User authuser(String username, String password) {
		String hql = "from User where username= " + "'" + username + "'" + "and password= " + "'" + password + "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Transactional
	public User oneuser(int id) {
		String hql = "from User where id= " + "'" + id + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> list = query.list();

		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Transactional
	public User profileof(String username) {
		String hql = "from User where username='" + username + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> list = query.list();

		if (list == null) 
		{
			return null;
		} 
		else 
		{
			return list.get(0);
		}
	}

	@Transactional
	public List<User> nonfriends(int id) {
		String hql = "from User where id !='" + id + "'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<User> list = query.list();
		return list;
	}

}
