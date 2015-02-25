package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.ColorsDAO;
import org.taobao88.taobao.enterprise.entity.Colors;

@Transactional
@Repository("colorsDAO")
public class ColorsDAOImpl implements ColorsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Colors getColorById(int id) {
		return (Colors) sessionFactory.getCurrentSession().createQuery("from Colors where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public Colors getColorByName(String colorName) {
		return (Colors) sessionFactory.getCurrentSession().createQuery("from Colors where color_name = :color_name").setParameter("color_name", colorName).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colors> getColors() {
		return (List<Colors>) sessionFactory.getCurrentSession().createQuery("from Colors").list();
	}

	@Override
	public int addColor(Colors color) {
		return (Integer) sessionFactory.getCurrentSession().save(color);
	}

	@Override
	public void updateColor(Colors color) {
		sessionFactory.getCurrentSession().update(color);
	}

	@Override
	public void deleteColor(Colors color) {
		sessionFactory.getCurrentSession().delete(color);
	}

}
