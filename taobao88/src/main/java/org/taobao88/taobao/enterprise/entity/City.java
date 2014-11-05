package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 23.06.14.
 */
@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id", nullable = false)
    private int idCity;

    @Column(name = "country_id", nullable = false)
    private int idCountry;

    @Column(name = "region_id", nullable = false)
    private int idRegion;

    @Column(name = "name", nullable = false)
    private String nameCity;

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCountry) {
        this.nameCity = nameCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (idCity != city.idCity) return false;
        if (idCountry != city.idCountry) return false;
        if (idRegion != city.idRegion) return false;
        if (nameCity != null ? !nameCity.equals(city.nameCity) : city.nameCity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCity;
        result = 31 * result + idCountry;
        result = 31 * result + idRegion;
        result = 31 * result + (nameCity != null ? nameCity.hashCode() : 0);
        return result;
    }
}
