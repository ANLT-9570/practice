package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import com.dg.main.Entity.orders.*;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.users.UserThirdAccount;

public class WithdrawMoneyWrapper {
    final private UserWithdrawMoney userWithdrawMoney;
    final private UserWithdrawMoneyStreamLog withdrawMoneyStreamLog;
    final private UserBalance userBalance;
    final private UserThirdAccount userThirdAccount;
    public UserWithdrawMoney getUserWithdrawMoney() {
        return userWithdrawMoney;
    }

    public WithdrawMoneyWrapper(UserWithdrawMoney userWithdrawMoney, UserWithdrawMoneyStreamLog withdrawMoneyStreamLog, UserBalance userBalance,UserThirdAccount userThirdAccount) {
        this.userWithdrawMoney = userWithdrawMoney;
        this.withdrawMoneyStreamLog = withdrawMoneyStreamLog;
        this.userBalance = userBalance;
        this.userThirdAccount=userThirdAccount;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public UserThirdAccount getUserThirdAccount() {
        return userThirdAccount;
    }

    public UserWithdrawMoneyStreamLog getWithdrawMoneyStreamLog() {
        return withdrawMoneyStreamLog;
    }
}
