package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

/**
 * Created by User on 03.06.14.
 */
@Entity
@Table(name="ordert")
public class OrderT implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3522561804261869297L;

	@Id
    @GeneratedValue
    @Column(name = "orderT_id", nullable = false)
    private int idOrder;

    @Column(name = "approve")
    private String approve;

    @Column(name = "user_id")
    private int idUser;

    @Column(name = "goods_id")
    private int idGoods;

    @Column(name = "idOrderStatus")
    private int idOrderStatus;

    @Column(name = "full_price")
    private int fullPrice;
   
    @Column(name = "date")
    private Timestamp dateOrder;
    
    @ManyToOne
    @JoinColumn(name = "idpackage", referencedColumnName = "idpackage")
    private PackageT packageT;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "orderT")
    private Goods goods;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderT")
    @IndexColumn(name = "id")
    private List<OrdersStatuses> ordersStatuses;
    
    public Timestamp getDateOrder() {
        return dateOrder;
    }
    
    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getIdOrderStatus() {

        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdGoods() {
        return idGoods;
    }

    public void setIdGoods(int idGoods) {
        this.idGoods = idGoods;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }

	public PackageT getPackageT() {
		return packageT;
	}

	public void setPackageT(PackageT packageT) {
		this.packageT = packageT;
	}
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<OrdersStatuses> getOrdersStatuses() {
		return ordersStatuses;
	}

	public void setOrdersStatuses(List<OrdersStatuses> ordersStatuses) {
		this.ordersStatuses = ordersStatuses;
	}

	@Override
    public String toString() {
        return "OrderT{" +
                "idOrder=" + idOrder +
                ", approve='" + approve + '\'' +
                ", idUser=" + idUser +
                ", idGoods=" + idGoods +
                ", idOrderStatus=" + idOrderStatus +
                ", fullPrice=" + fullPrice +
                '}';
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderT other = (OrderT) obj;
		if (approve == null) {
			if (other.approve != null)
				return false;
		} else if (!approve.equals(other.approve))
			return false;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
			return false;
		if (Double.doubleToLongBits(fullPrice) != Double
				.doubleToLongBits(other.fullPrice))
			return false;
		if (idGoods != other.idGoods)
			return false;
		if (idOrder != other.idOrder)
			return false;
		if (idOrderStatus != other.idOrderStatus)
			return false;
		if (idUser != other.idUser)
			return false;
		if (packageT == null) {
			if (other.packageT != null)
				return false;
		} else if (!packageT.equals(other.packageT))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approve == null) ? 0 : approve.hashCode());
		result = prime * result
				+ ((dateOrder == null) ? 0 : dateOrder.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fullPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idGoods;
		result = prime * result + idOrder;
		result = prime * result + idOrderStatus;
		result = prime * result + idUser;
		result = prime * result
				+ ((packageT == null) ? 0 : packageT.hashCode());
		return result;
	}
}
