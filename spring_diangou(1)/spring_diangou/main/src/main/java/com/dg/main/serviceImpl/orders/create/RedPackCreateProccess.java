package com.dg.main.serviceImpl.orders.create;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.MoneyNotEnoughException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.RedPackCreateService;
import com.dg.main.serviceImpl.orders.wrapper.RedPackWrapper;

import java.util.function.Function;

public class RedPackCreateProccess extends BaseProccess {
    private final RedPackWrapper redPackWrapper;
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    private RedPackService redPackService;
    private RedPackCreateService redPackCreateService;
    private final Function<UserBalance,Function<RedPack,Boolean>> checkPlatformMoney=userBalance ->
            redPack -> {
                if(userBalance.getPlatformMoney()<redPack.getPlatformMoney()){
                    return false;
                }
                return true;
            };

    public RedPackCreateProccess(RedPackWrapper redPackWrapper, RedPackCreateService redPackCreateService) {
        this.redPackWrapper = redPackWrapper;
        this.redPackCreateService = redPackCreateService;
    }

    @Override
    public boolean validate() {
        if(!checkPlatformMoney.apply(redPackWrapper.getUserBalance()).apply(redPackWrapper.getRedPack())){
            exceptions.add(new MoneyNotEnoughException(ExceptionCode.Failure.MONEY_NOT_ENOUGH));
            return false;
        }
        return true;
    }

    @Override
    public void action() {
        redPackCreateService.save(redPackWrapper);
//        UserBalance _old=redPackWrapper.getUserBalance();
//        UserBalance _new=redPackWrapper.getUserBalance();
//        RedPack redPack=redPackWrapper.getRedPack();
//        _new.setPlatformMoney(_old.getPlatformMoney()-redPack.getPlatformMoney());
//        _new.setModifyTime(new Date());
//        _new.setOperationTimes(_old.getOperationTimes()+1);
//
//
//    //    userBalanceService.save(redPackWrapper.getUserBalance());
//      //  userMoneyStreamLogService.save(redPackWrapper.getUserMoneyStreamLog());
//        redPackService.save(redPack);
//        userBalanceService.update(_new);
//        userMoneyStreamLogService.saveUserBalances(_old,_new);
    }

    @Override
    public void notifyChange() {

    }
}
