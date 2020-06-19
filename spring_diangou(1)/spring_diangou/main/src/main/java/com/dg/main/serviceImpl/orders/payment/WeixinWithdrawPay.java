package com.dg.main.serviceImpl.orders.payment;

public class WeixinWithdrawPay implements IPay {
    public interface WeixinCallback{
        void isWeinxinCallbackSuccess(Object o);
        void isWeinxinCallbackFailure(Object o);
    }
    private WeixinCallback weixinCallback;

    public void setWeixinCallback(WeixinCallback weixinCallback) {
        this.weixinCallback = weixinCallback;
    }

    @Override
    public void pay() {

    }
}
