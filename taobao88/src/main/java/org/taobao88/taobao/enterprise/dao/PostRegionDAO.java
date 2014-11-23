package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PostRegion;

public interface PostRegionDAO {

	public PostRegion findById(int id);
	
	public List<PostRegion> getAll();
	
	public int create(PostRegion postRegion);
	
	public void update(PostRegion postRegion);
	
	public void delete(PostRegion postRegion);
}
