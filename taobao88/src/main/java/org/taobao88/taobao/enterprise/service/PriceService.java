package org.taobao88.taobao.enterprise.service;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.PostService;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface PriceService {

	public double getPriceOfOrder(int amount, double priceForOne);
	
	public double getFullPriceOfOrder(UserT user, double priceOrder, double weight, int amount);
	
	public int getOrderPrice(OrderT order);
	
	public int getDeliveryPrice(List<OrderT> orders, UserT user, double priceWithoutDelivery, PostService postService);
	
	public double calculatePackageWeight(List<OrderT> orders);
	
}
