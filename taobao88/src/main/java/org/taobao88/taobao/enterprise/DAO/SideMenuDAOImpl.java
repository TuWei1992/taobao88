package org.taobao88.taobao.enterprise.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

	@Override
	public List<SideMenu> getSideMenu() {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu where parent_id = 0 order by menu_order").list();
	}
	
	@Override
	public List<SideMenu> getAll() {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu order by menu_order").list();
	}

	@Override
	public List<SideMenu> getChildren(int parentId) {
		return (List<SideMenu>) sessionFactory.getCurrentSession().createQuery("from SideMenu where parent_id = :parent_id order by menu_order").setParameter("parent_id", parentId).list();
	}

}
