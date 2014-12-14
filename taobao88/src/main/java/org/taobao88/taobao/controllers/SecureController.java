package org.taobao88.taobao.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taobao88.taobao.enterprise.dao.UserDAO;
import org.taobao88.taobao.enterprise.dao.UserRoleDAO;
import org.taobao88.taobao.enterprise.entity.UserRole;
import org.taobao88.taobao.enterprise.entity.UserT;

@Controller
@RequestMapping(value = "/secure")
public class SecureController {

	@Autowired private UserDAO userDAO;
	@Autowired private UserRoleDAO userRoleDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "secure/index";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String auth(HttpServletRequest request, Model model) {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if ((login != null && password != null) && (!login.isEmpty() && !password.isEmpty())) {
			UserT admin = userDAO.findUserByCredentials(login, password);
			if (admin != null) {
				UserRole role = userRoleDAO.findRole(admin);
				if (role != null && role.getAuthority().equals("ROLE_ADMIN")) {
					request.getSession().setAttribute("admin", admin);
					return "redirect:/admin";
				}
			}
		}
		model.addAttribute("incorrect_credentials", true);
		return "secure/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("admin", null);
		return "redirect:/admin";
	}
}
