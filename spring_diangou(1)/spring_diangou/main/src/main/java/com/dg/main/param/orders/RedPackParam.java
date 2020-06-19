package com.dg.main.param.orders;

import java.sql.Timestamp;

public class RedPackParam {
    private Long userId;
    private Long shopId;
    private Long platformMoney;
    private Integer number;
    private Timestamp sendTime=null;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }
    //private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public RedPackParam() {
    }
}
