package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "post_services")
public class PostService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198467839402041083L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "service_name")
	private String serviceName;
	
	@ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
	private Country country;
	
	@Column(name = "image_id")
	private int imageId;
	
	@ManyToOne
	@JoinColumn(name = "", referencedColumnName = "type_id")
	private Set<PostServicePrice> postServicesPrices;
	
	@Transient
	private Images image;
	
	public PostService() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

    public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<PostServicePrice> getPostServicesPrices() {
		return postServicesPrices;
	}

	public void setPostServicesPrices(Set<PostServicePrice> postServicesPrices) {
		this.postServicesPrices = postServicesPrices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + imageId;
		result = prime * result
				+ ((serviceName == null) ? 0 : serviceName.hashCode());
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
		PostService other = (PostService) obj;
		if (id != other.id)
			return false;
		if (imageId != other.imageId)
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
	
}
