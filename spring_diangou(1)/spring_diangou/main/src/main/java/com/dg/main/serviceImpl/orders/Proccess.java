package com.dg.main.serviceImpl.orders;

import com.alipay.api.AlipayApiException;

import java.io.IOException;

public interface Proccess {
    boolean validate();
    void action() throws Exception;
    void notifyChange();
}
