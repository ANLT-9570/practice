package com.dg.main.param.orders;

public class WithdrawMoneyParam {
    private Long userId;
    private String money;
    private Integer type;//1 支付宝 2.微信

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public WithdrawMoneyParam() {
    }
}
