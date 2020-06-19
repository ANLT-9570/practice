package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.SystemErrorException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ManualCancelWithdrawMoneyService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;

import java.io.IOException;

public class ManualCancelWithdrawMoneyProccess extends BaseProccess {
    private ManualCancelWithdrawMoneyService manualCancelWithdrawMoneyService;
    private WithdrawMoneyWrapper withdrawMoneyWrapper;

    public ManualCancelWithdrawMoneyProccess(ManualCancelWithdrawMoneyService manualCancelWithdrawMoneyService, WithdrawMoneyWrapper withdrawMoneyWrapper) {
        this.manualCancelWithdrawMoneyService = manualCancelWithdrawMoneyService;
        this.withdrawMoneyWrapper = withdrawMoneyWrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
            if(!manualCancelWithdrawMoneyService.action(withdrawMoneyWrapper)){
                getException().add(new SystemErrorException(ExceptionCode.Failure.SYSTEM_ERROR));
            }
    }

    @Override
    public void notifyChange() {

    }
}
