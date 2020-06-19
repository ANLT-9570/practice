package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.Entity.logs.UserDepositPlatformMoneyLog;

public class DepositPlatformMoneyWrapper {
    final private UserDepositPlatformMoney userDepositPlatformMoney;
    final private UserDepositPlatformMoneyLog userDepositPlatformMoneyLog;
    final private UserBalance userBalance;
    public UserDepositPlatformMoney getUserDepositPlatformMoney() {
        return userDepositPlatformMoney;
    }

    public UserDepositPlatformMoneyLog getUserDepositPlatformMoneyLog() {
        return userDepositPlatformMoneyLog;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public DepositPlatformMoneyWrapper(UserDepositPlatformMoney userDepositPlatformMoney, UserDepositPlatformMoneyLog userDepositPlatformMoneyLog, UserBalance userBalance) {
        this.userDepositPlatformMoney = userDepositPlatformMoney;
        this.userDepositPlatformMoneyLog = userDepositPlatformMoneyLog;
        this.userBalance = userBalance;
    }
}
