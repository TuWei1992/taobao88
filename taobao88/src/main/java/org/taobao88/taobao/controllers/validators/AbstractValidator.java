package org.taobao88.taobao.controllers.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractValidator {

	protected List<String> errors = new ArrayList<String>();
	protected Map<String, Object> requestParams;
	
	protected void validateStringParam(Object param, String paramName) {
		String paramStr = null;
		if (param != null) {
			paramStr = (String) param;
			if (!paramStr.isEmpty()) {
				this.requestParams.put(paramName, paramStr);
			} else {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
	protected void validateDoubleParam(Object param, String paramName) {
		double paramDouble = 0;
		if (param != null) {
			try {
				paramDouble = Double.parseDouble((String) param);
				this.requestParams.put(paramName, paramDouble);
			} catch (Exception e) {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
	protected void validateIntegerParam(Object param, String paramName) {
		int paramDouble = 0;
		if (param != null) {
			try {
				paramDouble = Integer.parseInt((String) param);
				this.requestParams.put(paramName, paramDouble);
			} catch (Exception e) {
				errors.add(paramName);
			}
		} else {
			errors.add(paramName);
		}
	}
	
	public String getString(String paramName) {
		return (String) requestParams.get(paramName);
	}
	
	public int getInt(String paramName) {
		return (int) requestParams.get(paramName);
	}
	
	public double getDouble(String paramName) {
		return (double) requestParams.get(paramName);
	}
}
