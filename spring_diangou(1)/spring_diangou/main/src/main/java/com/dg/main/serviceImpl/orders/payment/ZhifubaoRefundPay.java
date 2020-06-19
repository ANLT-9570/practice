package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;



public class ZhifubaoRefundPay implements IPay {
    public interface ZhifubaoRefundCallback{
        void zhifubaoRefundSuccess();
        void zhifubaoRefundFailure();
    }
    private ZhifubaoRefundCallback zhifubaoRefundCallback;

    public void setZhifubaoRefundCallback(ZhifubaoRefundCallback zhifubaoRefundCallback) {
        this.zhifubaoRefundCallback = zhifubaoRefundCallback;
    }

    private AlipayTradeRefundModel model=null;
   // private HttpServletResponse response;

    public void setModel(AlipayTradeRefundModel model) {
        this.model = model;
    }

    //public void setResponse(HttpServletResponse response) {
       // this.response = response;
   // }

    @Override
    public void pay() {
        AlipayClient alipayClient = new DefaultAlipayClient(ZhifubaoPay.Config.gatewayUrl,ZhifubaoPay.Config.appid,
                ZhifubaoPay.Config.appPrivateKey,"json",ZhifubaoPay.Config.charset,ZhifubaoPay.Config.alipayPublicKey,"RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);

        AlipayTradeRefundResponse alipayResponse = null;
        try {
            alipayResponse = alipayClient.execute(request);
            System.out.println(alipayResponse.getBody());
            if(alipayResponse.isSuccess()){
                System.out.println("调用成功");
                if(zhifubaoRefundCallback!=null){
                    zhifubaoRefundCallback.zhifubaoRefundSuccess();
                }

            } else {
                System.out.println("调用失败");
                if(zhifubaoRefundCallback!=null){
                    zhifubaoRefundCallback.zhifubaoRefundFailure();
                }
            }
        } catch (Exception e) {
            if(zhifubaoRefundCallback!=null){
                zhifubaoRefundCallback.zhifubaoRefundFailure();
            }
            e.printStackTrace();
        }

    }
}
