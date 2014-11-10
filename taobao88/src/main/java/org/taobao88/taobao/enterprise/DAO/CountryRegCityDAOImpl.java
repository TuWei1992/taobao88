package org.taobao88.taobao.enterprise.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @SuppressWarnings("unchecked")
	@Override
    public List<Country> getCountriesByID(int id) {
        String query = "from Country where idCountry = "+id;
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Region> getRegionsByID(int id) {
        String query = "from Region where idCountry = "+id;
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<City> getCitiessByID(int id) {
        String query = "from City where idRegion = "+id;
        return sessionFactory.getCurrentSession().createQuery(query).list();
    }
}
