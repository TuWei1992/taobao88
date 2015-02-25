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

	@Override
	public double getPriceByWeight(double weight, int postServiceId) {
		Double realWeight = (Double) sessionFactory.getCurrentSession().createSQLQuery("SELECT MIN(weight) FROM post_services_prices WHERE weight >= " + weight + " AND post_service_id = " + postServiceId).uniqueResult();
		if (realWeight != null) {
			return (double) sessionFactory.getCurrentSession().createSQLQuery("SELECT price FROM post_services_prices WHERE weight = " + realWeight.doubleValue() + " AND post_service_id = " + postServiceId).uniqueResult();
		}
		return 0.0;
		
	}

}
