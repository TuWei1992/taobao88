package org.taobao88.taobao.enterprise.dao.impl;

import java.math.BigDecimal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.BalanceDAO;
import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;

@Transactional
@Repository("balanceDAO")
public class BalanceDAOImpl implements BalanceDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public int getBalance(UserT userT) {
		BigDecimal val = (BigDecimal) sessionFactory.getCurrentSession().createSQLQuery("SELECT SUM(amount) balance FROM balance_operations WHERE user_id = :user_id").addScalar("balance").setParameter("user_id", userT.getIdUser()).uniqueResult();
		if (val != null) {
			return val.intValue();
		} else {
			return 0;
		}
	}

	@Override
	public int adjustBalance(BalanceOperation balanceOperation) {
		return (int) sessionFactory.getCurrentSession().save(balanceOperation);
	}

}
