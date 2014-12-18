package org.taobao88.taobao.controllers.validators;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

public class PageRedactorValidator extends AbstractValidator {

	public List<String> validateCreateRecomendation(HttpServletRequest request, Map<String, Object> requestParams) {
		
		validateStringParam(request.getParameter("rDesc"), "rDesc", requestParams);
		validateStringParam(request.getParameter("rDescLong"), "rDescLong", requestParams);
		validateStringParam(request.getParameter("rHref"), "rHref", requestParams);
		validateStringParam(request.getParameter("rColor"), "rColor", requestParams);
		validateStringParam(request.getParameter("rSize"), "rSize", requestParams);
		
		validateDoubleParam(request.getParameter("rPrice"), "rPrice", requestParams);
		validateDoubleParam(request.getParameter("rWeight"), "rWeight", requestParams);
		
		validateIntegerParam(request.getParameter("rType"), "rType", requestParams);
		validateIntegerParam(request.getParameter("rCount"), "rCount", requestParams);
		
		return errors;
	}
	
	public List<String> validateUpdateRecomendation(HttpServletRequest request, Map<String, Object> requestParams) {
		
		validateStringParam(request.getParameter("rDesc"), "rDesc", requestParams);
		validateStringParam(request.getParameter("rDescLong"), "rDescLong", requestParams);
		validateStringParam(request.getParameter("rHref"), "rHref", requestParams);
		validateStringParam(request.getParameter("rColor"), "rColor", requestParams);
		validateStringParam(request.getParameter("rSize"), "rSize", requestParams);
		
		validateDoubleParam(request.getParameter("rPrice"), "rPrice", requestParams);
		validateDoubleParam(request.getParameter("rWeight"), "rWeight", requestParams);
		
		validateIntegerParam(request.getParameter("rCount"), "rCount", requestParams);
		
		return errors;
	}
	
	public List<String> validateCreateTopMenu(HttpServletRequest request, Map<String, Object> requestParams) {
		
		validateStringParam(request.getParameter("menuName"), "menuName", requestParams);
		validateStringParam(request.getParameter("menuDescription"), "menuDescription", requestParams);
		
		validateIntegerParam(request.getParameter("menuOrder"), "menuOrder", requestParams);
		
		return errors;
	}
}