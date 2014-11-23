package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface PriceService {

	public double getPriceOfOrder(int amount, double priceForOne);
	
	public double getFullPriceOfOrder(UserT user, double priceOrder, double weight, int amount);
	
	public double getOrderPrice(OrderT order);
	
	public double getDeliveryPrice(List<OrderT> orders, UserT user, double priceWithoutDelivery);
	
}
