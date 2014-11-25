package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PostServicePrice;

public interface PostRegionDAO {

	public PostServicePrice findById(int id);
	
	public List<PostServicePrice> getAll();
	
	public int create(PostServicePrice postRegion);
	
	public void update(PostServicePrice postRegion);
	
	public void delete(PostServicePrice postRegion);
}
