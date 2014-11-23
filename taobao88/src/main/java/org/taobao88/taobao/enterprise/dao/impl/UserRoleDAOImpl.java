package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.UserRoleDAO;
import org.taobao88.taobao.enterprise.entity.UserRole;

/**
 * Created by User on 03.06.14.
 */
@Transactional
@Repository("userRoleDAO")
public class UserRoleDAOImpl implements UserRoleDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(UserRole userRole) {

        sessionFactory.getCurrentSession().save(userRole);

    }
}
