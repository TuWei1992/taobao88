package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.SideMenu;

public interface SideMenuService {

public void addSideMenu(SideMenu sideMenu);
	
	public void deleteSideMenu(SideMenu sideMenu);
	
	public void updateSideMenu(SideMenu sideMenu);
	
	public SideMenu getSideMenuById(int id);
	
	public SideMenu getSideMenuByName(String menuName);
	
	public List<SideMenu> getSideMenu(String orderBy);
	
	public List<SideMenu> getAll();
	
	public List<SideMenu> getSideMenuForPage(int page, String orderBy);
	
}
