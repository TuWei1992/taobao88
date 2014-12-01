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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4646743450624975235L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "status_name")
	private String statusName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<PackagesStatuses> packagesStatuses;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<OrdersStatuses> ordersStatuses;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_status", referencedColumnName = "id")
	private Status parentStatus;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentStatus")
	private Set<Status> children;
	
	public Status() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Set<PackagesStatuses> getPackagesStatuses() {
		return packagesStatuses;
	}

	public void setPackagesStatuses(Set<PackagesStatuses> packagesStatuses) {
		this.packagesStatuses = packagesStatuses;
	}

	public Set<OrdersStatuses> getOrdersStatuses() {
		return ordersStatuses;
	}

	public void setOrdersStatuses(Set<OrdersStatuses> ordersStatuses) {
		this.ordersStatuses = ordersStatuses;
	}

	public Status getParentStatus() {
		return parentStatus;
	}

	public void setParentStatus(Status parentStatus) {
		this.parentStatus = parentStatus;
	}

	public Set<Status> getChildren() {
		return children;
	}

	public void setChildren(Set<Status> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((statusName == null) ? 0 : statusName.hashCode());
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
		Status other = (Status) obj;
		if (id != other.id)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}
	
	
}
