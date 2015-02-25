package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PaymentMethod;

public interface PaymentMethodDAO {

	public List<PaymentMethod> getAll();
	
	public PaymentMethod findById(int id);
	
	public PaymentMethod findByName(String methodName);
	
	public int create(PaymentMethod method);
	
	public void update(PaymentMethod method);
	
	public void delete(PaymentMethod method);
}
