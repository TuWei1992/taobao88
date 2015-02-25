package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.OrderStatusDAO;
import org.taobao88.taobao.enterprise.entity.OrderStatus;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 09.06.14.
 */
@Transactional
@Repository("orderStatusDAO")
public class OrderStatusDAOImpl implements OrderStatusDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveStatus(OrderStatus orderStatus) {
        sessionFactory.getCurrentSession().save(orderStatus);
    }

    @Override
    public int getIdStatus(OrderStatus orderStatus) {
        return (Integer) sessionFactory.getCurrentSession().getIdentifier(orderStatus);
    }

    @Override
    public OrderStatus findStatusById(int id) {
        return (OrderStatus) sessionFactory.getCurrentSession().get(OrderStatus.class, id);
    }

    @Override
    public void updateOrderStatus(OrderStatus orderStatus) {
        sessionFactory.getCurrentSession().update(orderStatus);
    }

    @Override
    public void deleteOrderStatus(int id) {
        OrderStatus orderStatus = findStatusById(id);
        sessionFactory.getCurrentSession().delete(orderStatus);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderStatus> getOrdersStatuses(List<OrderT> orderTs) {
        if(orderTs.size()!=0){
            String query = "from OrderStatus where ";

            for(int i=0;i<orderTs.size();i++){
                if(i<orderTs.size()-1){
                    query+="idOrderStatus='"+orderTs.get(i).getIdOrderStatus()+"' OR ";
                }else{
                    query+="idOrderStatus='"+orderTs.get(i).getIdOrderStatus()+"'";
                }
            }
            System.out.println("ORDERSTATUSES = "+query);
            return sessionFactory.getCurrentSession().createQuery(query)
                    .list();
        }

        return null;
    }
}
