package org.taobao88.taobao.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.entity.SideMenu;
import org.taobao88.taobao.enterprise.service.SideMenuService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/sideMenu")
public class SideMenuController {

	@Autowired private SideMenuService sideMenuService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("side_menu", sideMenuService.getAll());
		model.addAttribute("side_menu_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createMenu", method = RequestMethod.GET)
	public String createMenu(Model model) {
		model.addAttribute("side_menu", sideMenuService.getAll());
		model.addAttribute("side_menu_create", true);		
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createMenu/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam("menuHref") String menuHref,
							 @RequestParam("menuName") String menuName,
							 @RequestParam("menuOrder") int menuOrder,
							 @RequestParam("parentId") int parentId,
							 HttpServletRequest request,
							 Model model) {
		SideMenu sideMenu = new SideMenu();
		sideMenu.setMenuHref(menuHref);
		sideMenu.setMenuName(menuName);
		sideMenu.setMenuOrder(menuOrder);
		sideMenu.setParentId(parentId);
		
		if (parentId != 0) {
			SideMenu parent = sideMenuService.getSideMenuById(parentId);
			sideMenu.setLevel(parent.getLevel() + 1);
		}
		
		sideMenuService.addSideMenu(sideMenu);
		return "redirect:/admin/pageRedactor/sideMenu";
	}

	@RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
	public String deleteMenu(@RequestParam("id") int id, Model model) {
		sideMenuService.deleteSideMenu(sideMenuService.getSideMenuById(id));
		return "redirect:/admin/pageRedactor/sideMenu";
	}

	@RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
	public String updateMenu(@RequestParam("id") int id, Model model) {
		model.addAttribute("menu", sideMenuService.getSideMenuById(id));
		model.addAttribute("side_menu_update", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateMenu/doUpdate", method = RequestMethod.POST)
	public String doUpdateSideMenu(@RequestParam("id") int id,
								   @RequestParam("menuName") String menuName,
								   @RequestParam("menuHref") String menuHref,
								   @RequestParam("menuOrder") int menuOrder) {
		SideMenu sideMenu = sideMenuService.getSideMenuById(id);
		sideMenu.setMenuName(menuName);
		sideMenu.setMenuHref(menuHref);
		sideMenu.setMenuOrder(menuOrder);
		sideMenuService.updateSideMenu(sideMenu);
		return "redirect:/admin/pageRedactor/sideMenu";
	}
	
}
