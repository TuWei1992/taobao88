package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PostRegionDAO;
import org.taobao88.taobao.enterprise.entity.PostServicePrice;

@Repository("postRegionDAO")
@Transactional
public class PostRegionDAOImpl implements PostRegionDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public PostServicePrice findById(int id) {
		return (PostServicePrice) sessionFactory.getCurrentSession().createQuery("from PostRegion where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostServicePrice> getAll() {
		return (List<PostServicePrice>) sessionFactory.getCurrentSession().createQuery("from PostRegion").list();
	}

	@Override
	public int create(PostServicePrice postRegion) {
		return (int) sessionFactory.getCurrentSession().save(postRegion);
	}

	@Override
	public void update(PostServicePrice postRegion) {
		sessionFactory.getCurrentSession().update(postRegion);
	}

	@Override
	public void delete(PostServicePrice postRegion) {
		sessionFactory.getCurrentSession().delete(postRegion);
	}

}
