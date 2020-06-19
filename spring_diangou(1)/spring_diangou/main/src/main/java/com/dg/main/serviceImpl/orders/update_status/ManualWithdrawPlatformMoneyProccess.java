package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ManualWithdrawPlatformMoneyProccessService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;

import java.io.IOException;

public class ManualWithdrawPlatformMoneyProccess extends BaseProccess {
    private ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService;
    private final WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper;

    public ManualWithdrawPlatformMoneyProccess(ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService, WithdrawPlatformMoneyWrapper withdrawPlatformMoneyWrapper) {
        this.manualWithdrawPlatformMoneyProccessService = manualWithdrawPlatformMoneyProccessService;
        this.withdrawPlatformMoneyWrapper = withdrawPlatformMoneyWrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
      //  manualWithdrawPlatformMoneyProccessService.action(withdrawPlatformMoneyWrapper);
        manualWithdrawPlatformMoneyProccessService.actionv2(withdrawPlatformMoneyWrapper);
    }

    @Override
    public void notifyChange() {

    }
}
