package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "logistics_log")
public class LogisticsLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "accept_station")
    private String AcceptStation;
    @Column(name = "accept_time")
    private Timestamp AcceptTime;
    @Column(name = "logistics_state_id")
    private Long logisticsStateId;

    public Long getLogisticsStateId() {
        return logisticsStateId;
    }

    public void setLogisticsStateId(Long logisticsStateId) {
        this.logisticsStateId = logisticsStateId;
    }

    @Override
    public String toString() {
        return "LogisticsLog{" +
                "id=" + id +
                ", AcceptStation='" + AcceptStation + '\'' +
                ", AcceptTime=" + AcceptTime +
                '}';
    }

    public LogisticsLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcceptStation() {
        return AcceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        AcceptStation = acceptStation;
    }

    public Timestamp getAcceptTime() {
        return AcceptTime;
    }

    public void setAcceptTime(Timestamp acceptTime) {
        AcceptTime = acceptTime;
    }
}
