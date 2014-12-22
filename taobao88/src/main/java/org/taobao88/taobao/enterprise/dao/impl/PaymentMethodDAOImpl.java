package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PaymentMethodDAO;
import org.taobao88.taobao.enterprise.entity.PaymentMethod;

@Transactional
@Repository("paymentMethodDAO")
public class PaymentMethodDAOImpl implements PaymentMethodDAO {

	@Autowired private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentMethod> getAll() {
		return (List<PaymentMethod>) sessionFactory.getCurrentSession().createQuery("from PaymentMethod").list();
	}

	@Override
	public PaymentMethod findById(int id) {
		return (PaymentMethod) sessionFactory.getCurrentSession().createQuery("from PaymentMethod where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public PaymentMethod findByName(String methodName) {
		return (PaymentMethod) sessionFactory.getCurrentSession().createQuery("from PaymentMethod where method_name = :method_name").setParameter("method_name", methodName).uniqueResult();
	}

	@Override
	public int create(PaymentMethod method) {
		return (int) sessionFactory.getCurrentSession().save(method);
	}

	@Override
	public void update(PaymentMethod method) {
		sessionFactory.getCurrentSession().update(method);	
	}

	@Override
	public void delete(PaymentMethod method) {
		sessionFactory.getCurrentSession().delete(method);		
	}
	
}
