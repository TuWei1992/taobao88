package org.taobao88.taobao.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.DAO.BalanceDAO;
import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;

@Repository("balanceService")
public class BalanceServiceImpl implements BalanceService {

	@Autowired private BalanceDAO balanceDAO;
	
	@Override
	public double getBalance(UserT userT) {
		double sum = balanceDAO.getBalance(userT);
		return sum;
	}

	@Override
	public int adjustBalance(BalanceOperation balanceOperation) {
		return balanceDAO.adjustBalance(balanceOperation);
	}

}
