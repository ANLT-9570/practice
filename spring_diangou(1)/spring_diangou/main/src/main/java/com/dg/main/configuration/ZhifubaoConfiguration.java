package com.dg.main.configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ZhifubaoConfiguration {
//    @Bean
//    public AlipayTradeService alipayTradeService() {
//        return new AlipayTradeServiceImpl.ClientBuilder()
//                .setGatewayUrl(properties.getGatewayUrl())
//                .setAppid(properties.getAppid())
//                .setPrivateKey(properties.getAppPrivateKey())
//                .setAlipayPublicKey(properties.getAlipayPublicKey())
//                .setSignType(properties.getSignType())
//                .build();
//    }

    /**
     * alipay-sdk-java
     * @return
     */
    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(ZhifubaoPay.Config.gatewayUrl,
                ZhifubaoPay.Config.appid,
                ZhifubaoPay.Config.appPrivateKey,
                ZhifubaoPay.Config.formate,
                ZhifubaoPay.Config.charset,
                ZhifubaoPay.Config.alipayPublicKey,
                ZhifubaoPay.Config.signType);
    }
}
