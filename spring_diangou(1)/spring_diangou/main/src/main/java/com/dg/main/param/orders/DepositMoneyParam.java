package com.dg.main.param.orders;

public class DepositMoneyParam {
    private Long userId;
    private Long money;
    private Integer type=1;//1 支付宝 2 微信

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

    public DepositMoneyParam() {
    }
}
