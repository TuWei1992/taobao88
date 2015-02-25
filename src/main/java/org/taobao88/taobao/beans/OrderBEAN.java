package org.taobao88.taobao.beans;

/**
 * Created by User on 06.06.14.
 */
public class OrderBEAN implements Comparable<OrderBEAN>{

    private int idGoods;

    private String loginUser;

    private String gmailUser;

    private String hrefGoods;

    private double priceGoods;

    private int amountGoods;

    private double weightGoods;

    private Integer idOrder;

    private int idOrderStatus;

    private String approve;

    private int idUser;

    private double fullPrice;

    private String nameGoods;

    private String colorGoods;

    private String sizeGoods;

    private String chinaGoods;

    private String photoGoods;

    private String complexGoods;

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getRansomStatus() {
        return ransomStatus;
    }

    public void setRansomStatus(String ransomStatus) {
        this.ransomStatus = ransomStatus;
    }

    public String getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(String readyStatus) {
        this.readyStatus = readyStatus;
    }

    public String getDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(String doneStatus) {
        this.doneStatus = doneStatus;
    }

    private String approveStatus;

    private String payStatus;

    private String ransomStatus;

    private String readyStatus;

    private String doneStatus;

    @Override
    public String toString() {
        return "OrderBEAN{" +
                "idGoods=" + idGoods +
                ", loginUser='" + loginUser + '\'' +
                ", gmailUser='" + gmailUser + '\'' +
                ", hrefGoods='" + hrefGoods + '\'' +
                ", priceGoods=" + priceGoods +
                ", amountGoods=" + amountGoods +
                ", weightGoods=" + weightGoods +
                ", idOrder=" + idOrder +
                ", idOrderStatus=" + idOrderStatus +
                ", approve='" + approve + '\'' +
                ", idUser=" + idUser +
                ", fullPrice=" + fullPrice +
                ", nameGoods='" + nameGoods + '\'' +
                ", colorGoods='" + colorGoods + '\'' +
                ", sizeGoods='" + sizeGoods + '\'' +
                ", chinaGoods='" + chinaGoods + '\'' +
                ", photoGoods='" + photoGoods + '\'' +
                ", complexGoods='" + complexGoods + '\'' +
                ", approveStatus='" + approveStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", ransomStatus='" + ransomStatus + '\'' +
                ", readyStatus='" + readyStatus + '\'' +
                ", doneStatus='" + doneStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBEAN orderBEAN = (OrderBEAN) o;

        if (amountGoods != orderBEAN.amountGoods) return false;
        if (Double.compare(orderBEAN.fullPrice, fullPrice) != 0) return false;
        if (idGoods != orderBEAN.idGoods) return false;
        if (idOrderStatus != orderBEAN.idOrderStatus) return false;
        if (idUser != orderBEAN.idUser) return false;
        if (Double.compare(orderBEAN.priceGoods, priceGoods) != 0) return false;
        if (Double.compare(orderBEAN.weightGoods, weightGoods) != 0) return false;
        if (!approve.equals(orderBEAN.approve)) return false;
        if (!approveStatus.equals(orderBEAN.approveStatus)) return false;
        if (!chinaGoods.equals(orderBEAN.chinaGoods)) return false;
        if (!colorGoods.equals(orderBEAN.colorGoods)) return false;
        if (!complexGoods.equals(orderBEAN.complexGoods)) return false;
        if (!doneStatus.equals(orderBEAN.doneStatus)) return false;
        if (!gmailUser.equals(orderBEAN.gmailUser)) return false;
        if (!hrefGoods.equals(orderBEAN.hrefGoods)) return false;
        if (!idOrder.equals(orderBEAN.idOrder)) return false;
        if (!loginUser.equals(orderBEAN.loginUser)) return false;
        if (!nameGoods.equals(orderBEAN.nameGoods)) return false;
        if (!payStatus.equals(orderBEAN.payStatus)) return false;
        if (!photoGoods.equals(orderBEAN.photoGoods)) return false;
        if (!ransomStatus.equals(orderBEAN.ransomStatus)) return false;
        if (!readyStatus.equals(orderBEAN.readyStatus)) return false;
        if (!sizeGoods.equals(orderBEAN.sizeGoods)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idGoods;
        result = 31 * result + loginUser.hashCode();
        result = 31 * result + gmailUser.hashCode();
        result = 31 * result + hrefGoods.hashCode();
        temp = Double.doubleToLongBits(priceGoods);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amountGoods;
        temp = Double.doubleToLongBits(weightGoods);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + idOrder.hashCode();
        result = 31 * result + idOrderStatus;
        result = 31 * result + approve.hashCode();
        result = 31 * result + idUser;
        temp = Double.doubleToLongBits(fullPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + nameGoods.hashCode();
        result = 31 * result + colorGoods.hashCode();
        result = 31 * result + sizeGoods.hashCode();
        result = 31 * result + chinaGoods.hashCode();
        result = 31 * result + photoGoods.hashCode();
        result = 31 * result + complexGoods.hashCode();
        result = 31 * result + approveStatus.hashCode();
        result = 31 * result + payStatus.hashCode();
        result = 31 * result + ransomStatus.hashCode();
        result = 31 * result + readyStatus.hashCode();
        result = 31 * result + doneStatus.hashCode();
        return result;
    }

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

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getIdOrderStatus() {
        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
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

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getGmailUser() {
        return gmailUser;
    }

    public void setGmailUser(String gmailUser) {
        this.gmailUser = gmailUser;
    }

    public int compareTo(OrderBEAN o) {
        return this.idOrder.compareTo(o.getIdOrder());
    }
}
