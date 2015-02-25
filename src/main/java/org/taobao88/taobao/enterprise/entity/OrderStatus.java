package org.taobao88.taobao.enterprise.entity;

import javax.persistence.*;

/**
 * Created by User on 09.06.14.
 */
@Entity
@Table(name="orderstatus")
public class OrderStatus {

    @Id
    @GeneratedValue
    @Column(name = "idOrderStatus", nullable = false)
    private int idOrderStatus;

    @Column(name = "approveW")
    private String approve;

    @Column(name = "payW")
    private String pay;

    @Column(name = "ransom")
    private String ransom;

    @Column(name = "ready")
    private String ready;

    @Column(name = "import")
    private String done;

    public int getIdOrderStatus() {
        return idOrderStatus;
    }

    public void setIdOrderStatus(int idOrderStatus) {
        this.idOrderStatus = idOrderStatus;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getRansom() {
        return ransom;
    }

    public void setRansom(String ransom) {
        this.ransom = ransom;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatus that = (OrderStatus) o;

        if (idOrderStatus != that.idOrderStatus) return false;
        if (!approve.equals(that.approve)) return false;
        if (!done.equals(that.done)) return false;
        if (!pay.equals(that.pay)) return false;
        if (!ransom.equals(that.ransom)) return false;
        if (!ready.equals(that.ready)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrderStatus;
        result = 31 * result + approve.hashCode();
        result = 31 * result + pay.hashCode();
        result = 31 * result + ransom.hashCode();
        result = 31 * result + ready.hashCode();
        result = 31 * result + done.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "idOrderStatus=" + idOrderStatus +
                ", approve='" + approve + '\'' +
                ", pay='" + pay + '\'' +
                ", ransom='" + ransom + '\'' +
                ", ready='" + ready + '\'' +
                ", done='" + done + '\'' +
                '}';
    }
}
