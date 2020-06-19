package com.dg.main.serviceImpl.orders.factory;

import com.google.gson.Gson;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.Entity.logs.UserDepositPlatformMoneyLog;
import com.dg.main.param.orders.DepositPlatformMoneyParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.setting.PlatformRateService;
import com.dg.main.serviceImpl.orders.wrapper.DepositPlatformMoneyWrapper;
import com.dg.main.util.Tuple2;

import java.math.BigDecimal;

public class DepositPlatformMoneyWrapperFactory {
    public static DepositPlatformMoneyWrapper newInstance(Long userId,Long platformMoney){
        Gson gson=new Gson();
        UserDepositPlatformMoney userDepositPlatformMoney=new UserDepositPlatformMoney();
      //  userDepositPlatformMoney.setCreateTime(new Date());
        userDepositPlatformMoney.setPlatformMoney(platformMoney);
        userDepositPlatformMoney.setDepositStreamCode(CodeGenerator.depositPlatformMoneyCode.create(userDepositPlatformMoney));
        userDepositPlatformMoney.setUserId(userId);

        UserDepositPlatformMoneyLog userDepositPlatformMoneyLog=new UserDepositPlatformMoneyLog();
        userDepositPlatformMoneyLog.setJsonOfUserDepositPlatformMoney(gson.toJson(userDepositPlatformMoney));
      //  userDepositPlatformMoneyLog.setCreateTime(new Date());
        userDepositPlatformMoneyLog.setMoney(platformMoney);
        userDepositPlatformMoneyLog.setUserId(userId);
        UserBalance userBalance=new UserBalance();
        return new DepositPlatformMoneyWrapper(userDepositPlatformMoney,userDepositPlatformMoneyLog,userBalance);
    }
    public static DepositPlatformMoneyWrapper newInstance(UserDepositPlatformMoney userDepositPlatformMoney){
        return new DepositPlatformMoneyWrapper(userDepositPlatformMoney,null,null);
    }
    public static Tuple2<UserDepositPlatformMoney,UserDepositPlatformMoneyLog> newInstance(Long userId, String platformMoney, Integer action, PlatformRateService platformRateService){
        Gson gson=new Gson();
        UserDepositPlatformMoney userDepositPlatformMoney=new UserDepositPlatformMoney();
        BigDecimal bigDecimal=new BigDecimal(platformMoney);
        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
        userDepositPlatformMoney.setPlatformMoney(money);
        userDepositPlatformMoney.setDepositStreamCode(CodeGenerator.depositPlatformMoneyCode.create(userDepositPlatformMoney));
        userDepositPlatformMoney.setUserId(userId);
        userDepositPlatformMoney.setDirection(action);
        userDepositPlatformMoney.setMoney(money);
        platformRateService.calDeposit(userDepositPlatformMoney);

        UserDepositPlatformMoneyLog userDepositPlatformMoneyLog=new UserDepositPlatformMoneyLog();
        userDepositPlatformMoneyLog.setJsonOfUserDepositPlatformMoney(gson.toJson(userDepositPlatformMoney));
        //userDepositPlatformMoneyLog.setCreateTime(new Date());
        userDepositPlatformMoneyLog.setMoney(money);
        userDepositPlatformMoneyLog.setUserId(userId);
        return new Tuple2<>(userDepositPlatformMoney,userDepositPlatformMoneyLog);

    }
    public static DepositPlatformMoneyWrapper newInstance(UserBalance userBalance, DepositPlatformMoneyParam depositPlatformMoneyParam){
        Gson gson=new Gson();
        UserDepositPlatformMoney userDepositPlatformMoney=new UserDepositPlatformMoney();
        BigDecimal bigDecimal=new BigDecimal(depositPlatformMoneyParam.getMoney());
        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
     //   userDepositPlatformMoney.setCreateTime(new Date());
        userDepositPlatformMoney.setPlatformMoney(money);
        userDepositPlatformMoney.setDepositStreamCode(CodeGenerator.depositPlatformMoneyCode.create(userDepositPlatformMoney));
        userDepositPlatformMoney.setUserId(userBalance.getUserId());

        UserDepositPlatformMoneyLog userDepositPlatformMoneyLog=new UserDepositPlatformMoneyLog();
        userDepositPlatformMoneyLog.setJsonOfUserDepositPlatformMoney(gson.toJson(userDepositPlatformMoney));
      //  userDepositPlatformMoneyLog.setCreateTime(new Date());
        userDepositPlatformMoneyLog.setMoney(money);
        userDepositPlatformMoneyLog.setUserId(userBalance.getUserId());
       // UserBalance userBalance=new UserBalance();
        return new DepositPlatformMoneyWrapper(userDepositPlatformMoney,userDepositPlatformMoneyLog,userBalance);
    }
    public static DepositPlatformMoneyWrapper newInstance(UserBalance userBalance, UserDepositPlatformMoney userDepositPlatformMoney){
        Gson gson=new Gson();
       // UserDepositPlatformMoney userDepositPlatformMoney=new UserDepositPlatformMoney();
        //   userDepositPlatformMoney.setCreateTime(new Date());
      //  userDepositPlatformMoney.setPlatformMoney(depositPlatformMoneyParam.getMoney());
      //  userDepositPlatformMoney.setDepositStreamCode(CodeGenerator.depositPlatformMoneyCode.create(userDepositPlatformMoney));
      //  userDepositPlatformMoney.setUserId(userBalance.getUserId());

        UserDepositPlatformMoneyLog userDepositPlatformMoneyLog=new UserDepositPlatformMoneyLog();
        userDepositPlatformMoneyLog.setJsonOfUserDepositPlatformMoney(gson.toJson(userDepositPlatformMoney));
        //  userDepositPlatformMoneyLog.setCreateTime(new Date());
        userDepositPlatformMoneyLog.setMoney(userDepositPlatformMoney.getMoney());
        userDepositPlatformMoneyLog.setUserId(userBalance.getUserId());
        // UserBalance userBalance=new UserBalance();
        return new DepositPlatformMoneyWrapper(userDepositPlatformMoney,userDepositPlatformMoneyLog,userBalance);
    }
}
