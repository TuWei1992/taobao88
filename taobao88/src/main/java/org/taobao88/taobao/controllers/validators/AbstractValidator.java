package org.taobao88.taobao.controllers.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractValidator {

	protected List<String> errors = new ArrayList<String>();
	
	protected void validateStringParam(Object param, String paramName, Map<String, Object> requestParams) {
		String paramStr = null;
		if (param != null) {
			paramStr = (String) param;
			if (!paramStr.isEmpty()) {
				requestParams.put(paramName, paramStr);
			} else {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
	protected void validateDoubleParam(Object param, String paramName, Map<String, Object> requestParams) {
		double paramDouble = 0;
		if (param != null) {
			try {
				paramDouble = Double.parseDouble((String) param);
				requestParams.put(paramName, paramDouble);
			} catch (Exception e) {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
	protected void validateIntegerParam(Object param, String paramName, Map<String, Object> requestParams) {
		int paramDouble = 0;
		if (param != null) {
			try {
				paramDouble = Integer.parseInt((String) param);
				requestParams.put(paramName, paramDouble);
			} catch (Exception e) {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
}
