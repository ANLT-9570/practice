package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayApiException;
import com.dg.main.exception.BaseException;
import com.dg.main.util.Tuple2;
import com.dg.main.util.Tuple3;

import java.io.IOException;

public interface IPay {
   // Tuple2<Boolean, BaseException> pay();
    void pay() throws Exception;
}
