package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManualCancelWithdrawMoneyService {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserWithdrawMoneyService userWithdrawMoneyService;
    @Autowired
    UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean action(WithdrawMoneyWrapper withdrawMoneyWrapper){
        UserBalance _old=withdrawMoneyWrapper.getUserBalance();
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
    //    UserBalance _new= UserBalanceFactory.newInstance(_old);
        for(int i=0;i<5;i++){
            UserBalance _new=userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=_new.getOperationTimes();
            int result=userBalanceMapper.increaseVersionUpdateMoney(version,userWithdrawMoney.getMoney(),_old.getId());
            if(result==0){
                continue;
            }

            userWithdrawMoney.setIsAudit(1);
            userWithdrawMoney.setIsSuccess(2);
         //   userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
            userWithdrawMoneyService.update(userWithdrawMoney);
            _new.setMoney(_old.getMoney()+userWithdrawMoney.getMoney());
            _new.setOperationTimes(_old.getOperationTimes()+i+1);
            userBalanceService.withdrawMoneyV1(_old,_new,"ManualCancelWithdrawMoneyService","action",
                    userWithdrawMoney.getWithdrawStreamCode(), UserStreamEnum.CANCEL_WITHDRAW_MOENY.getIndex());
            return true;


        }

        return false;
    }
}
