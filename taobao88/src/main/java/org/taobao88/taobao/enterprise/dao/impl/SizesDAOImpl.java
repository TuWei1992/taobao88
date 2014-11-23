package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.SizesDAO;
import org.taobao88.taobao.enterprise.entity.Sizes;

@Transactional
@Repository("sizesDAO")
public class SizesDAOImpl implements SizesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Sizes getSizeById(int id) {
		return (Sizes) sessionFactory.getCurrentSession().createQuery("from Sizes where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public Sizes getSizesByName(String sizeName) {
		return (Sizes) sessionFactory.getCurrentSession().createQuery("from Sizes where size_name = :size_name").setParameter("size_name", sizeName).uniqueResult();
	}

	@Override
	public List<Sizes> getSizes() {
		return (List<Sizes>) sessionFactory.getCurrentSession().createQuery("from Sizes").list();
	}

	@Override
	public int addSize(Sizes size) {
		return (Integer) sessionFactory.getCurrentSession().save(size);
	}

	@Override
	public void updateSize(Sizes size) {
		sessionFactory.getCurrentSession().update(size);
	}

	@Override
	public void deleteSize(Sizes size) {
		sessionFactory.getCurrentSession().delete(size);
	}

}
