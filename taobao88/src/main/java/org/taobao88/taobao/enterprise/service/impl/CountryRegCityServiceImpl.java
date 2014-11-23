package org.taobao88.taobao.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taobao88.taobao.enterprise.dao.CountryRegCityDAO;
import org.taobao88.taobao.enterprise.entity.City;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Region;
import org.taobao88.taobao.enterprise.service.CountryRegCityService;

import java.util.List;

/**
 * Created by User on 23.06.14.
 */
@Repository ("countryRegCityService")
public class CountryRegCityServiceImpl implements CountryRegCityService{

    @Autowired
    CountryRegCityDAO countryRegCityDAO;

    @Override
    @Transactional
    public List<Country> getAllCountry() {
        return countryRegCityDAO.getAllCountry();
    }

    @Override
    @Transactional
    public List<Region> getAllRegion() {
        return countryRegCityDAO.getAllRegion();
    }

    @Override
    @Transactional
    public List<City> getAllCity() {
        return countryRegCityDAO.getAllCity();
    }

    @Override
    @Transactional
    public List<Country> getCountriesByID(int id) {
        return null;
    }

    @Override
    @Transactional
    public List<Region> getRegionsByID(int id) {
        return countryRegCityDAO.getRegionsByID(id);
    }

    @Override
    @Transactional
    public List<City> getCitiessByID(int id) {
        return countryRegCityDAO.getCitiessByID(id);
    }
}
