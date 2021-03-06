package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.SideMenuDAO;
import org.taobao88.taobao.enterprise.entity.SideMenu;

@Transactional
@Repository("sideMenuDAO")
public class SideMenuDAOImpl implements SideMenuDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public void addSideMenu(SideMenu sideMenu) {
		sessionFactory.getCurrentSession().save(sideMenu);
	}

	@Override
	public void deleteSideMenu(SideMenu sideMenu) {
		sessionFactory.getCurrentSession().delete(sideMenu);
	}

	@Override
	public void updateSideMenu(SideMenu sideMenu) {
		sessionFactory.getCurrentSession().update(sideMenu);
	}

	@Override
	public SideMenu getSideMenuById(int id) {
		return (SideMenu) sessionFactory.getCurrentSession().createQuery("from SideMenu where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public SideMenu getSideMenuByName(String menuName) {
		return (SideMenu) sessionFactory.getCurrentSession().createQuery("from SideMenu where menu_name = :menu_name").setParameter("menu_name", menuName).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SideMenu> getSideMenu(String orderBy) {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu where parent_id = 0 order by " + orderBy).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SideMenu> getAll() {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu order by menu_name").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SideMenu> getChildren(int parentId, String orderBy) {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu where parent_id = :parent_id order by " + orderBy).setParameter("parent_id", parentId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SideMenu> getSideMenuByLevel(int level, String orderBy) {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu where level = :level order by " + orderBy).setParameter("level", level).list();
	}

}
