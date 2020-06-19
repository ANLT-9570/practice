package com.dg.main.serviceImpl.orders.payment;

import com.dg.main.dto.ThirdPayResultDto;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;
import com.dg.main.util.WebUtils;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AppWeinxinPay  extends WXPay implements IPay {
    private Map<String,String> paramMap=null;
    private HttpServletResponse response=null;

    public AppWeinxinPay(WXPayConfig config) {
        super(config);
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    @Override
    public void pay() throws Exception {
        try{
            Map<String, String> result = new HashMap<>();
            String sign = WXPayUtil.generateSignature(paramMap, WeixinPay.Config.key);

            paramMap.put("sign",sign);
            String xml = WXPayUtil.mapToXml(paramMap);//将所有参数(map)转xml格式

            String re = WebUtils.postData(WeixinPay.Config.URL, xml);//发送post请求"统一下单接口"
            result=WXPayUtil.xmlToMap(re);
            System.out.println(result);
            Map<String, String> wxPayMap = new HashMap<String, String>();
            ThirdPayResultDto thirdPayResultDto=new ThirdPayResultDto();
            wxPayMap.put("appId", WeixinPay.Config.appID);
            thirdPayResultDto.setAppId(WeixinPay.Config.appID);
            wxPayMap.put("partnerid",WeixinPay.Config.mchID);
            thirdPayResultDto.setPartnerid(WeixinPay.Config.mchID);
            wxPayMap.put("timestamp", String.valueOf((int)(System.currentTimeMillis() / 1000)));
            thirdPayResultDto.setTimestamp(String.valueOf((int)(System.currentTimeMillis() / 1000)));
            wxPayMap.put("nonce_str", WXPayUtil.generateNonceStr());
            thirdPayResultDto.setNonceStr(WXPayUtil.generateNonceStr());
            wxPayMap.put("package", "Sign=WXPay");
            thirdPayResultDto.setPackages("Sign=WXPay");
            wxPayMap.put("signType", "MD5");
            thirdPayResultDto.setSignType("MD5");
            String sign2 = WXPayUtil.generateSignature(wxPayMap, WeixinPay.Config.key);
            System.out.println(wxPayMap);
            result.put("prepay_id", result.get("prepay_id"));
            thirdPayResultDto.setPrepayId(result.get("prepay_id"));
            result.put("sign", sign2);
            thirdPayResultDto.setSign(sign2);
            result.putAll(wxPayMap);
            this.response.setContentType("text/plain; charset=UTF-8");
            ResponseUtils.send(this.response, Result.successResult(thirdPayResultDto));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
