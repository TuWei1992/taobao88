package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface BalanceDAO {

	public int getBalance(UserT userT);
	
	public int adjustBalance(BalanceOperation balanceOperation);
	
}
