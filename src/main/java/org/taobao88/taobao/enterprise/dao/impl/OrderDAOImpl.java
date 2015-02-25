package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.OrderDAO;
import org.taobao88.taobao.enterprise.entity.Goods;
import org.taobao88.taobao.enterprise.entity.OrderT;

import java.util.List;

/**
 * Created by User on 03.06.14.
 */
@Transactional
@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO{

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrders(int idUser) {
        return sessionFactory.getCurrentSession().createQuery("from OrderT where idUser=:articleId ").setParameter("articleId", idUser).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrdersByPackages(int idPackage) {
        return sessionFactory.getCurrentSession().createQuery("from OrderT where idPackage=:articleId ").setParameter("articleId", idPackage).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrdersForPack(int idUser) {
        return sessionFactory.getCurrentSession().createQuery("from OrderT where idUser = :articleId AND approve = 'true' ").setParameter("articleId", idUser).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrdersOnStartPage(int idUser) {
        return sessionFactory.getCurrentSession().createQuery("from OrderT where idUser = :articleId AND approve = 'false' ").setParameter("articleId", idUser).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrdersForPackages(int idPackage) {
        return sessionFactory.getCurrentSession().createQuery("from OrderT where idPackage=:articleId ").setParameter("articleId", idPackage).list();
    }

    @Override
    public int addOrder(OrderT orderT) {
        sessionFactory.getCurrentSession().save(orderT);
        return (Integer) sessionFactory.getCurrentSession().getIdentifier(orderT);
    }

    @Override
    public OrderT findOrderById(int id) {
        return (OrderT) sessionFactory.getCurrentSession().get(OrderT.class,id);
    }

    @Override
    public void updateOrder(OrderT orderT) {
        sessionFactory.getCurrentSession().update(orderT);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<OrderT> getOrdersForAdmin() {
        String query = "";
        query  = "from OrderT where approve='true'";

        return (List<OrderT>) sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @Override
    public void deleteOrder(int orderId) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM ordert WHERE orderT_id = :orderT_id").setParameter("orderT_id", orderId).executeUpdate();
    }

	@Override
	public OrderT findByGoods(Goods good) {
		return (OrderT) sessionFactory.getCurrentSession().createQuery("from OrderT where goods_id = :goods_id").setParameter("goods_id", good.getIdGoods()).uniqueResult();
	}
}
