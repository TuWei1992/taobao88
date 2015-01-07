package org.taobao88.taobao.enterprise.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.SideMenuDAO;
import org.taobao88.taobao.enterprise.entity.SideMenu;
import org.taobao88.taobao.enterprise.service.SideMenuService;

@Repository("sideMenuService")
public class SideMenuServiceImpl implements SideMenuService {

	@Autowired
	private SideMenuDAO sideMenuDAO;
	
	@Override
	public void addSideMenu(SideMenu sideMenu) {
		sideMenuDAO.addSideMenu(sideMenu);
	}

	@Override
	public void deleteSideMenu(SideMenu sideMenu) {
		sideMenuDAO.deleteSideMenu(sideMenu);
	}

	@Override
	public void updateSideMenu(SideMenu sideMenu) {
		sideMenuDAO.updateSideMenu(sideMenu);
	}

	@Override
	public SideMenu getSideMenuById(int id) {
		SideMenu sideMenu = sideMenuDAO.getSideMenuById(id);
		sideMenu.setChildren(sideMenuDAO.getChildren(sideMenu.getId()));
		sideMenu.setParent(sideMenuDAO.getSideMenuById(sideMenu.getParentId()));
		return sideMenuDAO.getSideMenuById(id);
	}

	@Override
	public SideMenu getSideMenuByName(String menuName) {
		return sideMenuDAO.getSideMenuByName(menuName);
	}

	@Override
	public List<SideMenu> getSideMenu(String orderBy) {
		List<SideMenu> sideMenu = sideMenuDAO.getSideMenu(orderBy);
		for (SideMenu menu : sideMenu) {
			menu.setChildren(sideMenuDAO.getChildren(menu.getId()));
			menu.setParent(sideMenuDAO.getSideMenuById(menu.getParentId()));
			if (menu.getChildren().size() != 0) {
				for (SideMenu m1 : menu.getChildren()) {
					m1.setChildren(sideMenuDAO.getChildren(m1.getId()));
					m1.setParent(menu);
					if (m1.getChildren().size() != 0) {
						for (SideMenu m2 : m1.getChildren()) {
							m2.setChildren(sideMenuDAO.getChildren(m2.getId()));
							m2.setParent(m1);
						}
					}
				}
			}
		}
		return sideMenu;
	}
	
	@Override
	public List<SideMenu> getAll() {
		List<SideMenu> sideMenu = sideMenuDAO.getAll();
		for (SideMenu menu : sideMenu) {
			menu.setChildren(sideMenuDAO.getChildren(menu.getId()));
			menu.setParent(sideMenuDAO.getSideMenuById(menu.getParentId()));
		}
		return sideMenu;
	}

	@Override
	public List<SideMenu> getSideMenuForPage(int page, String orderBy) {
		List<SideMenu> sideMenu = sideMenuDAO.getSideMenuByLevel(page - 1, orderBy);
		for (SideMenu menu : sideMenu) {
			menu.setChildren(sideMenuDAO.getChildren(menu.getId()));
			menu.setParent(sideMenuDAO.getSideMenuById(menu.getParentId()));
		}
		return sideMenu;
	}

}
