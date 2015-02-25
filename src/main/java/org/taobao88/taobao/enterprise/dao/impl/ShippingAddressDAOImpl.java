package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.ShippingAddressDAO;
import org.taobao88.taobao.enterprise.entity.ShippingAddress;

@Transactional
@Repository("shippingAddressDAO")
public class ShippingAddressDAOImpl implements ShippingAddressDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public ShippingAddress findById(int id) {
		return (ShippingAddress) sessionFactory.getCurrentSession().createQuery("from ShippingAddress where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingAddress> getAll() {
		return (List<ShippingAddress>) sessionFactory.getCurrentSession().createQuery("from ShippingAddress").list();
	}

	@Override
	public int add(ShippingAddress shippingAddress) {
		return (int) sessionFactory.getCurrentSession().save(shippingAddress);
	}

	@Override
	public void delete(ShippingAddress shippingAddress) {
		sessionFactory.getCurrentSession().delete(shippingAddress);
	}

	@Override
	public void update(ShippingAddress shippingAddress) {
		sessionFactory.getCurrentSession().update(shippingAddress);
	}

}
