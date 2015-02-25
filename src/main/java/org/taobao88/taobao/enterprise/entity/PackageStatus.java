package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 19.06.14.
 */
@Entity
@Table(name="packagestatus")
public class PackageStatus {
    @Id
    @GeneratedValue
    @Column(name = "idpackageStatus", nullable = false)
    private int idPackageStatus;

    @Column(name = "approve")
    private String approvePackage;

    @Column(name = "pay")
    private String payPackage;

    @Column(name = "ransom")
    private String ransomPackage;

    @Column(name = "ready")
    private String readyPackage;

    @Column(name = "import")
    private String importPackage;

    @Column(name = "endPoint")
    private String endPackage;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PackageT packageT;
    
    public int getIdPackageStatus() {
        return idPackageStatus;
    }

    public void setIdPackageStatus(int idPackageStatus) {
        this.idPackageStatus = idPackageStatus;
    }

    public String getApprovePackage() {
        return approvePackage;
    }

    public void setApprovePackage(String approvePackage) {
        this.approvePackage = approvePackage;
    }

    public String getPayPackage() {
        return payPackage;
    }

    public void setPayPackage(String payPackage) {
        this.payPackage = payPackage;
    }

    public String getRansomPackage() {
        return ransomPackage;
    }

    public void setRansomPackage(String ransomPackage) {
        this.ransomPackage = ransomPackage;
    }

    public String getReadyPackage() {
        return readyPackage;
    }

    public void setReadyPackage(String readyPackage) {
        this.readyPackage = readyPackage;
    }

    public String getImportPackage() {
        return importPackage;
    }

    public void setImportPackage(String importPackage) {
        this.importPackage = importPackage;
    }

    public String getEndPackage() {
        return endPackage;
    }

    public void setEndPackage(String endPackage) {
        this.endPackage = endPackage;
    }

    public PackageT getPackageT() {
		return packageT;
	}

	public void setPackageT(PackageT packageT) {
		this.packageT = packageT;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageStatus other = (PackageStatus) obj;
		if (approvePackage == null) {
			if (other.approvePackage != null)
				return false;
		} else if (!approvePackage.equals(other.approvePackage))
			return false;
		if (endPackage == null) {
			if (other.endPackage != null)
				return false;
		} else if (!endPackage.equals(other.endPackage))
			return false;
		if (idPackageStatus != other.idPackageStatus)
			return false;
		if (importPackage == null) {
			if (other.importPackage != null)
				return false;
		} else if (!importPackage.equals(other.importPackage))
			return false;
		if (packageT == null) {
			if (other.packageT != null)
				return false;
		} else if (!packageT.equals(other.packageT))
			return false;
		if (payPackage == null) {
			if (other.payPackage != null)
				return false;
		} else if (!payPackage.equals(other.payPackage))
			return false;
		if (ransomPackage == null) {
			if (other.ransomPackage != null)
				return false;
		} else if (!ransomPackage.equals(other.ransomPackage))
			return false;
		if (readyPackage == null) {
			if (other.readyPackage != null)
				return false;
		} else if (!readyPackage.equals(other.readyPackage))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((approvePackage == null) ? 0 : approvePackage.hashCode());
		result = prime * result
				+ ((endPackage == null) ? 0 : endPackage.hashCode());
		result = prime * result + idPackageStatus;
		result = prime * result
				+ ((importPackage == null) ? 0 : importPackage.hashCode());
		result = prime * result
				+ ((packageT == null) ? 0 : packageT.hashCode());
		result = prime * result
				+ ((payPackage == null) ? 0 : payPackage.hashCode());
		result = prime * result
				+ ((ransomPackage == null) ? 0 : ransomPackage.hashCode());
		result = prime * result
				+ ((readyPackage == null) ? 0 : readyPackage.hashCode());
		return result;
	}
}
