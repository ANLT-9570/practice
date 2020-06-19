package com.dg.main.trans_mapper_obj;

import java.sql.Timestamp;

public class UserDepositStream {
    private Integer type;
    private Long money;
    private Timestamp createTime;
    private Integer userId;
    private Integer direction;

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public UserDepositStream() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDepositStream{" +
                "type=" + type +
                ", money=" + money +
                ", createTime=" + createTime +
                ", userId=" + userId +
                '}';
    }
}
