package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WithDrawPlatformMoneyCreateService {
    @Autowired
    private UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
    @Autowired
    private UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Transactional
    public void save(final WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        Gson gson=new Gson();
        UserWithdrawPlatformMoney userDepositPlatformMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserBalance oldObj=withdrawPlatformMoneyWrapper.getUserBalance();
        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
        newObj.setModifyTime(new Date());
        newObj.setPlatformMoney(oldObj.getPlatformMoney()-userDepositPlatformMoney.getPlatformMoney());
        userBalanceService.update(newObj);

        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        userMoneyStreamLog.setUserId(oldObj.getUserId());
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
      //  userMoneyStreamLogService.save(userMoneyStreamLog);
        userWithdrawPlatformMoneyService.save(userDepositPlatformMoney);
        userWithdrawPlatformMoneyLogService.save(withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog());
    }
}
