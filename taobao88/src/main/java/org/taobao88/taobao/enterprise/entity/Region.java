package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 23.06.14.
 */
@Entity
@Table(name="region")
public class Region {

    @Id
    @GeneratedValue
    @Column(name = "region_id", nullable = false)
    private int idRegion;

    @Column(name = "country_id", nullable = false)
    private int idCountry;

    @Column(name = "city_id", nullable = false)
    private int idCity;

    @Column(name = "name", nullable = false)
    private String nameRegion;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

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

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameCity) {
        this.nameRegion = nameCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (idCity != region.idCity) return false;
        if (idCountry != region.idCountry) return false;
        if (idRegion != region.idRegion) return false;
        if (nameRegion != null ? !nameRegion.equals(region.nameRegion) : region.nameRegion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegion;
        result = 31 * result + idCountry;
        result = 31 * result + idCity;
        result = 31 * result + (nameRegion != null ? nameRegion.hashCode() : 0);
        return result;
    }
}
