package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WithDrawMoneyCreateService {
    @Autowired
    private UserWithdrawMoneyService userWithdrawMoneyService;
    @Autowired
    private UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Transactional
    public void save(WithdrawMoneyWrapper withdrawMoneyWrapper){
        Gson gson=new Gson();
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
        UserBalance oldObj=withdrawMoneyWrapper.getUserBalance();
        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
        newObj.setModifyTime(new Date());
        newObj.setMoney(oldObj.getMoney()-userWithdrawMoney.getMoney());
        userBalanceService.update(newObj);

        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
        userMoneyStreamLog.setUserId(oldObj.getUserId());
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
       // userMoneyStreamLogService.save(userMoneyStreamLog);
        userWithdrawMoneyService.save(userWithdrawMoney);
      //  userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
    }
}
