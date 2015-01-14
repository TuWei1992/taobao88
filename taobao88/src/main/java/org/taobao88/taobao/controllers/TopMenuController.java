package org.taobao88.taobao.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.controllers.validators.PageRedactorValidator;
import org.taobao88.taobao.enterprise.entity.TopMenu;
import org.taobao88.taobao.enterprise.service.TopMenuService;

@Controller
@RequestMapping(value = "/admin/pageRedactor/topMenu")
public class TopMenuController extends MainController {

	@Autowired private TopMenuService topMenuService;
	private PageRedactorValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		model.addAttribute("top_menu_index", true);
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/createTopMenu", method = RequestMethod.GET)
	public String createTopMenu(Model model) {
		model.addAttribute("top_menu_create", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/createTopMenu/doCreate", method = RequestMethod.POST)
	public String doCreateTopMenu(HttpServletRequest request, Model model) {
		
		validator = new PageRedactorValidator();
		List<String> errors = validator.validateCreateTopMenu(request);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			model.addAttribute("top_menu_create", true);
			return "pageRedactor";
		}
		
		try {
			TopMenu topMenu = new TopMenu();
			topMenu.setMenuName(validator.getString("menuName"));
			topMenu.setMenuDescription(validator.getString("menuDescription"));
			topMenu.setMenuOrder(validator.getInt("menuOrder"));
			topMenuService.addTopMenu(topMenu);
			return "redirect:/admin/pageRedactor/topMenu";
		} catch (Exception e) {
			model.addAttribute("unknown_error", true);
			model.addAttribute("top_menu_create", true);
			return "pageRedactor";
		}
	}

	@RequestMapping(value = "/updateTopMenu", method = RequestMethod.GET)
	public String updateTopMenu(@RequestParam("id") int id, Model model) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		model.addAttribute("topMenu", topMenu);
		model.addAttribute("top_menu_update", true);
		return "pageRedactor";
	}

	@RequestMapping(value = "/updateTopMenu/doUpdate", method = RequestMethod.POST)
	public String doUpdateTopMenu(@RequestParam("id") int id,
								  @RequestParam("menuName") String menuName,
								  @RequestParam("menuDescription") String menuDescription,
								  @RequestParam("menuOrder") int menuOrder) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		topMenu.setMenuName(menuName);
		topMenu.setMenuDescription(menuDescription);
		topMenu.setMenuOrder(menuOrder);
		topMenuService.updateTopMenu(topMenu);
		return "redirect:/admin/pageRedactor/topMenu";
	}

	@RequestMapping(value = "/deleteTopMenu", method = RequestMethod.GET)
	public String deleteTopMenu(@RequestParam("id") int id) {
		TopMenu topMenu = topMenuService.getTopMenuById(id);
		topMenuService.deleteTopMenu(topMenu);
		return "redirect:/admin/pageRedactor/topMenu";
	}

	@RequestMapping(value = "/viewTopMenu", method = RequestMethod.GET)
	public String viewTopMenu(@RequestParam("id") int id, Model model) {
		model.addAttribute("topMenu", topMenuService.getTopMenuById(id));
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}

	@RequestMapping(value = "/previewTopMenu", method = RequestMethod.POST)
	public String previewTopMenu(
			@RequestParam("menuDescription") String menuDescription, Model model) {
		TopMenu topMenu = new TopMenu();
		topMenu.setMenuDescription(menuDescription);
		model.addAttribute("topMenu", topMenu);
		model.addAttribute("topMenuList", topMenuService.getFullTopMenu());
		return "_template";
	}
	
}
