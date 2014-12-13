package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.UuidDAO;
import org.taobao88.taobao.enterprise.entity.Uuid;

@Transactional
@Repository("uuidDAO")
public class UuidDAOImpl implements UuidDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void addUuid(Uuid uuid) {
		sessionFactory.getCurrentSession().save(uuid);
	}

	@Override
	public Uuid findUuid(String uuid) {
		return (Uuid) sessionFactory.getCurrentSession().createQuery("from Uuid where uuid = :uuid").setParameter("uuid", uuid).uniqueResult();
	}

	@Override
	public void deleteUuid(Uuid uuid) {
		sessionFactory.getCurrentSession().delete(uuid);
	}

}
