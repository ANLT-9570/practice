package com.dg.main.Entity.settings;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "send_order_time_setting")
public class SendOrderTimeSetting   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "types")
    private Integer types=1;
    @Column(name = "days")
    private Integer days=1;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());

    @Override
    public String toString() {
        return "SendOrderTimeSetting{" +
                "id=" + id +
                ", types=" + types +
                ", values=" + days +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public SendOrderTimeSetting() {
    }
}
