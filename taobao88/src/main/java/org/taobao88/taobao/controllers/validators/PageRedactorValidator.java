package org.taobao88.taobao.controllers.validators;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PageRedactorValidator extends AbstractValidator {
	
	public PageRedactorValidator() {
		this.requestParams = new HashMap<String, Object>();
	}

	public List<String> validateCreateRecomendation(HttpServletRequest request) {
		
		validateStringParam(request.getParameter("rDesc"), "rDesc");
		validateStringParam(request.getParameter("rDescLong"), "rDescLong");
		validateStringParam(request.getParameter("rHref"), "rHref");
		validateStringParam(request.getParameter("rColor"), "rColor");
		validateStringParam(request.getParameter("rSize"), "rSize");
		
		validateDoubleParam(request.getParameter("rPrice"), "rPrice");
		validateDoubleParam(request.getParameter("rWeight"), "rWeight");
		
		validateIntegerParam(request.getParameter("rType"), "rType");
		validateIntegerParam(request.getParameter("rCount"), "rCount");
		
		return errors;
	}
	
	public List<String> validateUpdateRecomendation(HttpServletRequest request) {
		
		validateStringParam(request.getParameter("rDesc"), "rDesc");
		validateStringParam(request.getParameter("rDescLong"), "rDescLong");
		validateStringParam(request.getParameter("rHref"), "rHref");
		validateStringParam(request.getParameter("rColor"), "rColor");
		validateStringParam(request.getParameter("rSize"), "rSize");
		
		validateDoubleParam(request.getParameter("rPrice"), "rPrice");
		validateDoubleParam(request.getParameter("rWeight"), "rWeight");
		
		validateIntegerParam(request.getParameter("rCount"), "rCount");
		
		return errors;
	}
	
	public List<String> validateCreateTopMenu(HttpServletRequest request) {
		
		validateStringParam(request.getParameter("menuName"), "menuName");
		validateStringParam(request.getParameter("menuDescription"), "menuDescription");
		
		validateIntegerParam(request.getParameter("menuOrder"), "menuOrder");
		
		return errors;
	}
	
	public List<String> validateUpdateBrand(HttpServletRequest request) {
		validateStringParam(request.getParameter("bDesc"), "bDesc");
		return errors;
	}
}