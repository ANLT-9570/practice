package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.dao.orders.UserBalanceMapper;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.CompanyDailyStreamLogService;
import com.dg.main.serviceImpl.logs.UserDepositPlatformMoneyLogService;
import com.dg.main.serviceImpl.orders.wrapper.DepositPlatformMoneyWrapper;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
public class ThirdDepositPlatformMoneyPayedSuccessService {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    CompanyBalanceService companyBalanceService;
    @Autowired
    UserDepositPlatformMoneyLogService userDepositPlatformMoneyLogService;
    @Autowired
    UserDepositPlatformMoneyService userDepositPlatformMoneyService;
    @Autowired
    UserBalanceMapper userBalanceMapper;
    @Autowired
    CompanyDailyStreamLogService companyDailyStreamLogService;
    @Transactional(isolation = READ_COMMITTED)
    public Boolean action(DepositPlatformMoneyWrapper depositPlatformMoneyWrapper){
        UserBalance _old=depositPlatformMoneyWrapper.getUserBalance();
        for(int i=0;i<5;i++){
            UserBalance _new= userBalanceMapper.selectOneWithNoLock(_old.getId());
            Long version=_new.getOperationTimes();
            UserDepositPlatformMoney userDepositPlatformMoney=depositPlatformMoneyWrapper.getUserDepositPlatformMoney();
         //   _new.setModifyTime(new Timestamp(new Date().getTime()));
          //  _new.setOperationTimes(_old.getOperationTimes()+1);
         //   _new.setPlatformMoney(_old.getPlatformMoney()+userDepositPlatformMoney.getPlatformMoney());
            int result=    userBalanceMapper.increaseVersionUpdatePlatformMoney(version,userDepositPlatformMoney.getPlatformMoney(),_old.getId());
            if(result==0){
                continue;
            }
            userDepositPlatformMoney.setIsSuccess(1);
            userDepositPlatformMoneyService.update(userDepositPlatformMoney);
          //  userDepositPlatformMoneyLogService.save(depositPlatformMoneyWrapper.getUserDepositPlatformMoneyLog());
            _new.setPlatformMoney(_old.getPlatformMoney()+userDepositPlatformMoney.getPlatformMoney());
            _new.setOperationTimes(_old.getOperationTimes()+i+1);
            CompanyDailyStreamLog companyDailyStreamLog=new CompanyDailyStreamLog();
            LocalDateTime localDateTime=LocalDateTime.now();
            companyDailyStreamLog.setDayOfyear(localDateTime.getDayOfYear());
            companyDailyStreamLog.setMonth(localDateTime.getMonthValue());
            companyDailyStreamLog.setYears(localDateTime.getYear());
            if(userDepositPlatformMoney.getDirection()==1){
                userBalanceService.withdrawPlatformMoneyV1(_old,_new,"ThirdDepositPlatformMoneyPayedSuccessService","action",
                        userDepositPlatformMoney.getDepositStreamCode(), UserStreamEnum.ZHIFUBAO_DEPOSIT_PLATFORM_MONEY.getIndex());

                companyDailyStreamLog.setIncome(userDepositPlatformMoney.getMoney());
                companyDailyStreamLog.setZhifubaoIncome(userDepositPlatformMoney.getMoney());
                companyDailyStreamLog.setTypes(1);

            }else{
                userBalanceService.withdrawPlatformMoneyV1(_old,_new,"ThirdDepositPlatformMoneyPayedSuccessService","action",
                        userDepositPlatformMoney.getDepositStreamCode(), UserStreamEnum.WEIXIN_DEPOSIT_PLATFORM_MONEY.getIndex());
                companyDailyStreamLog.setIncome(userDepositPlatformMoney.getMoney());
                companyDailyStreamLog.setWeixinIncome(userDepositPlatformMoney.getMoney());
                companyDailyStreamLog.setTypes(2);
            }
            companyDailyStreamLogService.findRecord(companyDailyStreamLog);

            return true;
        }
        return false;


    }
}
