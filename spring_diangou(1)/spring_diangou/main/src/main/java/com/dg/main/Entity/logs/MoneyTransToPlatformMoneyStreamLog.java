package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//人民币转平台币的现金流记录
@Entity
@Table(name = "money_trans_to_platform_stream_log")
public class MoneyTransToPlatformMoneyStreamLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "money")
    private Long money;//账户
    @Column(name = "platform_money")
    private Long platformMoney;//平台的钱
    @Column(name = "current_rate")
    private Long currentRate;//当日汇率

    @Override
    public String toString() {
        return "MoneyTransToPlatformMoneyStreamLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", money=" + money +
                ", platformMoney=" + platformMoney +
                ", currentRate=" + currentRate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public Long getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Long currentRate) {
        this.currentRate = currentRate;
    }

    public MoneyTransToPlatformMoneyStreamLog() {
    }
}
