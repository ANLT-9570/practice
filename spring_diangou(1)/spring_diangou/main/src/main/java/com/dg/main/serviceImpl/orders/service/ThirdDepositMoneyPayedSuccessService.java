package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.serviceImpl.logs.UserDepositMoneyLogService;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.DepositMoneyWrapper;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.Date;

@Service
public class ThirdDepositMoneyPayedSuccessService {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    CompanyBalanceService companyBalanceService;
    @Autowired
    UserDepositMoneyService userDepositMoneyService;
    @Autowired
    UserDepositMoneyLogService userDepositMoneyLogService;
    @Transactional
    public void action(DepositMoneyWrapper depositMoneyWrapper){
        UserBalance _old=depositMoneyWrapper.getUserBalance();
        UserBalance _new= UserBalanceFactory.newInstance(_old);
        UserDepositMoney userDepositMoney=depositMoneyWrapper.getUserDepositMoney();
        _new.setOperationTimes(_old.getOperationTimes()+1);
        _new.setModifyTime(new Timestamp(new Date().getTime()));
        _new.setMoney(_old.getMoney()+userDepositMoney.getMoney());

        userDepositMoney.setIsSuccess(1);
        userDepositMoneyService.update(userDepositMoney);
        userDepositMoneyLogService.save(depositMoneyWrapper.getUserDepositMoneyLog());
        userBalanceService.withdrawMoney(_old,_new,"ThirdDepositMoneyPayedSuccessService","action",userDepositMoney.getDepositCode());
    }
}
