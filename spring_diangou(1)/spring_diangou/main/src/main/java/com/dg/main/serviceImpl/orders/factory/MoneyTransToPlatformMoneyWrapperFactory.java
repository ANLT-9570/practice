package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.logs.MoneyTransToPlatformMoneyStreamLog;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.Entity.orders.*;
import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.param.orders.MoneyTransToPlatformMoneyParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyWrapper;

import java.util.Date;

public class MoneyTransToPlatformMoneyWrapperFactory {
    public static MoneyTransToPlatformMoneyWrapper newInstance(){
        UserBalance userBalance=new UserBalance();
        MoneyTransToPlatformMoney moneyTransToPlatformMoney=new MoneyTransToPlatformMoney();
        MoneyTransToPlatformMoneyStreamLog moneyTransToPlatformMoneyStreamLog=new MoneyTransToPlatformMoneyStreamLog();
        MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=new MoneyTransToPlatformMoneyRate();
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();

        return new MoneyTransToPlatformMoneyWrapper(moneyTransToPlatformMoneyRate,
                userBalance,
                moneyTransToPlatformMoneyStreamLog,
                moneyTransToPlatformMoney,
                userMoneyStreamLog);
    }
    public static MoneyTransToPlatformMoneyWrapper newInstance(UserBalance userBalance,MoneyTransToPlatformMoneyRate inMoneyTransToPlatformMoneyRate,
                                                               MoneyTransToPlatformMoneyParam moneyTransToPlatformMoneyParam){
        UserBalance balance=userBalance;
        MoneyTransToPlatformMoney moneyTransToPlatformMoney=new MoneyTransToPlatformMoney();

        moneyTransToPlatformMoney.setCode(CodeGenerator.moneyTransToPlatformMoneyCode.create(moneyTransToPlatformMoney));
        moneyTransToPlatformMoney.setCreateTime(new Date());
        moneyTransToPlatformMoney.setMoney(moneyTransToPlatformMoneyParam.getMoney());

        moneyTransToPlatformMoney.setCurrentRate(inMoneyTransToPlatformMoneyRate.getRate());
        moneyTransToPlatformMoney.setPlatformMoney(moneyTransToPlatformMoneyParam.getMoney()*inMoneyTransToPlatformMoneyRate.getRate());
        moneyTransToPlatformMoney.setUserId(userBalance.getUserId());


        MoneyTransToPlatformMoneyStreamLog moneyTransToPlatformMoneyStreamLog=new MoneyTransToPlatformMoneyStreamLog();
     //   moneyTransToPlatformMoneyStreamLog.setCreateTime(new Date());
        moneyTransToPlatformMoneyStreamLog.setUserId(userBalance.getUserId());
        moneyTransToPlatformMoneyStreamLog.setMoney(moneyTransToPlatformMoneyParam.getMoney());
        moneyTransToPlatformMoneyStreamLog.setPlatformMoney(moneyTransToPlatformMoney.getPlatformMoney());
        moneyTransToPlatformMoneyStreamLog.setCurrentRate(inMoneyTransToPlatformMoneyRate.getRate());

        MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=inMoneyTransToPlatformMoneyRate;
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();


        return new MoneyTransToPlatformMoneyWrapper(moneyTransToPlatformMoneyRate,
                userBalance,
                moneyTransToPlatformMoneyStreamLog,
                moneyTransToPlatformMoney,
                userMoneyStreamLog);
    }
}
