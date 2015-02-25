package org.taobao88.taobao.controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taobao88.taobao.enterprise.dao.OrderDAO;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.dao.PackagesStatusesDAO;
import org.taobao88.taobao.enterprise.dao.StatusesDAO;
import org.taobao88.taobao.enterprise.dao.UserDAO;
import org.taobao88.taobao.enterprise.dao.UuidDAO;
import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.Message;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.OrdersStatuses;
import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.PackagesStatuses;
import org.taobao88.taobao.enterprise.entity.Status;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.entity.Uuid;
import org.taobao88.taobao.enterprise.service.BalanceService;
import org.taobao88.taobao.enterprise.service.MailService;
import org.taobao88.taobao.enterprise.service.MessagesService;
import org.taobao88.taobao.enterprise.service.PackageService;
import org.taobao88.taobao.enterprise.service.UserService;
import org.taobao88.taobao.mail.Templates;

import freemarker.template.Configuration;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

	@Autowired private UserDAO userDAO;
	@Autowired private PackageService packageService;
	@Autowired private BalanceService balanceService;
	@Autowired private StatusesDAO statusesDAO;
    @Autowired private OrdersStatusesDAO ordersStatusesDAO;
    @Autowired private PackagesStatusesDAO packagesStatusesDAO;
    @Autowired private MailService mailService;
    @Autowired private UserService userService;
    @Autowired private MessagesService messageService;
    @Autowired private UuidDAO uuidDAO;
    @Autowired private OrderDAO orderDAO;
	
	@RequestMapping(value = "/deductFromAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deductFromAccount(@RequestParam ("idPackage") int idPackage,
												  HttpServletRequest request) {
		
		PackageT packageT = packageService.findPackageById(idPackage);
		UserT user = (UserT) request.getSession().getAttribute("user");
		double userBalance = balanceService.getBalance(user);
		if (userBalance < packageT.getFullPrice()) {
			return "{\"success\":false,\"message\":\"not_enough_money\"}";
		} else {
			BalanceOperation bo = new BalanceOperation();
			bo.setUserT(user);
			bo.setAmount(-packageT.getFullPrice());
			bo.setCreatedAt(new Timestamp(new Date().getTime()));
			bo.setUpdatedAt(new Timestamp(new Date().getTime()));
			bo.setReason("Payment for package #" + packageT.getIdPackage() + " from user " + user.getFullNameUser() + " " + user.getFemailUser());
			bo.setId(balanceService.adjustBalance(bo));
			
			packageT.setPurchased(1);
			packageT.setPurchasedAmount(packageT.getFullPrice());
			packageService.updatePackage(packageT);
			
			Status status = statusesDAO.findById(2);
			PackagesStatuses ps = new PackagesStatuses();
			ps.setPackageT(packageT);
			ps.setStatus(status);
			ps.setCreatedAt(new Timestamp(new Date().getTime()));
			packagesStatusesDAO.add(ps);
			
			for (OrderT o : packageT.getOrders()) {
				OrdersStatuses os = new OrdersStatuses();
				os.setOrderT(o);
				os.setStatus(status);
				os.setCreatedAt(ps.getCreatedAt());
				ordersStatusesDAO.add(os);
				o.setPurchasedAmount(o.getFullPrice());
				o.setChanged(0);
				orderDAO.updateOrder(o);
			}
			
			ResourceBundle getPath = ResourceBundle.getBundle("mail");
	        String from = getPath.getString("mailAdmin");
	        String to = getPath.getString("mailReceiver");
	        mailService.sendSimpleMessage(from, to, "Оплата посылки #" + packageT.getIdPackage(), Templates.getPaymentCompletedTemplate(user, packageT, bo));
	        
	        Map<String, Object> templateModel = new HashMap<>();
	        templateModel.put("packageT", packageT);
	        templateModel.put("userT", user);
	        templateModel.put("transaction", bo);
	        mailService.sendMessageByFreemarkerTemplate((Configuration) request.getServletContext().getAttribute("freemarker_cfg"), templateModel, user.getGmail(), "Оплата посылки #" + packageT.getIdPackage(), "payment.ftl");
	        
	        Message message = new Message();
	        message.setFromUser(userService.findUserById(1));
	        message.setToUser(user);
	        message.setMessage("Посылка #" + packageT.getIdPackage() + " успешно оплачена. Со счета списано $" + (-bo.getAmount()));
	        message.setPackageT(packageT);
	        messageService.createMessage(message);
			
			return "{\"success\":true,\"message\":\"payment_completed\"}";
		}
	}
	
	@RequestMapping(value = "/confirmBalanceAdjustment", method = RequestMethod.GET)
	public String confirmBalanceAdjustment(HttpServletRequest request,
										   Model model) {
		
		String uuidStr = request.getParameter("uuid");
		Uuid uuid = uuidDAO.findUuid(uuidStr);
		if (uuid != null) {
			int amount = Integer.parseInt(request.getParameter("amount"));
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			UserT user = userService.findUserById(userId);
			BalanceOperation bo = new BalanceOperation();
        	bo.setUserT(user);
        	bo.setAmount(amount);
        	bo.setReason("Increment by Admin");
        	balanceService.adjustBalance(bo);
        	uuidDAO.deleteUuid(uuid);
        	
        	model.addAttribute("confirmation_complete", true);
        	model.addAttribute("confirmation_user", user);
        	model.addAttribute("confirmation_amount", amount);
        	return "balancesAdmin";
		} else {
			model.addAttribute("confirmation_error", true);
			return "balancesAdmin";
		}
	}
	
	@RequestMapping(value = "/additionPay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String additionPay(@RequestParam ("idPackage") int idPackage,
							  HttpServletRequest request) {
		
		PackageT packageT = packageService.findPackageById(idPackage);
		UserT user = (UserT) request.getSession().getAttribute("user");
		double userBalance = balanceService.getBalance(user);
		if (userBalance < packageT.getFullPrice()) {
			return "{\"success\":false,\"message\":\"not_enough_money\"}";
		} else {
			int toDeduction = packageT.getFullPrice() - packageT.getPurchasedAmount();
			BalanceOperation bo = new BalanceOperation();
			bo.setUserT(user);
			bo.setAmount(-toDeduction);
			bo.setCreatedAt(new Timestamp(new Date().getTime()));
			bo.setUpdatedAt(new Timestamp(new Date().getTime()));
			bo.setReason("Addition payment for package #" + packageT.getIdPackage() + " from user " + user.getFullNameUser() + " " + user.getFemailUser());
			bo.setId(balanceService.adjustBalance(bo));
			
			packageT.setPurchased(1);
			packageT.setPurchasedAmount(packageT.getFullPrice());
			packageService.updatePackage(packageT);
			
			Status status = statusesDAO.findById(2);
			for (OrderT o : packageT.getOrders()) {
				if (o.getChanged() == 1) {
					OrdersStatuses os = new OrdersStatuses();
					os.setOrderT(o);
					os.setStatus(status);
					os.setCreatedAt(new Timestamp(new Date().getTime()));
					ordersStatusesDAO.add(os);
					o.setPurchasedAmount(o.getFullPrice());
					o.setChanged(0);
					orderDAO.updateOrder(o);
				}
			}
			
			ResourceBundle getPath = ResourceBundle.getBundle("mail");
	        String from = getPath.getString("mailAdmin");
	        String to = getPath.getString("mailReceiver");
	        mailService.sendSimpleMessage(from, to, "Оплата посылки #" + packageT.getIdPackage(), Templates.getPaymentCompletedTemplate(user, packageT, bo));
	        
	        Map<String, Object> templateModel = new HashMap<>();
	        templateModel.put("packageT", packageT);
	        templateModel.put("userT", user);
	        templateModel.put("transaction", bo);
	        mailService.sendMessageByFreemarkerTemplate((Configuration) request.getServletContext().getAttribute("freemarker_cfg"), templateModel, user.getGmail(), "Оплата посылки #" + packageT.getIdPackage(), "payment.ftl");
	        
	        Message message = new Message();
	        message.setFromUser(userService.findUserById(1));
	        message.setToUser(user);
	        message.setMessage("Посылка #" + packageT.getIdPackage() + " успешно оплачена. Со счета списано $" + (-bo.getAmount()));
	        message.setPackageT(packageT);
	        messageService.createMessage(message);
			
			return "{\"success\":true,\"message\":\"payment_completed\"}";
		}
	}
	
	@RequestMapping(value = "/returnMoney", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String returnMoney(@RequestParam ("idPackage") int idPackage,
			  				  HttpServletRequest request) {
		
		PackageT packageT = packageService.findPackageById(idPackage);
		UserT user = (UserT) request.getSession().getAttribute("user");
				
		int toDeduction = packageT.getPurchasedAmount() - packageT.getFullPrice();
		BalanceOperation bo = new BalanceOperation();
		bo.setUserT(user);
		bo.setAmount(toDeduction);
		bo.setCreatedAt(new Timestamp(new Date().getTime()));
		bo.setUpdatedAt(new Timestamp(new Date().getTime()));
		bo.setReason("Return money for package #" + packageT.getIdPackage() + " from user " + user.getFullNameUser() + " " + user.getFemailUser());
		bo.setId(balanceService.adjustBalance(bo));
		
		packageT.setPurchasedAmount(packageT.getPurchasedAmount() - bo.getAmount());
		packageService.updatePackage(packageT);
		
		Status status = statusesDAO.findById(2);
		for (OrderT o : packageT.getOrders()) {
			if (o.getChanged() == 1) {
				o.setChanged(0);
				o.setPurchasedAmount(o.getFullPrice());
				orderDAO.updateOrder(o);
				
				ordersStatusesDAO.deleteAllByOrder(o);
				OrdersStatuses os = new OrdersStatuses();
				os.setOrderT(o);
				os.setStatus(status);
				os.setCreatedAt(new Timestamp(new Date().getTime()));
				ordersStatusesDAO.add(os);
			}
		}
		
		return "{\"success\":true,\"message\":\"return_completed\"}";
	}
}
