package com.dg.main.configuration;

import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class WeixinConfiguration {
    @Autowired
    private MyWXPayConfig wxPayConfig;
    @Bean
    public WXPay wxPay() {
       // WeixinConfiguration weixinConfiguration=new WeixinConfiguration();
        return new WeixinPay(wxPayConfig);
    }




//    @Bean
//    public WeixinPay wxPayClient() {
//        return new WeixinPay(this, WXPayConstants.SignType.MD5, WeixinPay.Config.useSandbox);
//    }
//    @Bean
//    public WeixinPay wxPayClient() {
//     //   WeixinConfiguration weixinConfiguration=new WeixinConfiguration();
//        return new WeixinPay(wxPayConfig);
//    }

}
