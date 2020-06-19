package com.dg.main.serviceImpl.orders.create;

import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.SystemErrorException;
import com.dg.main.exception.orders.ZhifubaoWithdrawFailureException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoWithdrawPay;
import com.dg.main.serviceImpl.orders.service.ManualWithdrawMoneyProccessService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import com.dg.main.vo.TransToVo;
import com.google.gson.Gson;

import java.math.BigDecimal;

public class CreateWithdrawMoneyProcess  extends BaseProccess  implements ZhifubaoWithdrawPay.ZhifubaoWithdrawCallback {
    private final WithdrawMoneyWrapper withdrawMoneyWrapper;
    private ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService;
    private IPay iPay;

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    public CreateWithdrawMoneyProcess(WithdrawMoneyWrapper withdrawMoneyWrapper, ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService) {
        this.withdrawMoneyWrapper = withdrawMoneyWrapper;
        this.manualWithdrawMoneyProccessService = manualWithdrawMoneyProccessService;
    }

    public WithdrawMoneyWrapper getWithdrawMoneyWrapper() {
        return withdrawMoneyWrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action()  {

        //  manualWithdrawMoneyProccessService.actionv1(withdrawMoneyWrapper);
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
        UserThirdAccount userThirdAccount=withdrawMoneyWrapper.getUserThirdAccount();
        manualWithdrawMoneyProccessService.save(withdrawMoneyWrapper);
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
    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void isSuccess(Object object) {
        if(! manualWithdrawMoneyProccessService.actionv1(withdrawMoneyWrapper)){
            UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
            userWithdrawMoney.setIsAudit(1);
            userWithdrawMoney.setIsSuccess(2);
            userWithdrawMoney.setAuditFailed("系统错误");
            UserWithdrawMoneyStreamLog userWithdrawMoneyStreamLog=withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
            userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
            userWithdrawMoneyStreamLog.setMoney(userWithdrawMoney.getMoney());
            userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
            userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
            manualWithdrawMoneyProccessService. saveFailureLog(userWithdrawMoneyStreamLog,userWithdrawMoney);
            getException().add(new SystemErrorException(ExceptionCode.Failure.SYSTEM_ERROR));
        }

    }

    @Override
    public void isFailure(Object object) {
        Gson gson=new Gson();
        UserWithdrawMoney userWithdrawMoney=withdrawMoneyWrapper.getUserWithdrawMoney();
        userWithdrawMoney.setIsAudit(1);
        userWithdrawMoney.setIsSuccess(2);
        userWithdrawMoney.setAuditFailed("账号与用户名不一致");
        UserWithdrawMoneyStreamLog userWithdrawMoneyStreamLog=withdrawMoneyWrapper.getWithdrawMoneyStreamLog();
        userWithdrawMoneyStreamLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawMoney));
        userWithdrawMoneyStreamLog.setMoney(userWithdrawMoney.getMoney());
        userWithdrawMoneyStreamLog.setUserId(userWithdrawMoney.getUserId());
        userWithdrawMoneyStreamLog.setWithdrawStreamCode(userWithdrawMoney.getWithdrawStreamCode());
        manualWithdrawMoneyProccessService. saveFailureLog(userWithdrawMoneyStreamLog,userWithdrawMoney);
        getException().add(new ZhifubaoWithdrawFailureException(ExceptionCode.Failure.DEPOSIT_USER_ERROR));
    }
}
