package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Status;

public interface StatusesDAO {

	public Status findById(int id);
	
	public List<Status> getAll();
}
