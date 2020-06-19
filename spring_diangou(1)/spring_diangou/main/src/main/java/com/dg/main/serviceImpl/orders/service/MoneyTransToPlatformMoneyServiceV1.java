package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.orders.MoneyTransToPlatformMoney;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyV1Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class MoneyTransToPlatformMoneyServiceV1 {
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;

    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Autowired
    MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean action(MoneyTransToPlatformMoneyV1Wrapper moneyTransToPlatformMoneyV1Wrapper){
        UserBalance _old=moneyTransToPlatformMoneyV1Wrapper.getUserBalance();
        UserBalance _new= UserBalanceFactory.newInstance(_old);
        MoneyTransToPlatformMoney moneyTransToPlatformMoney=new MoneyTransToPlatformMoney();

        moneyTransToPlatformMoney.setCode(CodeGenerator.moneyTransToPlatformMoneyCode.create(moneyTransToPlatformMoney));
        moneyTransToPlatformMoney.setCreateTime(new Date());
        moneyTransToPlatformMoney.setMoney(moneyTransToPlatformMoneyV1Wrapper.getMoney());

        moneyTransToPlatformMoney.setCurrentRate(10l);
        moneyTransToPlatformMoney.setPlatformMoney(moneyTransToPlatformMoneyV1Wrapper.getMoney()*10);
        moneyTransToPlatformMoney.setUserId(_old.getUserId());
        for(int i=0;i<5;i++){
            UserBalance _old1=userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=_old1.getOperationTimes();
            int result=userBalanceMapper.transToPlatform(version,_old.getId(),moneyTransToPlatformMoneyV1Wrapper.getPlatformMoney(),moneyTransToPlatformMoneyV1Wrapper.getMoney());
            if(result==0){
                continue;
            }
            _new.setOperationTimes(_old.getOperationTimes()+i+1);
            _new.setMoney(_old.getMoney()-moneyTransToPlatformMoneyV1Wrapper.getMoney());
            _new.setPlatformMoney(_old.getPlatformMoney()+moneyTransToPlatformMoneyV1Wrapper.getPlatformMoney());
         //   moneyTransToPlatformMoneyService.save(moneyTransToPlatformMoney);
            userMoneyStreamLogService.saveUserBalances(_old,_new,"MoneyTransToPlatformMoneyServiceV1","action",
                    UserStreamEnum.MONEY_TO_PLATFORM_MONEY.getIndex(),moneyTransToPlatformMoney.getCode());
            return true;
        }
        return false;
    }
}
