package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.SystemErrorException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ManualCancelWithdrawPlatformMoneyService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;

import java.io.IOException;

public class ManualCancelWithdrawPlatformMoneyProccess extends BaseProccess {
    private ManualCancelWithdrawPlatformMoneyService manualCancelWithdrawPlatformMoneyService;
    private final WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper;

    public ManualCancelWithdrawPlatformMoneyProccess(ManualCancelWithdrawPlatformMoneyService manualCancelWithdrawPlatformMoneyService, WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper) {
        this.manualCancelWithdrawPlatformMoneyService = manualCancelWithdrawPlatformMoneyService;
        this.withdrawPlatformMoneyWrapper = withdrawPlatformMoneyWrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
        if(!manualCancelWithdrawPlatformMoneyService.action(withdrawPlatformMoneyWrapper)){
            getException().add(new SystemErrorException(ExceptionCode.Failure.SYSTEM_ERROR));
        }
    }

    @Override
    public void notifyChange() {

    }
}
