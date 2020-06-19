package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "logistics_state")
public class LogisticsState   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "state")
    private String State;
    @Column(name = "e_business_id")
    private String EBusinessID;
    @Column(name = "success")
    private String Success;
    @Column(name = "logistic_code")
    private String LogisticCode;
    @Column(name = "shipper_code")
    private String ShipperCode;
    @Column(name = "order_code")
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public LogisticsState() {
    }

    @Override
    public String toString() {
        return "LogisticsState{" +
                "id=" + id +
                ", State='" + State + '\'' +
                ", EBusinessID='" + EBusinessID + '\'' +
                ", Success='" + Success + '\'' +
                ", LogisticCode='" + LogisticCode + '\'' +
                ", ShipperCode='" + ShipperCode + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }
}
