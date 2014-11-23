package org.taobao88.taobao.enterprise.dao;

import org.taobao88.taobao.enterprise.entity.City;
import org.taobao88.taobao.enterprise.entity.Country;
import org.taobao88.taobao.enterprise.entity.Region;

import java.util.List;

/**
 * Created by User on 23.06.14.
 */
public interface CountryRegCityDAO {
    List<Country> getAllCountry();

    List<Region> getAllRegion();

    List<City> getAllCity();

    List<Country> getCountriesByID(int id);

    List<Region> getRegionsByID(int id);

    List<City> getCitiessByID(int id);
}
