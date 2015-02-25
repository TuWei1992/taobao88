package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sizes")
public class Sizes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249760614935439278L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "size_name")
	private String sizeName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sizes")
	private Set<Recomendation> recomendations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result	+ ((sizeName == null) ? 0 : sizeName.hashCode());
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
		Sizes other = (Sizes) obj;
		if (id != other.id)
			return false;
		if (recomendations == null) {
			if (other.recomendations != null)
				return false;
		} else if (!recomendations.equals(other.recomendations))
			return false;
		if (sizeName == null) {
			if (other.sizeName != null)
				return false;
		} else if (!sizeName.equals(other.sizeName))
			return false;
		return true;
	}

	public Set<Recomendation> getRecomendations() {
		return recomendations;
	}

	public void setRecomendations(Set<Recomendation> recomendations) {
		this.recomendations = recomendations;
	}
	
}
