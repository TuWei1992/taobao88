package org.taobao88.taobao.enterprise.DAO;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.SideMenu;

public interface SideMenuDAO {

	public void addSideMenu(SideMenu sideMenu);
	
	public void deleteSideMenu(SideMenu sideMenu);
	
	public void updateSideMenu(SideMenu sideMenu);
	
	public SideMenu getSideMenuById(int id);
	
	public SideMenu getSideMenuByName(String menuName);
	
	public List<SideMenu> getSideMenu();
	
	public List<SideMenu> getAll();
	
	public List<SideMenu> getChildren(int parentId);
	
}