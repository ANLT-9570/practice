package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayFundTransUniTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.dg.main.vo.TransToVo;
import com.google.gson.Gson;


public class ZhifubaoWithdrawPay implements IPay{
    public interface  ZhifubaoWithdrawCallback{
        void isSuccess(Object object);
        void isFailure(Object object);
    }
    private TransToVo transTo=null;

    public void setTransTo(TransToVo transTo) {
        this.transTo = transTo;
    }

    private ZhifubaoWithdrawCallback callback;

    public void setCallback(ZhifubaoWithdrawCallback callback) {
        this.callback = callback;
    }

    @Override
    public void pay() {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");
        certAlipayRequest.setAppId("2019111169070317");
        certAlipayRequest.setPrivateKey(ZhifubaoPay.Config.newPrivateKey);
        certAlipayRequest.setFormat("json");
        certAlipayRequest.setCharset("GBK");
        certAlipayRequest.setSignType("RSA2");
        // certAlipayRequest.setCertPath(ZhifubaoPay.Config.certPath);
        //certAlipayRequest.setAlipayPublicCertPath(ZhifubaoPay.Config.alipayPublicCertPath);
        //certAlipayRequest.setRootCertPath(ZhifubaoPay.Config.rootCertPath);
        certAlipayRequest.setCertPath("C:/ceshi/gxsl/base/appCertPublicKey_2019111169070317.crt");
        certAlipayRequest.setAlipayPublicCertPath("C:/ceshi/gxsl/base/alipayCertPublicKey_RSA2.crt");
        certAlipayRequest.setRootCertPath("C:/ceshi/gxsl/base/alipayRootCert.crt");

        try{
            DefaultAlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
            AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
            //  TransTo transTo=new TransTo();
            Gson g=new Gson();
            //   transTo.setOut_biz_no(System.currentTimeMillis()+"");
//            transTo.setPayee_account("abc@sina.com");
//            transTo.setPayee_real_name("张三");
//            transTo.setPayer_show_name("上海交通卡退款");

            request.setBizContent(g.toJson(transTo));
//            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            AlipayFundTransUniTransferResponse response = alipayClient.certificateExecute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
                if(callback!=null){
                    callback.isSuccess(null);
                }
            } else {
                System.out.println(response.getCode());
                System.out.println("调用失败");
                if(callback!=null){
                    callback.isFailure(null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
