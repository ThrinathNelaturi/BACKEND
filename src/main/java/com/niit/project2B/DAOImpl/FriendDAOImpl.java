package com.niit.project2B.DAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.project2B.DAO.FriendDAO;
import com.niit.project2B.model.Friend;

@Repository(value="FriendDAO")
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	private SessionFactory sessionFactory;
	public FriendDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean saveorupdate(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	@Transactional
	public Friend newrequest(String uid, String fid) {
		String hql="from Friend where userid='"+uid+"' and friendid='"+fid+"'";
		@SuppressWarnings({ "rawtypes" })
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list=query.list();
		if(list==null){
			return null;
		}else{
			return list.get(0);
		}
		
	}
	@Transactional
	public List<Friend> getfriendlist(String uid) {
		String hql="from Friend where userid='"+uid+"' and status='a'";
		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings({ "unchecked" })
		List<Friend> list = query.list();
		return list;
	}
	@Transactional
	public List<Friend> getrequestlist(String uid) {
		String hql="from Friend where friendid='"+uid+"' and status='n'";
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list = query.list();
		return list;
	}
	@Transactional
	public List<Friend> setonline(String uid) {
		String hql="from Friend where friendid='"+uid+"'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> list=query.list();
		return list;
	}
	
}
