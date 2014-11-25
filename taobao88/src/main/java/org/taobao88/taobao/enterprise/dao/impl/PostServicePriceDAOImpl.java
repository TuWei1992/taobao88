package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PostServicePriceDAO;
import org.taobao88.taobao.enterprise.entity.PostServicePrice;

@Repository("postServicePriceDAO")
@Transactional
public class PostServicePriceDAOImpl implements PostServicePriceDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public PostServicePrice findById(int id) {
		return (PostServicePrice) sessionFactory.getCurrentSession().createQuery("from PostServicePrice where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostServicePrice> getAll() {
		return (List<PostServicePrice>) sessionFactory.getCurrentSession().createQuery("from PostServicePrice").list();
	}

	@Override
	public int create(PostServicePrice postPrice) {
		return (int) sessionFactory.getCurrentSession().save(postPrice);
	}

	@Override
	public void update(PostServicePrice postPrice) {
		sessionFactory.getCurrentSession().update(postPrice);
	}

	@Override
	public void delete(PostServicePrice postPrice) {
		sessionFactory.getCurrentSession().delete(postPrice);
	}

}
