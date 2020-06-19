package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class WeixinRefundPay  extends WXPay implements IPay {
    public interface WeixinRefundPayCallback{
        void weixinRefundPaySuccess();
        void weixinRefundPayFailure();
    }
     private Map<String,String> paramMap=null;

    private WeixinRefundPayCallback weixinRefundPayCallback;

    public void setWeixinRefundPayCallback(WeixinRefundPayCallback weixinRefundPayCallback) {
        this.weixinRefundPayCallback = weixinRefundPayCallback;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public WeixinRefundPay(WXPayConfig config) {
        super(config);
    }

    @Override
    public void pay()  {
    	try{
            //   String sign = WXPayUtil.generateSignature(paramMap, WeixinPay.Config.key);
             //  paramMap.put("sign",sign);
    		 System.out.println(paramMap);
               Map<String, String> resultMap = this.refund(paramMap);
               System.out.println(resultMap);
               if(resultMap.get("return_code").equals("SUCCESS")){
                   System.out.println("-------");
                   if(weixinRefundPayCallback!=null){
                       weixinRefundPayCallback.weixinRefundPaySuccess();
                   }
               }else{
                   System.out.println("-----------111--------");
                   if(weixinRefundPayCallback!=null){
                       weixinRefundPayCallback.weixinRefundPayFailure();
                   }
               }
           }catch (Exception e){
               e.printStackTrace();
           }

    }
}
