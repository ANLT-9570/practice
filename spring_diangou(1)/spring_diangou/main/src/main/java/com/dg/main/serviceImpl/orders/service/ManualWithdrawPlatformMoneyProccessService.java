package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import com.dg.main.Entity.logs.CompanyMoneyStreamLog;
import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;
import com.dg.main.Entity.settings.CompanyBalance;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.logs.CompanyDailyStreamLogService;
import com.dg.main.serviceImpl.logs.CompanyMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.orders.factory.CompanyBalanceFactory;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ManualWithdrawPlatformMoneyProccessService {
    @Autowired
    UserBalanceService userBalanceService;

    @Autowired
    UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
    @Autowired
    UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
    @Autowired
    CompanyBalanceService companyBalanceService;
    @Autowired
    CompanyMoneyStreamLogService companyMoneyStreamLogService;
    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Autowired
    CompanyDailyStreamLogService companyDailyStreamLogService;
    @Transactional
    public void action(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        Gson gson=new Gson();
        UserBalance _old=withdrawPlatformMoneyWrapper.getUserBalance();
        UserBalance _new= UserBalanceFactory.newInstance(_old);
        UserWithdrawPlatformMoney userWithdrawPlatformMoney = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
        _new.setOperationTimes(_old.getOperationTimes()+1);
        _new.setModifyTime(new Timestamp(new Date().getTime()));
        _new.setPlatformMoney(_old.getPlatformMoney()-userWithdrawPlatformMoney.getPlatformMoney());
        userWithdrawPlatformMoney.setIsSuccess(1);
        userWithdrawPlatformMoney.setIsAudit(1);
        userWithdrawPlatformMoneyService.update(userWithdrawPlatformMoney);
        userWithdrawPlatformMoneyLogService.save(userWithdrawPlatformMoneyLog);
        userBalanceService.withdrawPlatformMoney(_old,_new,"ManualWithdrawPlatformMoneyProccessService","action",userWithdrawPlatformMoney.getWithdrawStreamCode());
        CompanyBalance companyBalanceOld=companyBalanceService.getBalance();
        CompanyBalance companyBalanceNew= CompanyBalanceFactory.newInstance(companyBalanceOld);
        companyBalanceNew.setPlatformMoney(companyBalanceOld.getPlatformMoney()+userWithdrawPlatformMoney.getLeftMoney()/10);
    //    companyBalanceService.save(companyBalanceOld,companyBalanceNew,_new);
        CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
        companyMoneyStreamLog.setCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
        companyMoneyStreamLog.setIncomePlatformMoney(userWithdrawPlatformMoney.getLeftMoney()/10);
        companyMoneyStreamLog.setJsonOfCurrentCompanyBalance(gson.toJson(companyBalanceNew));
        companyMoneyStreamLog.setJsonOfPreviousCompanyBalance(gson.toJson(companyBalanceOld));
        companyMoneyStreamLog.setSellerId(userWithdrawPlatformMoney.getUserId());
        companyMoneyStreamLog.setBuyerId(userWithdrawPlatformMoney.getUserId());
        companyMoneyStreamLog.setStatus(userWithdrawPlatformMoney.getDirection());
        companyMoneyStreamLog.setTypes(2);
        companyMoneyStreamLogService.save(companyMoneyStreamLog);


    }
    @Transactional
    public void actionv2(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        Gson gson=new Gson();
//        UserBalance _old=withdrawPlatformMoneyWrapper.getUserBalance();
//        UserBalance _new= UserBalanceFactory.newInstance(_old);
        UserWithdrawPlatformMoney userWithdrawPlatformMoney = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
        userWithdrawPlatformMoney.setIsSuccess(1);
        userWithdrawPlatformMoney.setIsAudit(1);
        userWithdrawPlatformMoneyService.update(userWithdrawPlatformMoney);
        userWithdrawPlatformMoneyLogService.save(userWithdrawPlatformMoneyLog);
      //  userBalanceService.withdrawPlatformMoney(_old,_new,"ManualWithdrawPlatformMoneyProccessService","action",userWithdrawPlatformMoney.getWithdrawStreamCode());
        CompanyBalance companyBalanceOld=companyBalanceService.getBalance();
        CompanyBalance companyBalanceNew= CompanyBalanceFactory.newInstance(companyBalanceOld);
        companyBalanceNew.setPlatformMoney(companyBalanceOld.getPlatformMoney()+userWithdrawPlatformMoney.getLeftMoney()/10);
        //    companyBalanceService.save(companyBalanceOld,companyBalanceNew,_new);
        CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
        companyMoneyStreamLog.setCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
        companyMoneyStreamLog.setIncomePlatformMoney(userWithdrawPlatformMoney.getLeftMoney()/10);
        companyMoneyStreamLog.setJsonOfCurrentCompanyBalance(gson.toJson(companyBalanceNew));
        companyMoneyStreamLog.setJsonOfPreviousCompanyBalance(gson.toJson(companyBalanceOld));
        companyMoneyStreamLog.setSellerId(userWithdrawPlatformMoney.getUserId());
        companyMoneyStreamLog.setBuyerId(userWithdrawPlatformMoney.getUserId());
        companyMoneyStreamLog.setStatus(userWithdrawPlatformMoney.getDirection());
        companyMoneyStreamLog.setTypes(2);
    //    companyMoneyStreamLogService.save(companyMoneyStreamLog);
        CompanyDailyStreamLog companyDailyStreamLog=new CompanyDailyStreamLog();
        LocalDateTime localDateTime=LocalDateTime.now();
        companyDailyStreamLog.setDayOfyear(localDateTime.getDayOfYear());
        companyDailyStreamLog.setMonth(localDateTime.getMonthValue());
        companyDailyStreamLog.setYears(localDateTime.getYear());
       // companyDailyStreamLog.setBuyOutcome(userWithdrawPlatformMoney.getPlatformMoney());
        companyDailyStreamLog.setOutcome(userWithdrawPlatformMoney.getMoney());
        if(userWithdrawPlatformMoney.getDirection()==1){
            companyDailyStreamLog.setZhifubaoOutcome(userWithdrawPlatformMoney.getMoney()+userWithdrawPlatformMoney.getLeftMoney());
        }else{
            companyDailyStreamLog.setWeixinOutcome(userWithdrawPlatformMoney.getMoney()+userWithdrawPlatformMoney.getLeftMoney());
        }
        companyDailyStreamLogService.findRecord(companyDailyStreamLog);
    }
    @Transactional
    public void  save(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        Gson gson=new Gson();
        UserWithdrawPlatformMoney userWithdrawMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserWithdrawPlatformMoneyLog userWithdrawMoneyStreamLog=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
        userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
        userWithdrawMoneyStreamLog.setPlatformMoney(userWithdrawMoney.getPlatformMoney());
        userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        userWithdrawPlatformMoneyLogService.save(userWithdrawMoneyStreamLog);
        userWithdrawPlatformMoneyService.save(userWithdrawMoney);
    }
    @Transactional
    public void saveFailureLog(UserWithdrawPlatformMoneyLog userWithdrawMoneyStreamLog, UserWithdrawPlatformMoney userWithdrawMoney){
        userWithdrawPlatformMoneyLogService.save(userWithdrawMoneyStreamLog);
        userWithdrawPlatformMoneyService.update(userWithdrawMoney);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Boolean actionV1(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper){
        Gson gson=new Gson();
        UserBalance _old=withdrawPlatformMoneyWrapper.getUserBalance();
        UserWithdrawPlatformMoney userWithdrawPlatformMoney = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog = withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
        for(int i=0;i<5;i++){
            UserBalance userBalance=userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=userBalance.getOperationTimes();
            System.out.println(userWithdrawPlatformMoney.getPlatformMoney()+"");
            int result=userBalanceMapper.decreaseVersionUpdatePlatformMoney(version,userWithdrawPlatformMoney.getPlatformMoney(),_old.getId());
            if(result==0){
                continue;
            }
            userWithdrawPlatformMoney.setIsSuccess(1);
            userWithdrawPlatformMoney.setIsAudit(1);
            userWithdrawPlatformMoneyService.update(userWithdrawPlatformMoney);
            //   userWithdrawPlatformMoneyLogService.save(userWithdrawPlatformMoneyLog);
            UserWithdrawPlatformMoneyLog userWithdrawMoneyStreamLog=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
            userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawPlatformMoney));
            userWithdrawMoneyStreamLog.setUserId(userWithdrawPlatformMoney.getUserId());
            userWithdrawMoneyStreamLog.setPlatformMoney(userWithdrawPlatformMoney.getPlatformMoney());
            userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
            userWithdrawPlatformMoneyLogService.save(userWithdrawMoneyStreamLog);
            userBalance.setPlatformMoney(_old.getPlatformMoney()-userWithdrawPlatformMoney.getPlatformMoney());
            userBalance.setOperationTimes(_old.getOperationTimes()+i+1);
            userBalanceService.withdrawPlatformMoneyV1(_old,userBalance,"ManualWithdrawPlatformMoneyProccessService","actionV1"
                    ,userWithdrawPlatformMoney.getWithdrawStreamCode(), UserStreamEnum.WITHDRAW_PLATFORM_MONEY.getIndex());
//            CompanyBalance companyBalanceOld=companyBalanceService.getBalance();
//            CompanyBalance companyBalanceNew= CompanyBalanceFactory.newInstance(companyBalanceOld);
//            companyBalanceNew.setPlatformMoney(companyBalanceOld.getPlatformMoney()+userWithdrawPlatformMoney.getLeftMoney()/10);
//            //    companyBalanceService.save(companyBalanceOld,companyBalanceNew,_new);
//            CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
//            companyMoneyStreamLog.setCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
//            companyMoneyStreamLog.setIncomePlatformMoney(userWithdrawPlatformMoney.getLeftMoney()/10);
//            companyMoneyStreamLog.setJsonOfCurrentCompanyBalance(gson.toJson(companyBalanceNew));
//            companyMoneyStreamLog.setJsonOfPreviousCompanyBalance(gson.toJson(companyBalanceOld));
//            companyMoneyStreamLog.setSellerId(userWithdrawPlatformMoney.getUserId());
//            companyMoneyStreamLog.setBuyerId(userWithdrawPlatformMoney.getUserId());
//            companyMoneyStreamLog.setStatus(userWithdrawPlatformMoney.getDirection());
//            companyMoneyStreamLog.setTypes(2);
//            companyMoneyStreamLogService.save(companyMoneyStreamLog);
            return true;
        }
        return false;
    }
}
