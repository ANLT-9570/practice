package com.dg.main.param.orders;

public class MoneyTransToPlatformMoneyParam {
    private Long userId;
    private Long money;
    private Integer type;
    private Long platformMoney;

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MoneyTransToPlatformMoneyParam() {
    }
}
