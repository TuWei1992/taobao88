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

	@Override
	public PackagesStatuses findById(int id) {
		return (PackagesStatuses) sessionFactory.getCurrentSession().createQuery("from PackagesStatuses where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public PackagesStatuses getCurrentStatus(PackageT packageT) {
		return (PackagesStatuses) sessionFactory.getCurrentSession().createQuery("from PackagesStatuses where packageT.idPackage = :idPackage order by created_at").setParameter("idPackage", packageT.getIdPackage()).list().get(0);
	}

	@Override
	public void deleteAllByPackage(PackageT packageT) {
		sessionFactory.getCurrentSession().createQuery("delete PackagesStatuses where package_id = :package_id").setParameter("package_id", packageT.getIdPackage()).executeUpdate();			
	}

}
