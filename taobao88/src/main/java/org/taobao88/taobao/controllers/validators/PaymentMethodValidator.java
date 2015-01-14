package org.taobao88.taobao.controllers.validators;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PaymentMethodValidator extends AbstractValidator {

	public PaymentMethodValidator() {
		this.requestParams = new HashMap<String, Object>();
	}
	
	public List<String> validateCreatePaymentMethod(HttpServletRequest request) {
		
		validateStringParam(request.getParameter("methodName"), "methodName");
		validateStringParam(request.getParameter("methodDescription"), "methodDescription");
		
		return errors;
	}
	
}
