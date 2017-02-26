package com.niit.project2B.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.project2B.DAO.ForumCommentDAO;
import com.niit.project2B.model.ForumComment;

@Repository(value="forumCommentDAOImpl")
public class ForumCommentDAOImpl implements ForumCommentDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public ForumCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public boolean saveorupdate(ForumComment forumcomment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumcomment);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean delete(ForumComment forumcomment) {
		try {
			sessionFactory.getCurrentSession().delete(forumcomment);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<ForumComment> list(int fid) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(ForumComment.class);
		c.add(Restrictions.eq("forumid", fid));
		List<ForumComment> list=c.list();
		return list;
	}

}
