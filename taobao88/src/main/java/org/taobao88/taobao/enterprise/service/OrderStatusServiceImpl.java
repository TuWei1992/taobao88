package org.taobao88.taobao.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.DAO.OrderStatusDAO;
import org.taobao88.taobao.enterprise.entity.OrderStatus;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 09.06.14.
 */
@Repository("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService{

    @Autowired
    OrderStatusDAO orderStatusDAO;

    @Override
    @Transactional
    public int saveStatus(OrderStatus orderStatus) {
        orderStatusDAO.saveStatus(orderStatus);
        return orderStatusDAO.getIdStatus(orderStatus);
    }

    @Override
    @Transactional
    public OrderStatus findStatusById(int id) {
        return orderStatusDAO.findStatusById(id);
    }

    @Override
    @Transactional
    public void updateOrderStatus(OrderStatus orderStatus) {
        orderStatusDAO.updateOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    public void deleteOrderStatus(int id) {
        orderStatusDAO.deleteOrderStatus(id);
    }

    @Override
    @Transactional
    public List<OrderStatus> getOrdersStatuses(List<OrderT> orderTs) {
        return orderStatusDAO.getOrdersStatuses(orderTs);
    }
}
