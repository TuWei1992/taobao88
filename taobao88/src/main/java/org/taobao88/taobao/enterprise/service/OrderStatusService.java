package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.OrderStatus;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 09.06.14.
 */
public interface OrderStatusService {

    int saveStatus(OrderStatus orderStatus);

    OrderStatus findStatusById(int id);

    void updateOrderStatus(OrderStatus orderStatus);

    void deleteOrderStatus(int id);

    List<OrderStatus> getOrdersStatuses(List<OrderT> orderTs);
}
