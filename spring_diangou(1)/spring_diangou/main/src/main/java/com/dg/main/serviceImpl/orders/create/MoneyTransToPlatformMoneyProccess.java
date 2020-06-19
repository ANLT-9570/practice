package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.AlipayApiException;
import com.dg.main.exception.BaseException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.SystemErrorException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.MoneyTransToPlatformMoneyServiceV1;
import com.dg.main.serviceImpl.orders.wrapper.MoneyTransToPlatformMoneyV1Wrapper;

import java.io.IOException;

public class MoneyTransToPlatformMoneyProccess extends BaseProccess {
    private MoneyTransToPlatformMoneyServiceV1 moneyTransToPlatformMoneyServiceV1;
    private final MoneyTransToPlatformMoneyV1Wrapper moneyTransToPlatformMoneyV1Wrapper;

    public MoneyTransToPlatformMoneyProccess(MoneyTransToPlatformMoneyServiceV1 moneyTransToPlatformMoneyServiceV1, MoneyTransToPlatformMoneyV1Wrapper moneyTransToPlatformMoneyV1Wrapper) {
        this.moneyTransToPlatformMoneyServiceV1 = moneyTransToPlatformMoneyServiceV1;
        this.moneyTransToPlatformMoneyV1Wrapper = moneyTransToPlatformMoneyV1Wrapper;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action()  {
        if(!moneyTransToPlatformMoneyServiceV1.action(moneyTransToPlatformMoneyV1Wrapper)){
                getException().add(new SystemErrorException(ExceptionCode.Failure.SYSTEM_ERROR));

        }
    }

    @Override
    public void notifyChange() {

    }
}
