package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

/**
 * Created by User on 15.06.14.
 */
@Entity
@Table(name="package")
public class PackageT implements Comparable<PackageT>, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4519375259543053179L;

	@Id
    @GeneratedValue
    @Column(name = "idpackage", nullable = false)
    private Integer idPackage;

    @Column(name = "idpackageStatus", nullable = false)
    private int idPackageStatus;

    @Column(name = "full_price")
    private int fullPrice;

    @Column(name = "approve")
    private String approve;

    @Column(name = "date")
    private String datePackage;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "packageT")
    private Set<OrderT> orders;
    
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "packageT")
    private PackageStatus status;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "packageT", cascade = CascadeType.REMOVE)
    private List<Message> messages;
    
    @Column(name = "tracknumber")
    private String tracknumber;
    
    @Column(name = "weight")
    private double weight;
    
    @ManyToOne
    @JoinColumn(name = "post_service_id", referencedColumnName = "id")
    private PostService postService;
    
    @ManyToOne
    @JoinColumn(name = "shipping_address", referencedColumnName = "id")
    private ShippingAddress shippingAddress;
        
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "packageT")
    @IndexColumn(name = "id")
    private List<PackagesStatuses> packagesStatuses;
    
    @Column(name = "purchased")
    private int purchased;

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getIdPackageStatus() {
        return idPackageStatus;
    }
    
    public String getDatePackage() {
        return datePackage;
    }

    public void setDatePackage(String datePackage) {
        this.datePackage = datePackage;
    }

    public Set<OrderT> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderT> orders) {
		this.orders = orders;
	}

	public void setIdPackage(Integer idPackage) {
		this.idPackage = idPackage;
	}

	public PackageStatus getStatus() {
		return status;
	}

	public void setStatus(PackageStatus status) {
		this.status = status;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getTracknumber() {
		return tracknumber;
	}

	public void setTracknumber(String tracknumber) {
		this.tracknumber = tracknumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public List<PackagesStatuses> getPackagesStatuses() {
		return packagesStatuses;
	}

	public void setPackagesStatuses(List<PackagesStatuses> packagesStatuses) {
		this.packagesStatuses = packagesStatuses;
	}

	public int getPurchased() {
		return purchased;
	}

	public void setPurchased(int purchased) {
		this.purchased = purchased;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
    public String toString() {
        return "PackageT{" +
                "idPackage=" + idPackage +
                ", idPackageStatus=" + idPackageStatus +
                ", fullPrice=" + fullPrice +
                ", approve='" + approve + '\'' +
                ", datePackage='" + datePackage + '\'' +
                '}';
    }

    public void setIdPackageStatus(int idPackageStatus) {
        this.idPackageStatus = idPackageStatus;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageT other = (PackageT) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (datePackage == null) {
			if (other.datePackage != null)
				return false;
		} else if (!datePackage.equals(other.datePackage))
			return false;
		if (Double.doubleToLongBits(fullPrice) != Double
				.doubleToLongBits(other.fullPrice))
			return false;
		if (idPackage == null) {
			if (other.idPackage != null)
				return false;
		} else if (!idPackage.equals(other.idPackage))
			return false;
		if (idPackageStatus != other.idPackageStatus)
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result
				+ ((datePackage == null) ? 0 : datePackage.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fullPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((idPackage == null) ? 0 : idPackage.hashCode());
		result = prime * result + idPackageStatus;
		return result;
	}

    public int compareTo(PackageT o) {
        return this.idPackage.compareTo(o.getIdPackage());
    }
}
