package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.RecomendationDAO;
import org.taobao88.taobao.enterprise.entity.Recomendation;
import org.taobao88.taobao.enterprise.entity.RecomendationType;

@Transactional
@Repository("recomendationDAO")
public class RecomendationDAOImpl implements RecomendationDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public void addRecomendation(Recomendation recomendation) {
		sessionFactory.getCurrentSession().save(recomendation);
	}

	@Override
	public void deleteRecomendation(Recomendation recomendation) {
		sessionFactory.getCurrentSession().delete(recomendation);
	}

	@Override
	public void updateRecomendation(Recomendation recomendation) {
		sessionFactory.getCurrentSession().update(recomendation);
	}

	@Override
	public Recomendation getRecomendationById(int id) {
		return (Recomendation) sessionFactory.getCurrentSession().createQuery("from Recomendation where id = :id").setParameter("id", id).uniqueResult();
	}

	@Override
	public Recomendation getRecomendationByDescription(String description) {
		return (Recomendation) sessionFactory.getCurrentSession().createQuery("from Recomendation where description = :description").setParameter("description", description).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recomendation> getRecomendations(RecomendationType type) {
		return (List<Recomendation>) sessionFactory.getCurrentSession().createQuery("from Recomendation where type.typeId = :type_id").setParameter("type_id", type.getTypeId()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recomendation> getFirstFourRecomendations(RecomendationType type) {
		return (List<Recomendation>) sessionFactory.getCurrentSession().createQuery("from Recomendation where type.typeId = :type_id").setParameter("type_id", type.getTypeId()).setMaxResults(4).list();
	}

}