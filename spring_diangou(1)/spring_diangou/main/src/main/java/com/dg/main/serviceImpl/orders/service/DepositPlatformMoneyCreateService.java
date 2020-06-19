package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.UserDepositPlatformMoneyLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.serviceImpl.orders.wrapper.DepositPlatformMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositPlatformMoneyCreateService {
    @Autowired
    private UserDepositPlatformMoneyService userDepositPlatformMoneyService;
    @Autowired
    private UserDepositPlatformMoneyLogService userDepositPlatformMoneyLogService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Transactional
    public void save(DepositPlatformMoneyWrapper depositPlatformMoneyWrapper) {
        Gson gson = new Gson();
        UserDepositPlatformMoney userDepositPlatformMoney=depositPlatformMoneyWrapper.getUserDepositPlatformMoney();
//        UserBalance oldObj=depositPlatformMoneyWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
//        newObj.setModifyTime(new Date());
//        newObj.setPlatformMoney(oldObj.getPlatformMoney()+userDepositPlatformMoney.getPlatformMoney());
//        userBalanceService.update(newObj);

//        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
//        userMoneyStreamLog.setUserId(oldObj.getUserId());
//        userMoneyStreamLog.setCreateTime(new Date());
//        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
//        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
//        userMoneyStreamLogService.save(userMoneyStreamLog);
        userDepositPlatformMoneyService.save(userDepositPlatformMoney);
     //   userDepositPlatformMoneyLogService.save(depositPlatformMoneyWrapper.getUserDepositPlatformMoneyLog());
    }
}
