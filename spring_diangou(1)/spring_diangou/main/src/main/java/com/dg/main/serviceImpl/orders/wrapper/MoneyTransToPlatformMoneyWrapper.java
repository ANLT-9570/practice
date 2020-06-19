package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.logs.MoneyTransToPlatformMoneyStreamLog;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.Entity.orders.*;
import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import com.dg.main.Entity.users.UserBalance;

public class MoneyTransToPlatformMoneyWrapper {
    private final MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate;
    private final UserBalance userBalance;
    private final MoneyTransToPlatformMoneyStreamLog moneyTransToPlatformMoneyStreamLog;
    private final MoneyTransToPlatformMoney moneyTransToPlatformMoney;
    private final UserMoneyStreamLog userMoneyStreamLog;

    public MoneyTransToPlatformMoneyRate getMoneyTransToPlatformMoneyRate() {
        return moneyTransToPlatformMoneyRate;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public MoneyTransToPlatformMoneyStreamLog getMoneyTransToPlatformMoneyStreamLog() {
        return moneyTransToPlatformMoneyStreamLog;
    }

    public MoneyTransToPlatformMoney getMoneyTransToPlatformMoney() {
        return moneyTransToPlatformMoney;
    }

    public UserMoneyStreamLog getUserMoneyStreamLog() {
        return userMoneyStreamLog;
    }

    public MoneyTransToPlatformMoneyWrapper(MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate, UserBalance userBalance, MoneyTransToPlatformMoneyStreamLog moneyTransToPlatformMoneyStreamLog, MoneyTransToPlatformMoney moneyTransToPlatformMoney, UserMoneyStreamLog userMoneyStreamLog) {
        this.moneyTransToPlatformMoneyRate = moneyTransToPlatformMoneyRate;
        this.userBalance = userBalance;
        this.moneyTransToPlatformMoneyStreamLog = moneyTransToPlatformMoneyStreamLog;
        this.moneyTransToPlatformMoney = moneyTransToPlatformMoney;
        this.userMoneyStreamLog = userMoneyStreamLog;
    }
}
