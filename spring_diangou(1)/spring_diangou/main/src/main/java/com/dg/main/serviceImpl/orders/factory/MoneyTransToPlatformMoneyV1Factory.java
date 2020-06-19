package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyV1Wrapper;

public class MoneyTransToPlatformMoneyV1Factory {
    public static MoneyTransToPlatformMoneyV1Wrapper newInstance(UserBalance userBalance,Long money){
        Long platfromMoney=money*10;
        money=money*100;
        return new MoneyTransToPlatformMoneyV1Wrapper(userBalance,money,platfromMoney);
    }
}
