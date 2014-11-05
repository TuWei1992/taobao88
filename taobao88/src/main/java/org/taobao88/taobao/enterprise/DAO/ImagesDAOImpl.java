package org.taobao88.taobao.enterprise.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.entity.Images;
import org.taobao88.taobao.enterprise.entity.Recomendation;

@Transactional
@Repository("imagesDAO")
public class ImagesDAOImpl implements ImagesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Images> getAllImages() {
		return (List<Images>) sessionFactory.getCurrentSession().createQuery("from Images").list();
	}

	@Override
	public Images getImagesById(int imageId) {
		return (Images) sessionFactory.getCurrentSession().createQuery("from Images where image_id = :imageId").setParameter("imageId", imageId).uniqueResult();
	}

	@Override
	public Images getImageByName(String imageName) {
		return (Images) sessionFactory.getCurrentSession().createQuery("from Images where image_name = :imageName").setParameter("imageName", imageName).uniqueResult();
	}

	@Override
	public int addImage(Images image) {
		return (Integer) sessionFactory.getCurrentSession().save(image);
	}

	@Override
	public void deleteImage(Images image) {
		sessionFactory.getCurrentSession().delete(image);
	}

	@Override
	public void updateImage(Images image) {
		sessionFactory.getCurrentSession().update(image);
	}

	@Override
	public Set<Images> getImagesByRecomendation(Recomendation recomendation) {
		return null;
	}

}
