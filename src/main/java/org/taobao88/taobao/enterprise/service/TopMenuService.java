package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.TopMenu;

public interface TopMenuService {

	public List<TopMenu> getFullTopMenu();
	
	public TopMenu getTopMenuById(int id);
	
	public TopMenu getTopMenuByName(String name);
	
	public int addTopMenu(TopMenu topMenu);
	
	public void updateTopMenu(TopMenu topMenu);
	
	public void deleteTopMenu(TopMenu topMenu);
	
}
