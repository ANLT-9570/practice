package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.UserBalance;

public class RedPackWrapper {
    private final UserBalance userBalance;
 //   private final UserMoneyStreamLog userMoneyStreamLog;
    private final RedPack redPack;

    public UserBalance getUserBalance() {
        return userBalance;
    }

//    public UserMoneyStreamLog getUserMoneyStreamLog() {
//        return userMoneyStreamLog;
//    }

    public RedPack getRedPack() {
        return redPack;
    }

    public RedPackWrapper(UserBalance userBalance,  RedPack redPack) {
        this.userBalance = userBalance;
      //  this.userMoneyStreamLog = userMoneyStreamLog;
        this.redPack = redPack;
    }
}
