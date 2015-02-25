package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.Sizes;

public interface SizesDAO {

	public Sizes getSizeById(int id);
	
	public Sizes getSizesByName(String sizeName);
	
	public List<Sizes> getSizes();
	
	public int addSize(Sizes size);
	
	public void updateSize(Sizes size);
	
	public void deleteSize(Sizes size);
}
