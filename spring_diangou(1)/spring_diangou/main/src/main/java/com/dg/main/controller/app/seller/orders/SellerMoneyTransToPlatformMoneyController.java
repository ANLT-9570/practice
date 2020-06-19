package com.dg.main.controller.app.seller.orders;

import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.MoneyTransToPlatformMoneyParam;
import com.dg.main.serviceImpl.logs.MoneyTransToPlatformMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.MoneyTransToPlatformMoneyCreateProccess;
import com.dg.main.serviceImpl.orders.factory.MoneyTransToPlatformMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller/v1/moneyTransToPlatformMoney")
@Api(value = "商家-余额购买点币")
public class SellerMoneyTransToPlatformMoneyController {
    @Autowired
    MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateService;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    MoneyTransToPlatformMoneyStreamLogService moneyTransToPlatformMoneyStreamLogService;
    @Autowired
    MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
    @Autowired
    private MoneyTransToPlatformMoneyCreateService moneyTransToPlatformMoneyCreateService;

    @RequestMapping("/save")
    @ApiOperation(value = "执行余额到点币")
    public Result save(MoneyTransToPlatformMoneyParam moneyTransToPlatformMoneyParam) throws Exception {
        UserBalance userBalance=userBalanceService.findByUserId(moneyTransToPlatformMoneyParam.getUserId());
        if(userBalance==null){

        }
        MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateService.findBy(1l);

        BaseProccess proccess=new MoneyTransToPlatformMoneyCreateProccess(MoneyTransToPlatformMoneyWrapperFactory.newInstance(userBalance,moneyTransToPlatformMoneyRate,moneyTransToPlatformMoneyParam),
                moneyTransToPlatformMoneyCreateService);
        proccess.exec();
        if(proccess.getException().size()==0){
            return Result.successResult();
        }
        return Result.failureResult(proccess.getException().get(0));
    }
    @RequestMapping("/userDepositStream")
    public Result userDepositStream(Integer userId){
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
      //  return Result.successResult(moneyTransToPlatformMoneyService.userDepositStream(userId));
        return Result.successResult();

    }
    @RequestMapping("/countUserDepositStream")
    public Result countUserDepositStream(Integer userId) {
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
      //  return Result.successResult(moneyTransToPlatformMoneyService.countUserDepositStream(userId));
        return Result.successResult();
    }
}
