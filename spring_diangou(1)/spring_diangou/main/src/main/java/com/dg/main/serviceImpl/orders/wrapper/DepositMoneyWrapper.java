package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.Entity.logs.UserDepositMoneyLog;

public class DepositMoneyWrapper {
    final private UserDepositMoney userDepositMoney;
    final private UserDepositMoneyLog userDepositMoneyLog;
    final private UserBalance userBalance;
    public UserDepositMoney getUserDepositMoney() {
        return userDepositMoney;
    }

    public UserDepositMoneyLog getUserDepositMoneyLog() {
        return userDepositMoneyLog;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public DepositMoneyWrapper(UserDepositMoney userDepositMoney, UserDepositMoneyLog userDepositMoneyLog, UserBalance userBalance) {
        this.userDepositMoney = userDepositMoney;
        this.userDepositMoneyLog = userDepositMoneyLog;
        this.userBalance = userBalance;
    }
}
