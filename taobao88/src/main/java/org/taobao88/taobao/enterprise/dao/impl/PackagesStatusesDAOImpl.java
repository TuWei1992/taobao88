package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PackagesStatusesDAO;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.PackagesStatuses;

@Transactional
@Repository("packagesStatusesDAO")
public class PackagesStatusesDAOImpl implements PackagesStatusesDAO {

	@Autowired private SessionFactory sessionFactory;;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackagesStatuses> findByOrder(PackageT packageT) {
		return (List<PackagesStatuses>) sessionFactory.getCurrentSession().createQuery("from PackagesStatuses where packageT.idpackage = :idpackage order by created_at").setParameter("idpackage", packageT.getIdPackage()).uniqueResult();
	}

	@Override
	public int add(PackagesStatuses ps) {
		return (int) sessionFactory.getCurrentSession().save(ps);
	}

	@Override
	public void delete(PackagesStatuses ps) {
		sessionFactory.getCurrentSession().delete(ps);
	}

	@Override
	public void update(PackagesStatuses ps) {
		sessionFactory.getCurrentSession().update(ps);	
	}

}