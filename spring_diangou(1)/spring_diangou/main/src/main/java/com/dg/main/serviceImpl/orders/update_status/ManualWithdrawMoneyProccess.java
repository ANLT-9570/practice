package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ManualWithdrawMoneyProccessService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawMoneyWrapper;

import java.io.IOException;

public class ManualWithdrawMoneyProccess extends BaseProccess {
    private final WithdrawMoneyWrapper withdrawMoneyWrapper;
    private ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService;

    public ManualWithdrawMoneyProccess(WithdrawMoneyWrapper withdrawMoneyWrapper, ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService) {
        this.withdrawMoneyWrapper = withdrawMoneyWrapper;
        this.manualWithdrawMoneyProccessService = manualWithdrawMoneyProccessService;
    }

    @Override
    public boolean validate() {

        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
       // manualWithdrawMoneyProccessService.actionv1(withdrawMoneyWrapper);
        manualWithdrawMoneyProccessService.actionv2(withdrawMoneyWrapper);
    }

    @Override
    public void notifyChange() {

    }
}
