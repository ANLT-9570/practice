package com.dg.main.param.orders;

public class WithdrawPlatformMoneyParam {
    private Long userId;
    private Long money=0l;
    private Integer type=1;

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

    public WithdrawPlatformMoneyParam() {
    }
}
