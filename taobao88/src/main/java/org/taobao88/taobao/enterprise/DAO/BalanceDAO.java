package org.taobao88.taobao.enterprise.DAO;

import org.taobao88.taobao.enterprise.entity.BalanceOperation;
import org.taobao88.taobao.enterprise.entity.UserT;

public interface BalanceDAO {

	public double getBalance(UserT userT);
	
	public int adjustBalance(BalanceOperation balanceOperation);
	
}
