package org.taobao88.taobao.controllers;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.dao.PagesContentDAO;
import org.taobao88.taobao.enterprise.entity.PageContent;
import org.taobao88.taobao.enterprise.entity.SideMenu;
import org.taobao88.taobao.enterprise.service.SideMenuService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/sideMenu")
public class SideMenuController {

	@Autowired private SideMenuService sideMenuService;
	@Autowired private PagesContentDAO pagesContentDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		
		int page = 1;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		model.addAttribute("curr_page", page);
		model.addAttribute("total_pages", 4);
		model.addAttribute("side_menu", sideMenuService.getSideMenuForPage(page, "parent_id, menu_order"));
		model.addAttribute("side_menu_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createMenu", method = RequestMethod.GET)
	public String createMenu(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
						     Model model, 
						     HttpServletRequest request) {
		
		if (request.getParameter("parentId") != null) {
			int parentId = Integer.parseInt(request.getParameter("parentId"));
			model.addAttribute("parent", sideMenuService.getSideMenuById(parentId));
		} else {
			model.addAttribute("side_menu", sideMenuService.getAll());
		}
		model.addAttribute("curr_page", page);
		model.addAttribute("side_menu_create", true);		
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createMenu/doCreate", method = RequestMethod.POST)
	public String doCreate(@RequestParam("menuHref") String menuHref,
							 @RequestParam("menuName") String menuName,
							 @RequestParam("menuOrder") int menuOrder,
							 @RequestParam("parentId") int parentId,
							 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
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
		return "redirect:/admin/pageRedactor/sideMenu?page=" + page;
	}

	@RequestMapping(value = "/deleteMenu", method = RequestMethod.GET)
	public String deleteMenu(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
							 @RequestParam("id") int id, 
							 Model model) {
		sideMenuService.deleteSideMenu(sideMenuService.getSideMenuById(id));
		return "redirect:/admin/pageRedactor/sideMenu?page=" + page;
	}

	@RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
	public String updateMenu(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
						     @RequestParam("id") int id, Model model) {
		model.addAttribute("side_menu", sideMenuService.getAll());
		model.addAttribute("menu", sideMenuService.getSideMenuById(id));
		model.addAttribute("side_menu_update", true);
		model.addAttribute("curr_page", page);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateMenu/doUpdate", method = RequestMethod.POST)
	public String doUpdateSideMenu(@RequestParam("id") int id,
								   @RequestParam("menuName") String menuName,
								   @RequestParam("menuHref") String menuHref,
								   @RequestParam("menuOrder") int menuOrder,
								   @RequestParam("parentId") int parentId,
								   @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		SideMenu sideMenu = sideMenuService.getSideMenuById(id);
		sideMenu.setMenuName(menuName);
		sideMenu.setMenuHref(menuHref);
		sideMenu.setMenuOrder(menuOrder);
		sideMenu.setParentId(parentId);
		sideMenuService.updateSideMenu(sideMenu);
		return "redirect:/admin/pageRedactor/sideMenu?page=" + page;
	}
	
	@RequestMapping(value = "/other", method = RequestMethod.GET)
	public String anotherIndex(Model model) {
		model.addAttribute("another_menu_index", true);
		model.addAttribute("anotherMenu", pagesContentDAO.findContentByPageName("anotherMenu"));
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/other/update", method = RequestMethod.GET)
	public String anotherUpdate(Model model) {
		model.addAttribute("another_menu_update", true);
		model.addAttribute("anotherMenu", pagesContentDAO.findContentByPageName("anotherMenu"));
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/other/doUpdate", method = RequestMethod.POST)
	public String anotherDoUpdate(@RequestParam ("content") String content,
								  @RequestParam ("page") String page,
							      Model model) {
		PageContent anotherMenu = pagesContentDAO.findContentByPageName(page);
		anotherMenu.setContent(content);
		anotherMenu.setUpdated_at(new Timestamp(new Date().getTime()));
		pagesContentDAO.updatePageContent(anotherMenu);
		return "redirect:/admin/pageRedactor/sideMenu/other";
	}
	
}
