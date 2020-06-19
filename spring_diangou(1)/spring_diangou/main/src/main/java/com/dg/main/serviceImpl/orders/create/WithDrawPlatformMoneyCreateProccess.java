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
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;

import java.util.function.Function;

public class WithDrawPlatformMoneyCreateProccess extends BaseProccess implements ZhifubaoWithdrawPay.ZhifubaoWithdrawCallback
, WeixinWithdrawPay.WeixinCallback {
    private IPay iPay;
    private final WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper;
  //  private TransTo transTo=new TransTo();
//    private UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
//    private UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
     private WithDrawPlatformMoneyCreateService withDrawPlatformMoneyCreateService;

    public WithDrawPlatformMoneyCreateProccess(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper, WithDrawPlatformMoneyCreateService withDrawPlatformMoneyCreateService) {
        this.withdrawPlatformMoneyWrapper = withdrawPlatformMoneyWrapper;
        this.withDrawPlatformMoneyCreateService = withDrawPlatformMoneyCreateService;
    }

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    public static final Function<UserBalance,Function<UserWithdrawPlatformMoney,Boolean>> checkPlatformMoney= userBalance -> userWithdrawPlatformMoney -> {
      if(userBalance.getPlatformMoney()<userWithdrawPlatformMoney.getPlatformMoney()){
          return false;
      }
      return true;
    };
    @Override
    public boolean validate() {
//        if(!checkPlatformMoney.apply(withdrawPlatformMoneyWrapper.getUserBalance()).apply(withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney())){
//            exceptions.add(new PlatformMoneyNotEnoughException(ExceptionCode.Failure.PLATFOMR_MONEY_NOT_ENOUGH));
//            return false;
//        }
        return true;
    }

    @Override
    public void action() {

        try{
            if(iPay!=null){
                if(iPay instanceof ZhifubaoWithdrawPay){
                 //   withDrawPlatformMoneyCreateService.save(withdrawPlatformMoneyWrapper);
                    UserWithdrawPlatformMoney userWithdrawPlatformMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
//                    transTo.setOut_biz_no(userWithdrawPlatformMoney.getWithdrawStreamCode());
//                    transTo.setAmount(userWithdrawPlatformMoney.getMoney()+"");//todo
//                    transTo.setRemark(userWithdrawPlatformMoney.getUserId()+"");
//                    ((ZhifubaoWithdrawPay) iPay).setCallback(this);
//                    ((ZhifubaoWithdrawPay) iPay).setTransTo(transTo);
//                    iPay.pay();
                }else if(iPay instanceof WeixinWithdrawPay){

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void isSuccess(Object object) {

    }

    @Override
    public void isFailure(Object object) {
        getException().add(new ZhifubaoWithdrawFailureException(ExceptionCode.Failure.ZHIFUBAO_WITHDRAW_FAILURE));
    }

    @Override
    public void isWeinxinCallbackSuccess(Object o) {

    }

    @Override
    public void isWeinxinCallbackFailure(Object o) {
        getException().add(new WeixinWithdrawFailureException(ExceptionCode.Failure.WEIXIN_WITHDRAW_FAILURE));
    }
}
