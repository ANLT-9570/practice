package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.MoneyTransToPlatformMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MoneyTransToPlatformMoneyCreateService {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private MoneyTransToPlatformMoneyStreamLogService moneyTransToPlatformMoneyStreamLogService;
    @Autowired
    private MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Transactional
    public void save(MoneyTransToPlatformMoneyWrapper moneyTransToPlatformMoneyWrapper) {
        Gson gson=new Gson();
        UserBalance oldObj=moneyTransToPlatformMoneyWrapper.getUserBalance();
        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
        newObj.setMoney(newObj.getMoney()-moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoney().getMoney());
        newObj.setPlatformMoney(moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoney().getPlatformMoney()+newObj.getPlatformMoney());

        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
        userBalanceService.update(newObj);
        UserMoneyStreamLog userMoneyStreamLog=moneyTransToPlatformMoneyWrapper.getUserMoneyStreamLog();
        userMoneyStreamLog.setCreateTime(new Date());
        userMoneyStreamLog.setUserId(oldObj.getUserId());
        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
       // userMoneyStreamLogService.save(userMoneyStreamLog);
        moneyTransToPlatformMoneyStreamLogService.save(moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoneyStreamLog());
    }
}
