package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.OrdersStatusesDAO;
import org.taobao88.taobao.enterprise.entity.OrderT;
import org.taobao88.taobao.enterprise.entity.OrdersStatuses;

@Transactional
@Repository("ordersStatusesDAO")
public class OrdersStatusesDAOImpl implements OrdersStatusesDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public OrdersStatuses findById(int id) {
		return (OrdersStatuses) sessionFactory.getCurrentSession().createQuery("from OrdersStatuses where id = :id").setParameter("id", id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersStatuses> findByOrder(OrderT orderT) {
		return (List<OrdersStatuses>) sessionFactory.getCurrentSession().createQuery("from OrdersStatuses where orderT.idOrder = :idOrder order by created_at").setParameter("idOrder", orderT.getIdOrder()).list();
	}

	@Override
	public int add(OrdersStatuses os) {
		return (int) sessionFactory.getCurrentSession().save(os);
	}

	@Override
	public void delete(OrdersStatuses os) {
		sessionFactory.getCurrentSession().delete(os);
	}

	@Override
	public void update(OrdersStatuses os) {
		sessionFactory.getCurrentSession().update(os);
	}

	@Override
	public void deleteAllByOrder(OrderT order) {
		sessionFactory.getCurrentSession().createQuery("delete OrdersStatuses where order_id = :order_id").setParameter("order_id", order.getIdOrder()).executeUpdate();		
	}

}
