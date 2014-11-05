package org.taobao88.taobao.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.DAO.UserRoleDAO;
import org.taobao88.taobao.enterprise.entity.UserRole;

/**
 * Created by User on 03.06.14.
 */
@Repository("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    UserRoleDAO userRoleDAO;

    @Override
    @Transactional
    public void save(UserRole userRole) {

        userRoleDAO.save(userRole);

    }
}
