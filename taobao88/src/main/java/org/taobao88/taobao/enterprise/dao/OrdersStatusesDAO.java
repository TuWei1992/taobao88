package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.OrdersStatuses;

public interface OrdersStatusesDAO {

	public OrdersStatuses findById(int id);
	
	public List<OrdersStatuses> findByOrder(OrderT orderT);
	
	public int add(OrdersStatuses os);
	
	public void delete(OrdersStatuses os);
	
	public void update(OrdersStatuses os);
	
}
