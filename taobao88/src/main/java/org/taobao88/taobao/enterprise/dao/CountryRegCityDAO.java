package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.City;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Region;

import java.util.List;

/**
 * Created by User on 23.06.14.
 */
public interface CountryRegCityDAO {
	
    public List<Country> getAllCountry();

    public List<Region> getAllRegion();

    public List<City> getAllCity();

    public Country getCountryByID(int id);

    public List<Region> getRegionsByID(int id);
    
    public Region findRegionById(int id);

    public List<City> getCitiessByID(int id);
    
    public City findCityById(int id);
    
    public int addCountry(Country country);
    
    public Country findByName(String countryName);
    
    public void delete(Country country);
    
    public void update(Country country);
}
