package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.TopMenuDAO;
import org.taobao88.taobao.enterprise.entity.TopMenu;

@Transactional
@Repository("topMenuDAO")
public class TopMenuDAOImpl implements TopMenuDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TopMenu> getFullTopMenu() {
		return (List<TopMenu>) sessionFactory.getCurrentSession().createQuery("from TopMenu order by menu_order").list();
	}

	@Override
	public TopMenu getTopMenuById(int id) {
		return (TopMenu) sessionFactory.getCurrentSession().createQuery("from TopMenu where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public TopMenu getTopMenuByName(String name) {
		return (TopMenu) sessionFactory.getCurrentSession().createQuery("from TopMenu where menu_name = :menu_name").setParameter("menu_name", name).uniqueResult();
	}

	@Override
	public int addTopMenu(TopMenu topMenu) {
		return (Integer) sessionFactory.getCurrentSession().save(topMenu);
	}

	@Override
	public void updateTopMenu(TopMenu topMenu) {
		sessionFactory.getCurrentSession().update(topMenu);
	}

	@Override
	public void deleteTopMenu(TopMenu topMenu) {
		sessionFactory.getCurrentSession().delete(topMenu);
	}

}
