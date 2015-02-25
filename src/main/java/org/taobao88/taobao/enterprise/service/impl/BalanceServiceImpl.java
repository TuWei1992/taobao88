package org.taobao88.taobao.enterprise.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.taobao88.taobao.enterprise.dao.BalanceDAO;
import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;
import org.taobao88.taobao.enterprise.service.BalanceService;

@Repository("balanceService")
public class BalanceServiceImpl implements BalanceService {

	@Autowired private BalanceDAO balanceDAO;
	
	@Override
	public int getBalance(UserT userT) {
		int sum = balanceDAO.getBalance(userT);
		return sum;
	}

	@Override
	public int adjustBalance(BalanceOperation balanceOperation) {
		balanceOperation.setCreatedAt(new Timestamp(new Date().getTime()));
		balanceOperation.setUpdatedAt(new Timestamp(new Date().getTime()));
		return balanceDAO.adjustBalance(balanceOperation);
	}

}
