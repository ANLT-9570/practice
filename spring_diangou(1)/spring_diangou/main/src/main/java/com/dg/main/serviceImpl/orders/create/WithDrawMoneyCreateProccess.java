package com.dg.main.serviceImpl.orders.create;

import com.dg.main.Entity.orders.*;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.WeixinWithdrawFailureException;
import com.dg.main.exception.orders.ZhifubaoWithdrawFailureException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.WeixinWithdrawPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoWithdrawPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;

import java.util.function.Function;


public class WithDrawMoneyCreateProccess extends BaseProccess implements ZhifubaoWithdrawPay.ZhifubaoWithdrawCallback, WeixinWithdrawPay.WeixinCallback {
    private IPay iPay;
    private WithdrawMoneyWrapper withdrawMoneyWrapper;
//    private UserWithdrawMoneyService userWithdrawMoneyService;
//    private UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
    private WithDrawMoneyCreateService withDrawMoneyCreateService;

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    public WithDrawMoneyCreateProccess(WithdrawMoneyWrapper withdrawMoneyWrapper, WithDrawMoneyCreateService withDrawMoneyCreateService) {
        this.withdrawMoneyWrapper = withdrawMoneyWrapper;
        this.withDrawMoneyCreateService = withDrawMoneyCreateService;
    }

    public static final Function<UserBalance,Function<UserWithdrawMoney,Boolean>> checkMoney= userBalance -> userWithdrawMoney -> {
        if(userBalance.getMoney()<userWithdrawMoney.getMoney()){
            return false;
        }
        return true;
    };
    @Override
    public boolean validate() {
//        if(!checkMoney.apply(withdrawMoneyWrapper.getUserBalance()).apply(withdrawMoneyWrapper.getUserWithdrawMoney())){
//            exceptions.add(new MoneyNotEnoughException(ExceptionCode.Failure.MONEY_NOT_ENOUGH));
//            return false;
//        }
        return true;
    }

    @Override
    public void action() {
        try{
            if(iPay!=null){
                if(iPay instanceof ZhifubaoWithdrawPay){
//                    UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
//                    transTo.setRemark("");
//                    transTo.setAmount(userWithdrawMoney.getMoney()+"");
//                    transTo.setOut_biz_no(userWithdrawMoney.getWithdrawStreamCode());
//                    ((ZhifubaoWithdrawPay) iPay).setCallback(this);
//                    ((ZhifubaoWithdrawPay) iPay).setTransTo(transTo);
//                    iPay.pay();
                }else if(iPay instanceof WeixinWithdrawPay){

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        if(iPay!=null){
//            try {
//                withDrawMoneyCreateService.save(withdrawMoneyWrapper);
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

//        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
//        UserBalance oldObj=withdrawMoneyWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
//        newObj.setModifyTime(new Date());
//        newObj.setMoney(oldObj.getMoney()-userWithdrawMoney.getMoney());
//        userBalanceService.update(newObj);
//
//        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
//        userMoneyStreamLog.setUserId(oldObj.getUserId());
//        userMoneyStreamLog.setCreateTime(new Date());
//        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
//        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
//        userMoneyStreamLogService.save(userMoneyStreamLog);
//        userWithdrawMoneyService.save(userWithdrawMoney);
//        userWithdrawMoneyStreamLogService.save(withdrawMoneyWrapper.getWithdrawMoneyStreamLog());
    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void isWeinxinCallbackSuccess(Object o) {

    }

    @Override
    public void isWeinxinCallbackFailure(Object o) {
        getException().add(new WeixinWithdrawFailureException(ExceptionCode.Failure.WEIXIN_WITHDRAW_FAILURE));
    }

    @Override
    public void isSuccess(Object object) {

    }

    @Override
    public void isFailure(Object object) {
        getException().add(new ZhifubaoWithdrawFailureException(ExceptionCode.Failure.ZHIFUBAO_WITHDRAW_FAILURE));
    }
}
