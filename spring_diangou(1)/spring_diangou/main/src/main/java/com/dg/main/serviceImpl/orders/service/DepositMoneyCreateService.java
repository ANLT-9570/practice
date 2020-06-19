package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.logs.UserDepositMoneyLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.Entity.logs.UserDepositMoneyLog;
import com.dg.main.Entity.logs.UserMoneyStreamLog;
import com.dg.main.serviceImpl.orders.wrapper.DepositMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositMoneyCreateService {
    @Autowired
    private UserDepositMoneyService userDepositMoneyService;
    @Autowired
    private UserDepositMoneyLogService userDepositMoneyLogService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Transactional
    public void save(DepositMoneyWrapper depositMoneyWrapper){
        Gson gson=new Gson();
        UserDepositMoney userDepositMoney=depositMoneyWrapper.getUserDepositMoney();
        UserDepositMoneyLog userDepositMoneyLog=depositMoneyWrapper.getUserDepositMoneyLog();
        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();

      //  UserBalance oldObj=depositMoneyWrapper.getUserBalance();
      //  UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
      //  newObj.setOperationTimes(oldObj.getOperationTimes()+1);
      //  newObj.setMoney(oldObj.getMoney()+userDepositMoney.getMoney());
      //  newObj.setModifyTime(new Date());
      //  userBalanceService.update(newObj);
     //   userMoneyStreamLog.setUserId(oldObj.getUserId());
     ///   userMoneyStreamLog.setCreateTime(new Date());
     //   userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
     //   userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));

      //  userMoneyStreamLogService.save(userMoneyStreamLog);

        userDepositMoneyService.save(userDepositMoney);
        userDepositMoneyLogService.save(userDepositMoneyLog);
    }
}
