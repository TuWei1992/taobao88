package org.taobao88.taobao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.enterprise.dao.PagesContentDAO;
import org.taobao88.taobao.enterprise.entity.PageContent;

@Controller
@RequestMapping(value = "/admin/pageRedactor/userAccount")
public class UserAccountController {
	
	@Autowired private PagesContentDAO pagesContentDAO;	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user_account_index", true);
		model.addAttribute("privateOffice", pagesContentDAO.findContentByPageName("privateOffice"));
		return "pageRedactor";
	}
	
	@RequestMapping(value = "/updatedPageContent", method = RequestMethod.POST)
	public String updatedPageContent(@RequestParam("page") String page,
									 @RequestParam("content") String content) {
		PageContent pageContent = pagesContentDAO.findContentByPageName(page);
		pageContent.setContent(content);
		pagesContentDAO.updatePageContent(pageContent);
		return "redirect:/admin/pageRedactor/userAccount";
	}

}
