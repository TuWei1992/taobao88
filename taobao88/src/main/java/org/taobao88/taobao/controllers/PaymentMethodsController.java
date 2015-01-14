package org.taobao88.taobao.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.taobao88.taobao.controllers.validators.PaymentMethodValidator;
import org.taobao88.taobao.enterprise.dao.PaymentMethodDAO;
import org.taobao88.taobao.enterprise.entity.PaymentMethod;

@Controller
@RequestMapping(value = "/admin/paymentMethods")
public class PaymentMethodsController extends MainController {

	@Autowired private PaymentMethodDAO paymentMethodDAO;
	private PaymentMethodValidator validator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("paymentMethods", paymentMethodDAO.getAll());
		return "payment/index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "payment/create";
	}
	
	@RequestMapping(value = "/create/doCreate", method = RequestMethod.POST)
	public String doCreate(Model model, HttpServletRequest request) {
		
		validator = new PaymentMethodValidator();
		List<String> errors = validator.validateCreatePaymentMethod(request);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			return "payment/create";
		}
		
		PaymentMethod method = new PaymentMethod();
		method.setMethodName(validator.getString("methodName"));
		method.setMethodDescription(validator.getString("methodDescription"));
		paymentMethodDAO.create(method);
		
		return "redirect:/admin/paymentMethods";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam ("id") int id, 
						 Model model) {
		model.addAttribute("method", paymentMethodDAO.findById(id));
		return "payment/update";
	}
	
	@RequestMapping(value = "/update/doUpdate", method = RequestMethod.POST)
	public String doUpdate(@RequestParam ("id") int id,
						   Model model,
					       HttpServletRequest request) {
		
		validator = new PaymentMethodValidator();		
		List<String> errors = validator.validateCreatePaymentMethod(request);
		if (errors.size() != 0) {
			model.addAttribute("errors", toJSArray(errors.toArray()));
			return "payment/update";
		}
		
		PaymentMethod method = paymentMethodDAO.findById(id);
		method.setMethodName(validator.getString("methodName"));
		method.setMethodDescription(validator.getString("methodDescription"));
		paymentMethodDAO.update(method);
		
		return "redirect:/admin/paymentMethods";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam ("id") int id) {
		paymentMethodDAO.delete(paymentMethodDAO.findById(id));
		return "redirect:/admin/paymentMethods";
	}
	
}
