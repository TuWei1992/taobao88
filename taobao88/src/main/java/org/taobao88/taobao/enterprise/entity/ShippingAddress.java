package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_addresses")
public class ShippingAddress implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1775899189877522428L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "post_index")
	private String postIndex;
	
	@Column(name = "address")
	private String address;
	
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private Country country;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shippingAddress")
    private List<PackageT> packages;
	
	public ShippingAddress() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(String postIndex) {
		this.postIndex = postIndex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<PackageT> getPackageT() {
		return packages;
	}

	public void setPackageT(List<PackageT> packages) {
		this.packages = packages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((postIndex == null) ? 0 : postIndex.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingAddress other = (ShippingAddress) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id != other.id)
			return false;
		if (postIndex == null) {
			if (other.postIndex != null)
				return false;
		} else if (!postIndex.equals(other.postIndex))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
}
