package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.UserT;

public interface PriceService {

	public double getPriceOfOrder(int amount, double priceForOne);
	
	public double getFullPriceOfOrder(UserT user, double priceOrder, double weight, int amount);
	
}
