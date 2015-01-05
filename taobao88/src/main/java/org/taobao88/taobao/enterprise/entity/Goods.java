package org.taobao88.taobao.enterprise.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="goods")
public class Goods implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2490027426943145294L;

	@Id
    @GeneratedValue
    @Column(name = "goods_id", nullable = false)
    private int idGoods;

    @Column(name = "href_goods", nullable = false)
    private String hrefGoods;

    @Column(name = "name_goods", nullable = false)
    private String nameGoods;

    @Column(name = "color_goods", nullable = false)
    private String colorGoods;

    @Column(name = "size_goods", nullable = false)
    private String sizeGoods;

    @Column(name = "complex_goods", nullable = false)
    private String complexGoods;

    @Column(name = "china_goods", nullable = false)
    private String chinaGoods;

    @Column(name = "photo_goods", nullable = false)
    private String photoGoods;

    @Column(name = "price_goods", nullable = false)
    private double priceGoods;

    @Column(name = "amount_goods", nullable = false)
    private int amountGoods;

    @Column(name = "weight_goods", nullable = false)
    private double weightGoods;
    
    @Column(name = "photo")
    private String photo;
    
    @ManyToOne
	@JoinColumn(name = "recomendation_id", referencedColumnName = "id")
	private Recomendation recomendation;
    
    @OneToOne(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private OrderT orderT;
    
    public int getIdGoods() {
        return idGoods;
    }

    public void setIdGoods(int idGoods) {
        this.idGoods = idGoods;
    }

    public String getHrefGoods() {
        return hrefGoods;
    }

    public void setHrefGoods(String hrefGoods) {
        this.hrefGoods = hrefGoods;
    }

    public double getPriceGoods() {
        return priceGoods;
    }

    public void setPriceGoods(double priceGoods) {
        this.priceGoods = priceGoods;
    }

    public int getAmountGoods() {
        return amountGoods;
    }

    public void setAmountGoods(int amountGoods) {
        this.amountGoods = amountGoods;
    }

    public double getWeightGoods() {
        return weightGoods;
    }

    public void setWeightGoods(double weightGoods) {
        this.weightGoods = weightGoods;
    }

    public String getNameGoods() {
        return nameGoods;
    }

    public void setNameGoods(String nameGoods) {
        this.nameGoods = nameGoods;
    }

    public String getColorGoods() {
        return colorGoods;
    }

    public void setColorGoods(String colorGoods) {
        this.colorGoods = colorGoods;
    }

    public String getSizeGoods() {
        return sizeGoods;
    }

    public void setSizeGoods(String sizeGoods) {
        this.sizeGoods = sizeGoods;
    }

    public String getChinaGoods() {
        return chinaGoods;
    }

    public void setChinaGoods(String chinaGoods) {
        this.chinaGoods = chinaGoods;
    }

    public String getPhotoGoods() {
        return photoGoods;
    }

    public void setPhotoGoods(String photoGoods) {
        this.photoGoods = photoGoods;
    }

    public String getComplexGoods() {
        return complexGoods;
    }

    public void setComplexGoods(String complexGoods) {
        this.complexGoods = complexGoods;
    }
    
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Recomendation getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(Recomendation recomendation) {
		this.recomendation = recomendation;
	}

	public OrderT getOrderT() {
		return orderT;
	}

	public void setOrderT(OrderT orderT) {
		this.orderT = orderT;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goods other = (Goods) obj;
		if (amountGoods != other.amountGoods)
			return false;
		if (chinaGoods == null) {
			if (other.chinaGoods != null)
				return false;
		} else if (!chinaGoods.equals(other.chinaGoods))
			return false;
		if (colorGoods == null) {
			if (other.colorGoods != null)
				return false;
		} else if (!colorGoods.equals(other.colorGoods))
			return false;
		if (complexGoods == null) {
			if (other.complexGoods != null)
				return false;
		} else if (!complexGoods.equals(other.complexGoods))
			return false;
		if (hrefGoods == null) {
			if (other.hrefGoods != null)
				return false;
		} else if (!hrefGoods.equals(other.hrefGoods))
			return false;
		if (idGoods != other.idGoods)
			return false;
		if (nameGoods == null) {
			if (other.nameGoods != null)
				return false;
		} else if (!nameGoods.equals(other.nameGoods))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (photoGoods == null) {
			if (other.photoGoods != null)
				return false;
		} else if (!photoGoods.equals(other.photoGoods))
			return false;
		if (Double.doubleToLongBits(priceGoods) != Double
				.doubleToLongBits(other.priceGoods))
			return false;
		if (sizeGoods == null) {
			if (other.sizeGoods != null)
				return false;
		} else if (!sizeGoods.equals(other.sizeGoods))
			return false;
		if (Double.doubleToLongBits(weightGoods) != Double
				.doubleToLongBits(other.weightGoods))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountGoods;
		result = prime * result
				+ ((chinaGoods == null) ? 0 : chinaGoods.hashCode());
		result = prime * result
				+ ((colorGoods == null) ? 0 : colorGoods.hashCode());
		result = prime * result
				+ ((complexGoods == null) ? 0 : complexGoods.hashCode());
		result = prime * result
				+ ((hrefGoods == null) ? 0 : hrefGoods.hashCode());
		result = prime * result + idGoods;
		result = prime * result
				+ ((nameGoods == null) ? 0 : nameGoods.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result
				+ ((photoGoods == null) ? 0 : photoGoods.hashCode());
		long temp;
		temp = Double.doubleToLongBits(priceGoods);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((sizeGoods == null) ? 0 : sizeGoods.hashCode());
		temp = Double.doubleToLongBits(weightGoods);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

    @Override
    public String toString() {
        return "Goods{" +
                "idGoods=" + idGoods +
                ", hrefGoods='" + hrefGoods + '\'' +
                ", priceGoods=" + priceGoods +
                ", amountGoods=" + amountGoods +
                ", weightGoods=" + weightGoods +
                '}';

    }
}
