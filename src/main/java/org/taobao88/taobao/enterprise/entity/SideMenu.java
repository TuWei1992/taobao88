package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="side_menu")
public class SideMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4067681417410356851L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "menu_href")
	private String menuHref;
	
	@Column(name = "menu_order")
	private int menuOrder;

	@Column(name = "parent_id")
	private int parentId;
	
	@Column(name = "level")
	private int level;
	
	@Transient
	private SideMenu parent;
	
	@Transient
	private List<SideMenu> children;
	
	public SideMenu() {
		
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

	public String getMenuHref() {
		return menuHref;
	}

	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}
	
	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public int getParentId() {
		return parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public SideMenu getParent() {
		return parent;
	}

	public void setParent(SideMenu parent) {
		this.parent = parent;
	}

	public List<SideMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SideMenu> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + level;
		result = prime * result
				+ ((menuHref == null) ? 0 : menuHref.hashCode());
		result = prime * result
				+ ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + menuOrder;
		result = prime * result + parentId;
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
		SideMenu other = (SideMenu) obj;
		if (id != other.id)
			return false;
		if (level != other.level)
			return false;
		if (menuHref == null) {
			if (other.menuHref != null)
				return false;
		} else if (!menuHref.equals(other.menuHref))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuOrder != other.menuOrder)
			return false;
		if (parentId != other.parentId)
			return false;
		return true;
	}

}
