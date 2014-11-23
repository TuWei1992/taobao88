package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 03.06.14.
 */
public interface OrderDAO {

    List<OrderT> getOrders(int idUser);

    List<OrderT> getOrdersOnStartPage(int idUser);

    List<OrderT> getOrdersForPackages(int idPackage);

    List<OrderT> getOrdersForPack(int idPackage);

    int addOrder(OrderT orderT);

    OrderT findOrderById(int id);

    void updateOrder(OrderT orderT);

    List<OrderT> getOrdersForAdmin();

    void deleteOrder(int orderId);

    public List<OrderT> getOrdersByPackages(int idPackage);
    
    public OrderT findByGoods(Goods good);

}
