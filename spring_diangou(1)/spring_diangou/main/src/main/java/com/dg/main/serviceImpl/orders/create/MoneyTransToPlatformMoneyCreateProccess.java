package com.dg.main.serviceImpl.orders.create;

import com.dg.main.Entity.orders.MoneyTransToPlatformMoney;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.PlatformMoneyNotEnoughException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyWrapper;

import java.util.function.Function;

public class MoneyTransToPlatformMoneyCreateProccess extends BaseProccess {
    private final MoneyTransToPlatformMoneyWrapper moneyTransToPlatformMoneyWrapper;
//    private UserBalanceService userBalanceService;
//    private MoneyTransToPlatformMoneyStreamLogService moneyTransToPlatformMoneyStreamLogService;
//    private MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
    private MoneyTransToPlatformMoneyCreateService moneyTransToPlatformMoneyStreamLogService;
    final private Function<UserBalance,Function<MoneyTransToPlatformMoney,Boolean>> check=userBalance ->
            moneyTransToPlatformMoney -> {
                if(userBalance.getMoney()<moneyTransToPlatformMoney.getMoney()){
                    return false;
                }
                return true;
            };

    public MoneyTransToPlatformMoneyCreateProccess(MoneyTransToPlatformMoneyWrapper moneyTransToPlatformMoneyWrapper, MoneyTransToPlatformMoneyCreateService moneyTransToPlatformMoneyStreamLogService) {
        this.moneyTransToPlatformMoneyWrapper = moneyTransToPlatformMoneyWrapper;
        this.moneyTransToPlatformMoneyStreamLogService = moneyTransToPlatformMoneyStreamLogService;
    }

    @Override
    public boolean validate() {
        if(!check.apply(moneyTransToPlatformMoneyWrapper.getUserBalance()).apply(moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoney())){
            exceptions.add(new PlatformMoneyNotEnoughException(ExceptionCode.Failure.MONEY_NOT_ENOUGH));
            return false;
        }
        return true;
    }

    @Override
    public void action() {
        moneyTransToPlatformMoneyStreamLogService.save(moneyTransToPlatformMoneyWrapper);
//        UserBalance oldObj=moneyTransToPlatformMoneyWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        newObj.setMoney(newObj.getMoney()-moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoney().getMoney());
//        newObj.setPlatformMoney(moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoney().getPlatformMoney()+newObj.getPlatformMoney());
//
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
//        userBalanceService.update(newObj);
//        UserMoneyStreamLog userMoneyStreamLog=moneyTransToPlatformMoneyWrapper.getUserMoneyStreamLog();
//        userMoneyStreamLog.setCreateTime(new Date());
//        userMoneyStreamLog.setUserId(oldObj.getUserId());
//        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
//        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
//        userMoneyStreamLogService.save(userMoneyStreamLog);
//        moneyTransToPlatformMoneyStreamLogService.save(moneyTransToPlatformMoneyWrapper.getMoneyTransToPlatformMoneyStreamLog());

    }

    @Override
    public void notifyChange() {

    }
}
