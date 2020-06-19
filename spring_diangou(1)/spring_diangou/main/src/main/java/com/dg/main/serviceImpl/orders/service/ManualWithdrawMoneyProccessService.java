package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.CompanyDailyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ManualWithdrawMoneyProccessService {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserWithdrawMoneyService userWithdrawMoneyService;
    @Autowired
    UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Autowired
    CompanyDailyStreamLogService companyDailyStreamLogService;
    @Transactional
    public void action(WithdrawMoneyWrapper withdrawMoneyWrapper){
        UserBalance _old=withdrawMoneyWrapper.getUserBalance();
        UserBalance _new= UserBalanceFactory.newInstance(_old);
        UserWithdrawMoneyStreamLog withdrawMoneyStreamLog = withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
        UserWithdrawMoney userWithdrawMoney = withdrawMoneyWrapper.getUserWithdrawMoney();
        _new.setModifyTime(new Timestamp(new Date().getTime()));
        _new.setOperationTimes(_old.getOperationTimes()+1);
        _new.setMoney(_old.getMoney()-userWithdrawMoney.getMoney());
        userWithdrawMoney.setIsSuccess(1);
        userWithdrawMoney.setIsAudit(1);
        userWithdrawMoneyService.update(userWithdrawMoney);
        userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
        userBalanceService.withdrawMoney(_old,_new,"ManualWithdrawMoneyProccessService","action",userWithdrawMoney.getWithdrawStreamCode());

    }
    @Transactional
    public void actionv2(WithdrawMoneyWrapper withdrawMoneyWrapper){
        UserWithdrawMoneyStreamLog withdrawMoneyStreamLog = withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
        UserWithdrawMoney userWithdrawMoney = withdrawMoneyWrapper.getUserWithdrawMoney();
        userWithdrawMoney.setIsSuccess(1);
        userWithdrawMoney.setIsAudit(1);
        userWithdrawMoneyService.update(userWithdrawMoney);
     //   userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
        CompanyDailyStreamLog companyDailyStreamLog=new CompanyDailyStreamLog();
        LocalDateTime localDateTime=LocalDateTime.now();
        companyDailyStreamLog.setDayOfyear(localDateTime.getDayOfYear());
        companyDailyStreamLog.setMonth(localDateTime.getMonthValue());
        companyDailyStreamLog.setYears(localDateTime.getYear());
        companyDailyStreamLog.setOutcome(userWithdrawMoney.getMoney());
      //  companyDailyStreamLog.setBuyOutcome(userWithdrawMoney.getMoney());
        if(userWithdrawMoney.getDirection()==1){
            companyDailyStreamLog.setZhifubaoOutcome(userWithdrawMoney.getMoney());
        }else{
            companyDailyStreamLog.setWeixinOutcome(userWithdrawMoney.getMoney());
        }
        companyDailyStreamLogService.findRecord(companyDailyStreamLog);
      //  userBalanceService.withdrawMoney(_old,_new,"ManualWithdrawMoneyProccessService","action",userWithdrawMoney.getWithdrawStreamCode());
    }
    @Transactional
    public void save(WithdrawMoneyWrapper withdrawMoneyWrapper){
        Gson gson=new Gson();
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
        UserWithdrawMoneyStreamLog userWithdrawMoneyStreamLog=withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
        userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
        userWithdrawMoneyStreamLog.setMoney(userWithdrawMoney.getMoney());
        userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawMoneyStreamLogService.save(userWithdrawMoneyStreamLog);
        userWithdrawMoneyService.save(userWithdrawMoney);
    }
    @Transactional
    public void saveFailureLog(UserWithdrawMoneyStreamLog userWithdrawMoneyStreamLog,UserWithdrawMoney userWithdrawMoney){
        userWithdrawMoneyService.update(userWithdrawMoney);
        userWithdrawMoneyStreamLogService.save(userWithdrawMoneyStreamLog);

    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean actionv1(WithdrawMoneyWrapper withdrawMoneyWrapper){
        Gson gson=new Gson();
        UserBalance _old=withdrawMoneyWrapper.getUserBalance();
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
        for(int i=0;i<5;i++){

            UserBalance _new=userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=_new.getOperationTimes();
            int result=userBalanceMapper.decreaseVersionUpdateMoney(version,userWithdrawMoney.getMoney(),_old.getId());
            if(result==0){
                continue;
            }

            userWithdrawMoney.setIsAudit(1);
            userWithdrawMoney.setIsSuccess(1);
            //  userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
            userWithdrawMoneyService.update(userWithdrawMoney);
            UserWithdrawMoneyStreamLog userWithdrawMoneyStreamLog=withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
            userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
            userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
            userWithdrawMoneyStreamLog.setMoney(userWithdrawMoney.getMoney());
            userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
            userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
            _new.setMoney(_old.getMoney()-userWithdrawMoney.getMoney());
            _new.setOperationTimes(_old.getOperationTimes()+i+1);
            userBalanceService.withdrawMoneyV1(_old,_new,"ManualWithdrawMoneyProccessService","actionv1",
                    userWithdrawMoney.getWithdrawStreamCode(), UserStreamEnum.WITHDRAW_MONEY.getIndex());
            return true;





        }
        return false;
//        UserBalance _old=withdrawMoneyWrapper.getUserBalance();
//        UserBalance _new= UserBalanceFactory.newInstance(_old);
//        UserWithdrawMoneyStreamLog withdrawMoneyStreamLog = withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
//        UserWithdrawMoney userWithdrawMoney = withdrawMoneyWrapper.getUserWithdrawMoney();
//
//        _new.setModifyTime(new Timestamp(new Date().getTime()));
//        _new.setOperationTimes(_old.getOperationTimes()+1);
//        _new.setMoney(_old.getMoney()-userWithdrawMoney.getMoney());
////        userWithdrawMoney.setIsSuccess(1);
////        userWithdrawMoney.setIsAudit(1);
//        userWithdrawMoneyService.update(userWithdrawMoney);
//        userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
//        userBalanceService.withdrawMoney(_old,_new,"ManualWithdrawMoneyProccessService","action",userWithdrawMoney.getWithdrawStreamCode());

    }

}
