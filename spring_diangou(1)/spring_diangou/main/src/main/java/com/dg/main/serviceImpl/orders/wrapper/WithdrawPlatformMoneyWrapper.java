package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;

public class WithdrawPlatformMoneyWrapper {
    final private UserWithdrawPlatformMoney userWithdrawPlatformMoney;
    final private UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog;
    final private UserBalance userBalance;
    final private UserThirdAccount userThirdAccount;
    public UserWithdrawPlatformMoney getUserWithdrawPlatformMoney() {
        return userWithdrawPlatformMoney;
    }

    public UserWithdrawPlatformMoneyLog getUserWithdrawPlatformMoneyLog() {
        return userWithdrawPlatformMoneyLog;
    }

    public WithdrawPlatformMoneyWrapper(UserWithdrawPlatformMoney userWithdrawPlatformMoney,
                                        UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog, UserBalance userBalance,
                                        UserThirdAccount userThirdAccount) {
        this.userWithdrawPlatformMoney = userWithdrawPlatformMoney;
        this.userWithdrawPlatformMoneyLog = userWithdrawPlatformMoneyLog;
        this.userBalance = userBalance;
        this.userThirdAccount=userThirdAccount;
    }

    public UserThirdAccount getUserThirdAccount() {
        return userThirdAccount;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }
}
