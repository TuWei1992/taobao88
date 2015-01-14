package org.taobao88.taobao.controllers.validators;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class OfficeValidator extends AbstractValidator {
	
	public OfficeValidator() {
		this.requestParams = new HashMap<String, Object>();
	}

	public List<String> validateOrder(HttpServletRequest request) {
		
		validateStringParam(request.getParameter("HREFGOODS"), "HREFGOODS");
		validateStringParam(request.getParameter("NAMEGOODS"), "NAMEGOODS");
		validateStringParam(request.getParameter("CHINAGOODS"), "CHINAGOODS");
		validateStringParam(request.getParameter("COLORGOODS"), "COLORGOODS");
		validateStringParam(request.getParameter("SIZEGOODS"), "SIZEGOODS");
		
		validateDoubleParam(request.getParameter("PRICEGOODS"), "PRICEGOODS");
		validateDoubleParam(request.getParameter("WEIGHTGOODS"), "WEIGHTGOODS");
		
		validateIntegerParam(request.getParameter("AMOUNTGOODS"), "AMOUNTGOODS");
		
		return errors;
	}
	
}
