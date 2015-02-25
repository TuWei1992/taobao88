package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PackageStatusDAO;
import org.taobao88.taobao.enterprise.entity.PackageStatus;

/**
 * Created by User on 19.06.14.
 */
@Transactional
@Repository("packageStatusDAO")
public class PackageStatusDAOImpl implements PackageStatusDAO{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void savePackageStatus(PackageStatus packageStatus) {
        sessionFactory.getCurrentSession().save(packageStatus);
    }

    @Override
    public int getIdPackageStatus(PackageStatus packageStatus) {
        return (Integer) sessionFactory.getCurrentSession().getIdentifier(packageStatus);
    }

    @Override
    public PackageStatus findPackageStatusById(int id) {
        return (PackageStatus) sessionFactory.getCurrentSession().get(PackageStatus.class,id);
    }

    @Override
    public void updatePackageStatus(PackageStatus packageStatus) {
        sessionFactory.getCurrentSession().update(packageStatus);
    }

    @Override
    public void deletePackageStatus(int id) {
        PackageStatus packageStatus = findPackageStatusById(id);
        sessionFactory.getCurrentSession().delete(packageStatus);
    }
}
