package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recomendations")
public class Recomendation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7605542245636976730L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "long_description")
	private String longDescription;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "href")
	private String href;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "count")
	private int count;
	
	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "type_id")
	private RecomendationType type;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "recomendations_images", joinColumns = { 
			@JoinColumn(name = "recomendation_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private Set<Images> images;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "recomendations_comments", joinColumns = { 
			@JoinColumn(name = "recomendation_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "comment_id", nullable = false, updatable = false) })
	private Set<Comments> comments;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "recomendations_colors", joinColumns = { 
			@JoinColumn(name = "recomendation_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "color_id", nullable = false, updatable = false) })
	private Set<Colors> colors;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinTable(name = "recomendations_sizes", joinColumns = { 
			@JoinColumn(name = "recomendation_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "sizes_id", nullable = false, updatable = false) })
	private Set<Sizes> sizes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recomendation")
	@Cascade(value = {org.hibernate.annotations.CascadeType.DELETE})
	private Set<Goods> goods;
	
	public Recomendation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((longDescription == null) ? 0 : longDescription.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Recomendation other = (Recomendation) obj;
		if (count != other.count)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (id != other.id)
			return false;
		if (longDescription == null) {
			if (other.longDescription != null)
				return false;
		} else if (!longDescription.equals(other.longDescription))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	public RecomendationType getType() {
		return type;
	}

	public void setType(RecomendationType type) {
		this.type = type;
	}

	public Set<Images> getImages() {
		return images;
	}

	public void setImages(Set<Images> images) {
		this.images = images;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public Set<Colors> getColors() {
		return colors;
	}

	public void setColors(Set<Colors> colors) {
		this.colors = colors;
	}

	public Set<Sizes> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Sizes> sizes) {
		this.sizes = sizes;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
	
}
