package com.dg.main.serviceImpl.orders.update_status;

import com.alipay.api.AlipayApiException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.DepositException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.ThirdDepositPlatformMoneyPayedSuccessService;
import com.dg.main.serviceImpl.orders.wrapper.DepositPlatformMoneyWrapper;
import com.dg.main.util.Tuple2;

import java.io.IOException;

public class ThirdDepositPlatformMoneyPayedSuccessProccess extends BaseProccess {
    private final DepositPlatformMoneyWrapper depositPlatformMoneyWrapper;
    private ThirdDepositPlatformMoneyPayedSuccessService thirdDepositPlatformMoneyPayedSuccessService;

    public ThirdDepositPlatformMoneyPayedSuccessProccess(DepositPlatformMoneyWrapper depositPlatformMoneyWrapper, ThirdDepositPlatformMoneyPayedSuccessService thirdDepositPlatformMoneyPayedSuccessService) {
        this.depositPlatformMoneyWrapper = depositPlatformMoneyWrapper;
        this.thirdDepositPlatformMoneyPayedSuccessService = thirdDepositPlatformMoneyPayedSuccessService;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
       // thirdDepositPlatformMoneyPayedSuccessService.action(depositPlatformMoneyWrapper);
        if(! thirdDepositPlatformMoneyPayedSuccessService.action(depositPlatformMoneyWrapper)){
            getException().add(new DepositException(ExceptionCode.Failure.DEPOSIT_ERROR));
        }

    }

    @Override
    public void notifyChange() {

    }
}
