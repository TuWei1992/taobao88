package org.taobao88.taobao.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.MailService;
import org.taobao88.taobao.enterprise.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/mail")
public class MailController {

	@Autowired private MailService mailService;
	@Autowired private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public void index(HttpServletRequest request) {
		
		UserT user = new UserT();
		user.setFullNameUser("Misha");
 
		Configuration cfg = (Configuration) request.getServletContext().getAttribute("freemarker_cfg");
		Template template = null;
		Map root = new HashMap();
		
		Goods g1 = new Goods();
		g1.setAmountGoods(10);
		g1.setPriceGoods(150);
		g1.setHrefGoods("http://onliner.by");
		g1.setNameGoods("New theme");
		g1.setWeightGoods(150);
		g1.setSizeGoods("XL");
		g1.setColorGoods("White");
		
		Set<OrderT> orders = new HashSet<OrderT>();
		OrderT o1 = new OrderT();
		o1.setGoods(g1);
		o1.setIdOrder(1);
		OrderT o2 = new OrderT();
		o2.setGoods(g1);
		o2.setIdOrder(2);
		
		orders.add(o1);
		orders.add(o2);
		
		PackageT p = new PackageT();
		p.setIdPackage(1);
		p.setOrders(orders);
		p.setFullPrice(150);
		p.setWeight(1.5);
		
		root.put("packageT", p);
				
		try {
			template = cfg.getTemplate("test.ftl");
			Writer out = new StringWriter();
			template.process(root, out);
			mailService.sendSimpleMessage("taobao88.for.mail@gmail.com", "doctorrokter@gmail.com", "test", out.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException te) {
			te.printStackTrace();
		}
	}
}
