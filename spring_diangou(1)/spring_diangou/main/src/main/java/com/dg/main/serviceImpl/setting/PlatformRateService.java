package com.dg.main.serviceImpl.setting;

import com.dg.main.Entity.settings.PlatformRate;
import com.dg.main.repository.orders.PlatformRateRepository;
import com.dg.main.serviceImpl.orders.service.MoneyTransToPlatformMoneyRateService;
import com.dg.main.Entity.orders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PlatformRateService {
    @Autowired
    PlatformRateRepository repository;
    @Autowired
    MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateService;
    @PostConstruct
    public void post(){
        PlatformRate platformRate=repository.findByTypes(1);
        if(platformRate==null){
            platformRate=new PlatformRate();
            platformRate.setRate(4l);
            platformRate.setTypes(1);
            repository.save(platformRate);
        }
    }
    public PlatformRate findBy(Integer id) {

        return repository.getOne(id);
    }


    public PlatformRate getRate(){
        return repository.findByTypes(1);
    }
    public  void calDeposit(UserDepositPlatformMoney userDepositPlatformMoney){
   //     Long platformRate=getRate().getRate();
        if(userDepositPlatformMoney.getDirection()==1){
            Long money=userDepositPlatformMoney.getMoney();//100
         //   MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateService.getRate();
         //   BigDecimal transToRate=new BigDecimal(moneyTransToPlatformMoneyRate.getRate());//1
          //  Long platformMoney=money*(ThirdPayRate.ZHIFUBAO_RATE+platformRate)/1000;
         //   BigDecimal leftMoney=new BigDecimal(money-platformMoney);
          //  BigDecimal userPlatformMoney=leftMoney.multiply(transToRate).divide(new BigDecimal(100),0, RoundingMode.HALF_UP);
        //    BigDecimal userPlatformMoney=new BigDecimal(money).multiply(transToRate).divide(new BigDecimal(100),0,RoundingMode.HALF_UP);
           // userDepositPlatformMoney.setLeftMoney(leftMoney.longValue());
          //  userDepositPlatformMoney.setPlatformMoney(userPlatformMoney.longValue());
             userDepositPlatformMoney.setLeftMoney(0l);
              userDepositPlatformMoney.setPlatformMoney(money/10);

        }else if(userDepositPlatformMoney.getDirection()==2){
            Long money=userDepositPlatformMoney.getMoney();
//            MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateService.getRate();
//            BigDecimal transToRate=new BigDecimal(moneyTransToPlatformMoneyRate.getRate());//1
//            Long platformMoney=money*(ThirdPayRate.WEIXIN_RATE+platformRate)/1000;
//            BigDecimal leftMoney=new BigDecimal(money-platformMoney);
//            //  BigDecimal userPlatformMoney=leftMoney.multiply(transToRate).divide(new BigDecimal(100),0, RoundingMode.HALF_UP);
//            BigDecimal userPlatformMoney=new BigDecimal(money).multiply(transToRate).divide(new BigDecimal(100),0,RoundingMode.HALF_UP);
//            userDepositPlatformMoney.setLeftMoney(leftMoney.longValue());
//            userDepositPlatformMoney.setPlatformMoney(userPlatformMoney.longValue());
            userDepositPlatformMoney.setLeftMoney(0l);
            userDepositPlatformMoney.setPlatformMoney(money/10);
        }

    }



}
