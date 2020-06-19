package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.users.UserBalance;

public class MoneyTransToPlatformMoneyV1Wrapper {
     final private UserBalance userBalance;
   final private Long money;
   final private Long platformMoney;

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public Long getMoney() {
        return money;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public MoneyTransToPlatformMoneyV1Wrapper(UserBalance userBalance, Long money, Long platformMoney) {
        this.userBalance = userBalance;
        this.money = money;
        this.platformMoney = platformMoney;
    }
}
