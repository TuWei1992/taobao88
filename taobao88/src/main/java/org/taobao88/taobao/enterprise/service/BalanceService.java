package org.taobao88.taobao.enterprise.service;

import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface BalanceService {

public double getBalance(UserT userT);
	
	public int adjustBalance(BalanceOperation balanceOperation);
	
}
