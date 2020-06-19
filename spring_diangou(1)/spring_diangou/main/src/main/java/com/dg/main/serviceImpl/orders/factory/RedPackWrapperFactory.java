package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.param.orders.RedPackParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.orders.wrapper.RedPackWrapper;
import com.dg.main.util.DateUtils;


import java.sql.Timestamp;
import java.util.Date;

public class RedPackWrapperFactory {
    public static RedPackWrapper newInstance(){
        UserBalance userBalance=new UserBalance();
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        RedPack redPack=new RedPack();
        return new RedPackWrapper(userBalance,redPack);
    }
    public static RedPackWrapper newInstance(UserBalance userBalance, RedPackParam redPackParam){
        RedPack redPack=new RedPack();
        redPack.setCreateTime(new Timestamp(new Date().getTime()));
        redPack.setCode(CodeGenerator.redpackCode.create(redPack));
        redPack.setPlatformMoney(redPackParam.getPlatformMoney());
        redPack.setUserId(userBalance.getUserId());
        redPack.setVersion(1l);
        redPack.setSchedulerTime(new Timestamp(DateUtils.addDay(new Date(),1).getTime()));
        redPack.setSendTime(redPackParam.getSendTime());
        redPack.setTakeNumber(redPackParam.getNumber());
        redPack.setShopId(redPackParam.getShopId());

        return new RedPackWrapper(userBalance,redPack);
    }
}
