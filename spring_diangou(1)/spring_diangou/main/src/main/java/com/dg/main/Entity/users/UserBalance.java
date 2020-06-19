package com.dg.main.Entity.users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//用户账户
@Entity
@Table(name = "user_balance")
@Data
public class UserBalance   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "money")
    private Long money=0l; //人命币
    @Column(name = "platform_money")
    private Long platformMoney=0l; //点币
    @Column(name = "operation_times")
    private Long operationTimes=1l;
    @Column(name ="create_time")
    private Date createTime=new Date();
    @Column(name = "modify_time")
    private Date modifyTime=new Date();

    public UserBalance() {
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

    public Long getOperationTimes() {
        return operationTimes;
    }

    public void setOperationTimes(Long operationTimes) {
        this.operationTimes = operationTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
