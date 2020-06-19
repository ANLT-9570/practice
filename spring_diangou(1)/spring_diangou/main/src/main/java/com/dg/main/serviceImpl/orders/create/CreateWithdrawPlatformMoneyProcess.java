package com.dg.main.serviceImpl.orders.create;

import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.SystemErrorException;
import com.dg.main.exception.orders.ZhifubaoWithdrawFailureException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoWithdrawPay;
import com.dg.main.serviceImpl.orders.service.ManualWithdrawPlatformMoneyProccessService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;
import com.dg.main.vo.TransToVo;
import com.google.gson.Gson;

import java.math.BigDecimal;

public class CreateWithdrawPlatformMoneyProcess   extends BaseProccess   implements ZhifubaoWithdrawPay.ZhifubaoWithdrawCallback{
    private WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper;
    private   ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService;
    private IPay iPay;

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    public CreateWithdrawPlatformMoneyProcess(WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper, ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService) {
        this.withdrawPlatformMoneyWrapper = withdrawPlatformMoneyWrapper;
        this.manualWithdrawPlatformMoneyProccessService = manualWithdrawPlatformMoneyProccessService;
    }

    public WithdrawPlatformMoneyWrapper getWithdrawPlatformMoneyWrapper() {
        return withdrawPlatformMoneyWrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() {
        UserWithdrawPlatformMoney userWithdrawMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        UserThirdAccount userThirdAccount=withdrawPlatformMoneyWrapper.getUserThirdAccount();
        manualWithdrawPlatformMoneyProccessService.save(withdrawPlatformMoneyWrapper);
        if(userWithdrawMoney.getDirection()==1){
            if(iPay!=null){
                if (iPay instanceof ZhifubaoWithdrawPay){
                    TransToVo transTo=new TransToVo();
                    TransToVo.PayeeInfo payeeInfo=new TransToVo.PayeeInfo(userThirdAccount.getZhifubaoPayeeAccount(),userThirdAccount.getZhifubaoPayeeRealName());
                    transTo.setOut_biz_no(userWithdrawMoney.getWithdrawStreamCode());
                    transTo.setPayee_info(payeeInfo);
                    BigDecimal money=new BigDecimal(userWithdrawMoney.getMoney());
                    System.out.println(money.divide(new BigDecimal(100)));
                    transTo.setTrans_amount(money.divide(new BigDecimal(100))+"");
                    ((ZhifubaoWithdrawPay) iPay).setTransTo(transTo);
                    ((ZhifubaoWithdrawPay) iPay).setCallback(this);
                    try {
                        iPay.pay();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(userWithdrawMoney.getDirection()==2){

        }
        // manualWithdrawPlatformMoneyProccessService.actionV1(withdrawPlatformMoneyWrapper);
    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void isSuccess(Object object) {
        if(!manualWithdrawPlatformMoneyProccessService.actionV1(withdrawPlatformMoneyWrapper)){
            UserWithdrawPlatformMoney userWithdrawMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
            userWithdrawMoney.setIsAudit(1);
            userWithdrawMoney.setIsSuccess(2);
            userWithdrawMoney.setAuditFailed("系统错误");
            UserWithdrawPlatformMoneyLog userWithdrawMoneyStreamLog=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
            userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
            userWithdrawMoneyStreamLog.setPlatformMoney(userWithdrawMoney.getPlatformMoney());
            userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
            userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
            manualWithdrawPlatformMoneyProccessService. saveFailureLog(userWithdrawMoneyStreamLog,userWithdrawMoney);
            getException().add(new SystemErrorException(ExceptionCode.Failure.SYSTEM_ERROR));
        }
    }

    @Override
    public void isFailure(Object object) {
        Gson gson=new Gson();
        UserWithdrawPlatformMoney userWithdrawMoney=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoney();
        userWithdrawMoney.setIsAudit(1);
        userWithdrawMoney.setIsSuccess(2);
        userWithdrawMoney.setAuditFailed("账号与用户名不一致");
        UserWithdrawPlatformMoneyLog userWithdrawMoneyStreamLog=withdrawPlatformMoneyWrapper.getUserWithdrawPlatformMoneyLog();
        userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        userWithdrawMoneyStreamLog.setPlatformMoney(userWithdrawMoney.getPlatformMoney());
        userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
        userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        manualWithdrawPlatformMoneyProccessService. saveFailureLog(userWithdrawMoneyStreamLog,userWithdrawMoney);
        getException().add(new ZhifubaoWithdrawFailureException(ExceptionCode.Failure.DEPOSIT_USER_ERROR));
    }
}
