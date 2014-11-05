package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 23.06.14.
 */
@Entity
@Table(name="country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "country_id", nullable = false)
    private int idCountry;

    @Column(name = "city_id", nullable = false)
    private int idCity;

    @Column(name = "name", nullable = false)
    private String nameCountry;

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (idCity != country.idCity) return false;
        if (idCountry != country.idCountry) return false;
        if (nameCountry != null ? !nameCountry.equals(country.nameCountry) : country.nameCountry != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCountry;
        result = 31 * result + idCity;
        result = 31 * result + (nameCountry != null ? nameCountry.hashCode() : 0);
        return result;
    }
}
