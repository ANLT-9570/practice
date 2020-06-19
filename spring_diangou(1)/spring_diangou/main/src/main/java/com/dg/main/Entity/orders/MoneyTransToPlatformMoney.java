package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "money_trans_to_platform_money")
public class MoneyTransToPlatformMoney  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_Id")
    private Long userId;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "money")
    private Long money;
    @Column(name = "platform_money")
    private Long platformMoney;
    @Column(name="current_rate")
    private Long currentRate;
    @Column(name = "code")
    private String code;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MoneyTransToPlatformMoney() {
    }
}
