package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;

public class RedPackLogWrapper {
    private final UserBalance userBalance;
    private final UserMoneyStreamLog userMoneyStreamLog;
    private final RedPackLog redPackLog;
    private final RedPack redPack;

    public RedPackLogWrapper(UserBalance userBalance, UserMoneyStreamLog userMoneyStreamLog, RedPackLog redPackLog, RedPack redPack) {
        this.userBalance = userBalance;
        this.userMoneyStreamLog = userMoneyStreamLog;
        this.redPackLog = redPackLog;
        this.redPack = redPack;
    }

    public RedPack getRedPack() {
        return redPack;
    }

    public UserMoneyStreamLog getUserMoneyStreamLog() {
        return userMoneyStreamLog;
    }

    public RedPackLog getRedPackLog() {
        return redPackLog;
    }

    public UserBalance getUserBalance() {
        return userBalance;
    }
}
