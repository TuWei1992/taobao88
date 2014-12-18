package org.taobao88.taobao.enterprise.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.BrandsDAO;
import org.taobao88.taobao.enterprise.entity.Brands;

@Transactional
@Repository("brandsDAO")
public class BrandsDAOImpl implements BrandsDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Brands> getAllBrands() {
		return (List<Brands>) sessionFactory.getCurrentSession().createQuery("from Brands order by brand_name").list();
	}

	@Override
	public Brands getBrandById(int brandId) {
		return (Brands) sessionFactory.getCurrentSession().createQuery("from Brands where brand_id = :brand_id").setParameter("brand_id", brandId).uniqueResult();
	}

	@Override
	public Brands getBrandByName(String brandName) {
		return (Brands) sessionFactory.getCurrentSession().createQuery("from Brands where brand_name = :brand_name").setParameter("brand_name", brandName).uniqueResult();
	}

	@Override
	public void addBrand(Brands brand) {
		sessionFactory.getCurrentSession().save(brand);
	}

	@Override
	public void deleteBrand(Brands brand) {
		sessionFactory.getCurrentSession().delete(brand);
	}

	@Override
	public void updateBrand(Brands brand) {
		sessionFactory.getCurrentSession().update(brand);
	}

}
