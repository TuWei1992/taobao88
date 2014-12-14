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
import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.MessagesService;
import org.taobao88.taobao.enterprise.service.PackageService;
import org.taobao88.taobao.enterprise.service.UserService;

@Controller
@RequestMapping(value = "/messages")
public class MessagesController {
	
	@Autowired private UserService userService;
	@Autowired private PackageService packageService;
	@Autowired private MessagesService messagesService;
	
	@RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    public String sendMessage(@RequestParam ("toUser") int toUser,
    						  @RequestParam ("idpackage") int idpackage,
    						  HttpServletRequest request, Model model) {
    	UserT fromUser = null;
    	if (request.getSession().getAttribute("currentIdUser") == null) {
    		fromUser = userService.findUserById(1);
    	} else {
    		fromUser = userService.findUserById((int) request.getSession().getAttribute("currentIdUser"));
    	}
    	if (fromUser.getIdUser() == 1) {
    		model.addAttribute("fromUser", fromUser);
    		model.addAttribute("toUser", userService.findUserById(toUser));
    		model.addAttribute("packageT", packageService.findPackageById(idpackage));
    		return "messages/messageAdmin";
    	} else {
    		return "messages/messageUser";
    	}
    }
    
    @RequestMapping(value = "confirmMessage", method = RequestMethod.POST)
    public String confirmMessage(@RequestParam ("toUser") int toUser, 
    							 @RequestParam ("fromUser") int fromUser, 
    							 @RequestParam("idpackage") int idpackage, 
    							 @RequestParam ("message") String message) {
    	Message m = new Message();
    	m.setFromUser(userService.findUserById(fromUser));
		m.setToUser(userService.findUserById(toUser));
		m.setMessage(message);
		m.setPackageT(packageService.findPackageById(idpackage));
		m.setCreatedAt(new Timestamp(new Date().getTime()));
		m.setUpdatedAt(new Timestamp(new Date().getTime()));
		m.setReaded(0);
		messagesService.createMessage(m);
    	if (fromUser == 1) {
    		return "redirect:/admin/showMessages";
    	} else {
    		return "redirect:/privateOffice/showMessages";
    	}
    }

}
