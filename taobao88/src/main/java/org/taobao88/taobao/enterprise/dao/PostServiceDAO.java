package org.taobao88.taobao.enterprise.dao;

import java.util.List;

import org.taobao88.taobao.enterprise.entity.PostService;

public interface PostServiceDAO {

	public PostService findById(int id);
	
	public PostService findByNameAndCountry(String serviceName, int countryId);
	
	public List<PostService> findByCountry(int countryId);
	
	public List<PostService> getAll();
	
	public int create(PostService postService);
	
	public void update(PostService postService);
	
	public void delete(PostService postService);
}
