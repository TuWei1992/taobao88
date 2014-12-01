package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PackageT;
import org.taobao88.taobao.enterprise.entity.PackagesStatuses;

public interface PackagesStatusesDAO {

	public List<PackagesStatuses> findByOrder(PackageT packageT);
	
	public int add(PackagesStatuses ps);
	
	public void delete(PackagesStatuses ps);
	
	public void update(PackagesStatuses ps);
	
}
