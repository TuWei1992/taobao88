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
@Table(name = "colors")
public class Colors implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4649050052786303048L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "color_name")
	private String colorName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "colors")
	private Set<Recomendation> recomendations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colorName == null) ? 0 : colorName.hashCode());
		result = prime * result + id;
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
		Colors other = (Colors) obj;
		if (colorName == null) {
			if (other.colorName != null)
				return false;
		} else if (!colorName.equals(other.colorName))
			return false;
		if (id != other.id)
			return false;
		if (recomendations == null) {
			if (other.recomendations != null)
				return false;
		} else if (!recomendations.equals(other.recomendations))
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
