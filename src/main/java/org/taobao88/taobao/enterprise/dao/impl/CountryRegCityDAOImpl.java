package org.taobao88.taobao.enterprise.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.entity.City;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Region;

import java.util.List;

/**
 * Created by User on 23.06.14.
 */
@Transactional
@Repository("countryRegCityDAO")
public class CountryRegCityDAOImpl implements CountryRegCityDAO{

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	@Override
    public List<Country> getAllCountry() {
        return sessionFactory.getCurrentSession().createQuery("from Country").list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Region> getAllRegion() {
        return sessionFactory.getCurrentSession().createQuery("from Region").list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<City> getAllCity() {
        return sessionFactory.getCurrentSession().createQuery("from City").list();
    }

    @Override
    public Country getCountryByID(int id) {
        String query = "from Country where idCountry = "+id;
        return (Country) sessionFactory.getCurrentSession().createQuery(query).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Region> getRegionsByID(int id) {
        String query = "from Region where idCountry = "+id;
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }
    
    public Region findRegionById(int id) {
    	return (Region) sessionFactory.getCurrentSession().createQuery("from Region where region_id = :id").setParameter("id", id).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<City> getCitiessByID(int id) {
        String query = "from City where idRegion = "+id;
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }
    
    public City findCityById(int id) {
    	return (City) sessionFactory.getCurrentSession().createQuery("from City where city_id = :id").setParameter("id", id).uniqueResult();
    }

	@Override
	public int addCountry(Country country) {
		return (int) sessionFactory.getCurrentSession().save(country);
	}

	@Override
	public Country findByName(String countryName) {
		return (Country) sessionFactory.getCurrentSession().createQuery("from Country where name = :countryName").setParameter("countryName", countryName).uniqueResult();
	}

	@Override
	public void delete(Country country) {
		sessionFactory.getCurrentSession().delete(country);		
	}

	@Override
	public void update(Country country) {
		sessionFactory.getCurrentSession().update(country);		
	}
}
