package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.CommentsDAO;
import org.taobao88.taobao.enterprise.entity.Comments;
import org.taobao88.taobao.enterprise.entity.UserT;

@Transactional
@Repository("commentsDAO")
public class CommentsDAOImpl implements CommentsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Comments getCommentById(int id) {
		return (Comments) sessionFactory.getCurrentSession().createQuery("from Comments where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public List<Comments> getCommentsByUser(UserT user) {
		return null;
	}

	@Override
	public List<Comments> getAllComments() {
		return (List<Comments>) sessionFactory.getCurrentSession().createQuery("from Comments").list();
	}

	@Override
	public int addComment(Comments comment) {
		return (Integer) sessionFactory.getCurrentSession().save(comment);
	}

	@Override
	public void deleteComment(Comments comment) {
		sessionFactory.getCurrentSession().delete(comment);
	}

}
