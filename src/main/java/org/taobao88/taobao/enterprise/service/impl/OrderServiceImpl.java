package org.taobao88.taobao.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.OrderDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.service.OrderService;

import java.util.List;

/**
 * Created by User on 03.06.14.
 */
@Repository("orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDAO orderDAO;


    @Override
    @Transactional
    public List<OrderT> getOrders(int idUser) {
        return orderDAO.getOrders(idUser);
    }

    @Override
    @Transactional
    public List<OrderT> getOrdersForPackages(int idPackage) {
        return orderDAO.getOrdersForPackages(idPackage);
    }

    @Override
    @Transactional
    public List<OrderT> getOrdersForPack(int idPackage) {
        return orderDAO.getOrdersForPack(idPackage);
    }

    @Override
    @Transactional
    public int addOrder(OrderT orderT) {
        return orderDAO.addOrder(orderT);
    }

    @Override
    @Transactional
    public OrderT findOrderById(int id) {
        return orderDAO.findOrderById(id);
    }

    @Override
    @Transactional
    public void updateOrder(OrderT orderT) {
        orderDAO.updateOrder(orderT);
    }

    @Override
    @Transactional
    public List<OrderT> getOrdersForAdmin() {
        return orderDAO.getOrdersForAdmin();
    }

    @Override
    @Transactional
    public void deleteOrder(int orderId) {
       orderDAO.deleteOrder(orderId);
    }

    @Override
    @Transactional
    public List<OrderT> getOrdersOnStartPage(int idUser) {
        return orderDAO.getOrdersOnStartPage(idUser);
    }

    @Override
    @Transactional
    public List<OrderT> getOrdersByPackages(int idPackage) {
        return orderDAO.getOrdersByPackages(idPackage);
    }

	@Override
	public OrderT findByGoods(Goods goods) {
		return orderDAO.findByGoods(goods);
	}
}
