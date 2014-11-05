package org.taobao88.taobao.enterprise.service;

import java.util.Set;

import org.taobao88.taobao.enterprise.entity.Sizes;

public interface SizesService {

	public Set<Sizes> prepareSizesFromString(String sizes);
	
}
