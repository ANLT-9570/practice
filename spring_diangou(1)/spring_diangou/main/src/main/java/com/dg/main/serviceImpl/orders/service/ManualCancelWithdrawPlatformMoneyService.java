package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManualCancelWithdrawPlatformMoneyService {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
    @Autowired
    UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean action(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        UserBalance _old=withdrawPlatformMoneyWrapper.getUserBalance();
        UserWithdrawPlatformMoney userWithdrawPlatformMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        for(int i=0;i<5;i++){
            UserBalance userBalance=userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=userBalance.getOperationTimes();
            int result=userBalanceMapper.increaseVersionUpdatePlatformMoney(version,userWithdrawPlatformMoney.getPlatformMoney(),_old.getId());
            if(result==0){
                continue;
            }
            userWithdrawPlatformMoney.setIsSuccess(2);
            userWithdrawPlatformMoney.setIsAudit(1);
            userWithdrawPlatformMoneyService.update(userWithdrawPlatformMoney);
            userBalance.setPlatformMoney(_old.getPlatformMoney()+userWithdrawPlatformMoney.getPlatformMoney());
            userBalance.setOperationTimes(_old.getOperationTimes()+i+1);
            userWithdrawPlatformMoneyLogService.save(withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog());
            userBalanceService.withdrawPlatformMoneyV1(_old,userBalance,"ManualCancelWithdrawPlatformMoneyService","action"
                    ,userWithdrawPlatformMoney.getWithdrawStreamCode(), UserStreamEnum.CANCEL_WITHDRAW_PLATFROM_MONEY.getIndex());
            return true;
        }
        return false;

    }
}
