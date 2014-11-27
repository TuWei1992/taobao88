package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.PostServiceDAO;
import org.taobao88.taobao.enterprise.entity.PostService;

@Repository("postServiceDAO")
@Transactional
public class PostServiceDAOImpl implements PostServiceDAO {

	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public PostService findById(int id) {
		return (PostService) sessionFactory.getCurrentSession().createQuery("from PostService where id = :id").setParameter("id", id).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostService> getAll() {
		return (List<PostService>) sessionFactory.getCurrentSession().createQuery("from PostService").list();
	}

	@Override
	public int create(PostService postService) {
		return (int) sessionFactory.getCurrentSession().save(postService);
	}

	@Override
	public void update(PostService postService) {
		sessionFactory.getCurrentSession().update(postService);
	}

	@Override
	public void delete(PostService postService) {
		sessionFactory.getCurrentSession().delete(postService);
	}

	@Override
	public PostService findByNameAndCountry(String serviceName, int countryId) {
		return (PostService) sessionFactory.getCurrentSession().createQuery("from PostService where serviceName = :serviceName and country.idCountry = :countryId")
				.setParameter("serviceName", serviceName).setParameter("countryId", countryId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostService> findByCountry(int countryId) {
		return (List<PostService>) sessionFactory.getCurrentSession().createQuery("from PostService where country.idCountry = :countryId")
				.setParameter("countryId", countryId).list();
	}

}
