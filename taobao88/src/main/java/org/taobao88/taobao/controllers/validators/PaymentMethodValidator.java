package org.taobao88.taobao.controllers.validators;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PaymentMethodValidator extends AbstractValidator {

	public List<String> validateCreatePaymentMethod(HttpServletRequest request, Map<String, Object> requestParams) {
		
		validateStringParam(request.getParameter("methodName"), "methodName", requestParams);
		validateStringParam(request.getParameter("methodDescription"), "methodDescription", requestParams);
		
		return errors;
	}
	
}
