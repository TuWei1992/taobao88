package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PostServicePrice;

public interface PostServicePriceDAO {

	public PostServicePrice findById(int id);
	
	public List<PostServicePrice> getAll();
	
	public int create(PostServicePrice postPrice);
	
	public void update(PostServicePrice postPrice);
	
	public void delete(PostServicePrice postPrice);
	
	public double getPriceByWeight(double weight, int postServiceId);
}
