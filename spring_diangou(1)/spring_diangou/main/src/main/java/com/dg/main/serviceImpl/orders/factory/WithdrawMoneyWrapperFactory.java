package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import com.dg.main.Entity.users.UserBalance;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.param.orders.WithdrawMoneyParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import com.dg.main.util.Tuple2;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawMoneyWrapperFactory {
    public static WithdrawMoneyWrapper newInstance(Long userId,Long money){
        Gson gson=new Gson();
        UserWithdrawMoney userWithdrawMoney=new UserWithdrawMoney();
        UserWithdrawMoneyStreamLog userWithdrawPlatformMoneyLog=new UserWithdrawMoneyStreamLog();
        userWithdrawMoney.setUserId(userId);
        // userWithdrawMoney.setCreateTime(new Date());
        userWithdrawMoney.setMoney(money);
        userWithdrawMoney.setWithdrawStreamCode(CodeGenerator.withdrawMoneyCode.create(userWithdrawMoney));

        userWithdrawPlatformMoneyLog.setCreateTime(new Date());
        userWithdrawPlatformMoneyLog.setUserId(userId);
        userWithdrawPlatformMoneyLog.setMoney(money);
        userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        UserBalance userBalance=new UserBalance();
        return new WithdrawMoneyWrapper(userWithdrawMoney,userWithdrawPlatformMoneyLog,userBalance,null);
    }
    public static WithdrawMoneyWrapper newInstance(UserWithdrawMoney userWithdrawMoney){
        return new WithdrawMoneyWrapper(userWithdrawMoney,null,null,null);
    }
    public static Tuple2<UserWithdrawMoney,UserWithdrawMoneyStreamLog> newInstance(WithdrawMoneyParam withdrawMoneyParam){
        Gson gson=new Gson();
        BigDecimal bigDecimal=new BigDecimal(withdrawMoneyParam.getMoney());
        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
        UserWithdrawMoney userWithdrawMoney=new UserWithdrawMoney();
        UserWithdrawMoneyStreamLog userWithdrawPlatformMoneyLog=new UserWithdrawMoneyStreamLog();
        userWithdrawMoney.setUserId(withdrawMoneyParam.getUserId());
        //  userWithdrawMoney.setCreateTime(new Date());
        userWithdrawMoney.setMoney(money);
        userWithdrawMoney.setWithdrawStreamCode(CodeGenerator.withdrawMoneyCode.create(userWithdrawMoney));

        userWithdrawPlatformMoneyLog.setCreateTime(new Date());
        userWithdrawPlatformMoneyLog.setUserId(withdrawMoneyParam.getUserId());
        userWithdrawPlatformMoneyLog.setMoney(money);
        userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        return new Tuple2<>(userWithdrawMoney,userWithdrawPlatformMoneyLog);
    }
    //    public static WithdrawMoneyWrapper newInstance(WithdrawMoneyParam withdrawMoneyParam){
//        Gson gson=new Gson();
//        BigDecimal bigDecimal=new BigDecimal(withdrawMoneyParam.getMoney());
//        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
//        UserWithdrawMoney userWithdrawMoney=new UserWithdrawMoney();
//        UserWithdrawMoneyStreamLog userWithdrawPlatformMoneyLog=new UserWithdrawMoneyStreamLog();
//        userWithdrawMoney.setUserId(withdrawMoneyParam.getUserId());
//        //  userWithdrawMoney.setCreateTime(new Date());
//        userWithdrawMoney.setMoney(money);
//        userWithdrawMoney.setWithdrawStreamCode(CodeGenerator.withdrawMoneyCode.create(userWithdrawMoney));
//
//        userWithdrawPlatformMoneyLog.setCreateTime(new Date());
//        userWithdrawPlatformMoneyLog.setUserId(withdrawMoneyParam.getUserId());
//        userWithdrawPlatformMoneyLog.setMoney(money);
//        userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
//        userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
//       // return new Tuple2<>(userWithdrawMoney,userWithdrawPlatformMoneyLog);
//        return new WithdrawMoneyWrapper(userWithdrawMoney,userWithdrawPlatformMoneyLog,null);
//    }
    public static WithdrawMoneyWrapper newInstance(UserBalance userBalance, WithdrawMoneyParam withdrawMoneyParam){
        Gson gson=new Gson();
        UserWithdrawMoney userWithdrawMoney=new UserWithdrawMoney();
        BigDecimal bigDecimal=new BigDecimal(withdrawMoneyParam.getMoney());
        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
        UserWithdrawMoneyStreamLog userWithdrawPlatformMoneyLog=new UserWithdrawMoneyStreamLog();
        userWithdrawMoney.setUserId(withdrawMoneyParam.getUserId());
        //userWithdrawMoney.setCreateTime(new Date());
        userWithdrawMoney.setMoney(money);
        userWithdrawMoney.setWithdrawStreamCode(CodeGenerator.withdrawMoneyCode.create(userWithdrawMoney));
        userWithdrawMoney.setDirection(withdrawMoneyParam.getType());

        userWithdrawPlatformMoneyLog.setCreateTime(new Date());
        userWithdrawPlatformMoneyLog.setUserId(withdrawMoneyParam.getUserId());
        userWithdrawPlatformMoneyLog.setMoney(money);
        userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        // UserBalance userBalance=new UserBalance();
        return new WithdrawMoneyWrapper(userWithdrawMoney,userWithdrawPlatformMoneyLog,userBalance,null);
    }
    public static WithdrawMoneyWrapper newInstance(UserBalance userBalance, UserWithdrawMoney userWithdrawMoney){
        Gson gson=new Gson();
        UserWithdrawMoneyStreamLog userWithdrawPlatformMoneyLog=new UserWithdrawMoneyStreamLog();
        userWithdrawPlatformMoneyLog.setCreateTime(new Date());
        userWithdrawPlatformMoneyLog.setUserId(userWithdrawMoney.getUserId());
        userWithdrawPlatformMoneyLog.setMoney(userWithdrawMoney.getMoney());
        userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        // UserBalance userBalance=new UserBalance();
        return new WithdrawMoneyWrapper(userWithdrawMoney,userWithdrawPlatformMoneyLog,userBalance,null);
    }
}