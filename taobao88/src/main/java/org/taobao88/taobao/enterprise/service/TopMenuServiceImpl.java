package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.DAO.TopMenuDAO;
import org.taobao88.taobao.enterprise.entity.TopMenu;

@Repository("topMenuService")
public class TopMenuServiceImpl implements TopMenuService {

	@Autowired
	private TopMenuDAO topMenuDAO;
	
	@Override
	public List<TopMenu> getFullTopMenu() {
		return topMenuDAO.getFullTopMenu();
	}

	@Override
	public TopMenu getTopMenuById(int id) {
		return topMenuDAO.getTopMenuById(id);
	}

	@Override
	public TopMenu getTopMenuByName(String name) {
		return topMenuDAO.getTopMenuByName(name);
	}

	@Override
	public int addTopMenu(TopMenu topMenu) {
		return topMenuDAO.addTopMenu(topMenu);
	}

	@Override
	public void updateTopMenu(TopMenu topMenu) {
		topMenuDAO.updateTopMenu(topMenu);
	}

	@Override
	public void deleteTopMenu(TopMenu topMenu) {
		topMenuDAO.deleteTopMenu(topMenu);
	}

}
