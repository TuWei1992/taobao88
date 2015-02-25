package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.ShippingAddress;

public interface ShippingAddressDAO {

	public ShippingAddress findById(int id);
	
	public List<ShippingAddress> getAll();
	
	public int add(ShippingAddress shippingAddress);
	
	public void delete(ShippingAddress shippingAddress);
	
	public void update(ShippingAddress shippingAddress);
	
}
