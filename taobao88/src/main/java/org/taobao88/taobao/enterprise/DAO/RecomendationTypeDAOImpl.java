package org.taobao88.taobao.enterprise.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.entity.RecomendationType;

@Transactional
@Repository("recomendationTypeDAO")
public class RecomendationTypeDAOImpl implements RecomendationTypeDAO {

	@Autowired
    SessionFactory sessionFactory;
	
	@Override
	public List<RecomendationType> getRecomendationTypes() {
		return (List<RecomendationType>) sessionFactory.getCurrentSession().createQuery("from RecomendationType").list();
	}

	@Override
	public RecomendationType getTypeById(int id) {
		return (RecomendationType) sessionFactory.getCurrentSession().createQuery("from RecomendationType where type_id = :type_id").setParameter("type_id", id).uniqueResult();
	}

}
