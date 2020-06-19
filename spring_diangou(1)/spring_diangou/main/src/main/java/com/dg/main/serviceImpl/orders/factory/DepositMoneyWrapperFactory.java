package com.dg.main.serviceImpl.orders.factory;

import com.google.gson.Gson;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.Entity.logs.UserDepositMoneyLog;
import com.dg.main.param.orders.DepositMoneyParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.orders.wrapper.DepositMoneyWrapper;
import com.dg.main.util.Tuple2;

public class DepositMoneyWrapperFactory {
    public static DepositMoneyWrapper newInstance(Long userId,Long money){
        Gson gson=new Gson();
        UserDepositMoney userDepositMoney=new UserDepositMoney();
     //   userDepositMoney.setCreateTime(new Date());
        userDepositMoney.setDepositCode(CodeGenerator.depositMoneyCode.create(userDepositMoney));
        userDepositMoney.setUserId(userId);
        userDepositMoney.setMoney(money);

        UserDepositMoneyLog userDepositMoneyLog=new UserDepositMoneyLog();
       // userDepositMoneyLog.setCreateTime(new Date());
        userDepositMoneyLog.setJsonOfUserDepositMoney(gson.toJson(userDepositMoney));
        userDepositMoneyLog.setMoney(money);
        userDepositMoneyLog.setUserId(userId);
        UserBalance userBalance=new UserBalance();

        return new DepositMoneyWrapper(userDepositMoney,userDepositMoneyLog,userBalance);
    }
    public static DepositMoneyWrapper newInstance(UserDepositMoney userDepositMoney){
        return new DepositMoneyWrapper(userDepositMoney,null,null);
    }
    public static Tuple2<UserDepositMoney,UserDepositMoneyLog> newInstance(Long userId, Long money, Integer action){
        Gson gson=new Gson();
        UserDepositMoney userDepositMoney=new UserDepositMoney();
      //  userDepositMoney.setCreateTime(new Date());
        userDepositMoney.setDepositCode(CodeGenerator.depositMoneyCode.create(userDepositMoney));
        userDepositMoney.setUserId(userId);
        userDepositMoney.setMoney(money);
        userDepositMoney.setDirection(action);

        UserDepositMoneyLog userDepositMoneyLog=new UserDepositMoneyLog();
      //  userDepositMoneyLog.setCreateTime(new Date());
        userDepositMoneyLog.setJsonOfUserDepositMoney(gson.toJson(userDepositMoney));
        userDepositMoneyLog.setMoney(money);
        userDepositMoneyLog.setUserId(userId);

        return new Tuple2(userDepositMoney,userDepositMoneyLog);
    }
    public static DepositMoneyWrapper newInstance(UserBalance userBalance, DepositMoneyParam param){
        Gson gson=new Gson();
        UserDepositMoney userDepositMoney=new UserDepositMoney();
       // userDepositMoney.setCreateTime(new Date());
        userDepositMoney.setDepositCode(CodeGenerator.depositMoneyCode.create(userDepositMoney));
        userDepositMoney.setUserId(param.getUserId());
        userDepositMoney.setMoney(param.getMoney());
        userDepositMoney.setDirection(param.getType());


        UserDepositMoneyLog userDepositMoneyLog=new UserDepositMoneyLog();
       // userDepositMoneyLog.setCreateTime(new Date());
        userDepositMoneyLog.setJsonOfUserDepositMoney(gson.toJson(userDepositMoney));
        userDepositMoneyLog.setMoney(param.getMoney());
        userDepositMoneyLog.setUserId(param.getUserId());
        UserBalance balance=userBalance;

        return new DepositMoneyWrapper(userDepositMoney,userDepositMoneyLog,balance);
    }
    public static DepositMoneyWrapper newInstance(UserBalance userBalance, UserDepositMoney userDepositMoney){
        Gson gson=new Gson();
//        UserDepositMoney userDepositMoney=new UserDepositMoney();
//        // userDepositMoney.setCreateTime(new Date());
//        userDepositMoney.setDepositCode(CodeGenerator.depositMoneyCode.create(userDepositMoney));
//        userDepositMoney.setUserId(param.getUserId());
//        userDepositMoney.setMoney(param.getMoney());
//        userDepositMoney.setDirection(param.getType());


        UserDepositMoneyLog userDepositMoneyLog=new UserDepositMoneyLog();
        // userDepositMoneyLog.setCreateTime(new Date());
        userDepositMoneyLog.setJsonOfUserDepositMoney(gson.toJson(userDepositMoney));
        userDepositMoneyLog.setMoney(userDepositMoney.getMoney());
        userDepositMoneyLog.setUserId(userDepositMoney.getUserId());
        UserBalance balance=userBalance;

        return new DepositMoneyWrapper(userDepositMoney,userDepositMoneyLog,balance);
    }
}
