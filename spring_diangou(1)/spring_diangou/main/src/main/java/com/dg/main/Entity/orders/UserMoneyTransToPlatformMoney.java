package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//用户人民币转平台币
@Entity
@Table(name = "user_money_trans_to_platform_money")
public class UserMoneyTransToPlatformMoney   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_Id")
    private Long userId;
    @Column(name = "money")
    private Long money;
    @Column(name = "platform_money")
    private Long platformMoney;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "is_success")
    private Integer isSuccess=2;

    @Override
    public String toString() {
        return "UserMoneyTransToPlatformMoney{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", platformMoney=" + platformMoney +
                ", createTime=" + createTime +
                ", isSuccess=" + isSuccess +
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public UserMoneyTransToPlatformMoney() {
    }
}
