package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.Uuid;

public interface UuidDAO {

	public void addUuid(Uuid uuid);
	
	public Uuid findUuid(String uuid);
	
	public void deleteUuid(Uuid uuid);
	
}
