package org.taobao88.taobao.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.UserDAO;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.UserService;

import java.util.List;

/**
 * Created by User on 02.06.14.
 */
@Repository("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void saveNewUser(UserT user) {
        userDAO.saveNewUser(user);
    }

    @Override
    @Transactional
    public List<UserT> getId(String name) {
        return userDAO.getId(name);
    }

    @Override
    @Transactional
    public UserT findUserById(int id) {
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserT userT) {
        userDAO.updateUser(userT);
    }

    @Override
    @Transactional
    public List<UserT> getAll() {
        return userDAO.getAll();
    }

}
