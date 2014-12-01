package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
import org.taobao88.taobao.enterprise.entity.Status;

@Transactional
@Repository("statusesDAO")
public class StatusesDAOImpl implements StatusesDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public Status findById(int id) {
		return (Status) sessionFactory.getCurrentSession().createQuery("from Status where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> getAll() {
		return (List<Status>) sessionFactory.getCurrentSession().createQuery("from Status").list();
	}

}
