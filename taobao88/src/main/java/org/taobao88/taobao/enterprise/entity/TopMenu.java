package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "top_menu")
public class TopMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4189896815189148355L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_description")
	private String menuDescription;
	
	@Column(name = "menu_order")
	private int menuOrder;
	
	public TopMenu() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((menuDescription == null) ? 0 : menuDescription.hashCode());
		result = prime * result
				+ ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + menuOrder;
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
		TopMenu other = (TopMenu) obj;
		if (id != other.id)
			return false;
		if (menuDescription == null) {
			if (other.menuDescription != null)
				return false;
		} else if (!menuDescription.equals(other.menuDescription))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuOrder != other.menuOrder)
			return false;
		return true;
	}
	
	
}
