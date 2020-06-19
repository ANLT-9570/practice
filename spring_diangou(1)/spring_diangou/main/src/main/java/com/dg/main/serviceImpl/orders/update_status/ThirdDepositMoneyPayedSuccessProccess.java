package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ThirdDepositMoneyPayedSuccessService;
import com.dg.main.serviceImpl.orders.wrapper.DepositMoneyWrapper;

import java.io.IOException;

public class ThirdDepositMoneyPayedSuccessProccess  extends BaseProccess {
    private final DepositMoneyWrapper depositMoneyWrapper;
    private ThirdDepositMoneyPayedSuccessService thirdDepositMoneyPayedSuccessService;

    public ThirdDepositMoneyPayedSuccessProccess(DepositMoneyWrapper depositMoneyWrapper, ThirdDepositMoneyPayedSuccessService thirdDepositMoneyPayedSuccessService) {
        this.depositMoneyWrapper = depositMoneyWrapper;
        this.thirdDepositMoneyPayedSuccessService = thirdDepositMoneyPayedSuccessService;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
            thirdDepositMoneyPayedSuccessService.action(depositMoneyWrapper);
    }

    @Override
    public void notifyChange() {

    }
}
