package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.UserDAO;
import org.taobao88.taobao.enterprise.entity.UserT;

import java.util.List;

/**
 * Created by User on 02.06.14.
 */
@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveNewUser(UserT user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UserT> getId(String name) {
       return sessionFactory.getCurrentSession().createQuery("from UserT where username=:articleId").setParameter("articleId", name).list();
    }

    @Override
    public UserT findUserById(int id) {
        return (UserT) sessionFactory.getCurrentSession().get(UserT.class,id);
    }

    @Override
    public void updateUser(UserT userT) {
        sessionFactory.getCurrentSession().update(userT);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UserT> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserT").list();
    }
}
