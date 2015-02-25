package org.taobao88.taobao.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController extends  MainController{
	
	@RequestMapping(value="/order", method = RequestMethod.GET)
	 public ModelAndView adminPage() {
	
	  return new ModelAndView("order");
	 
	 }

}
