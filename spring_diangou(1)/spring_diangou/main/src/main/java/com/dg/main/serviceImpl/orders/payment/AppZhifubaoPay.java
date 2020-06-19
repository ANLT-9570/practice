package com.dg.main.serviceImpl.orders.payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.dg.main.dto.ThirdPayResultDto;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AppZhifubaoPay implements IPay {
    public interface callback{
        public void success();
        public void failure();
    }
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AlipayTradeAppPayModel model;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setModel(AlipayTradeAppPayModel model) {
        this.model = model;
    }

    @Override
    public void pay()  {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",ZhifubaoPay.Config.appid,
                ZhifubaoPay.Config.appPrivateKey,"json",ZhifubaoPay.Config.charset,ZhifubaoPay.Config.alipayPublicKey,"RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl( ZhifubaoPay.Config.notifyUrl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(Result.successResult(response.getBody()).toString());//就是orderString 可以直接给客户端请求，无需再做处理。
            PrintWriter write=this.response.getWriter();
            this.response.setContentType("text/plain; charset=UTF-8");
            ThirdPayResultDto thirdPayResultDto=new ThirdPayResultDto();
            thirdPayResultDto.setZhifubao(response.getBody());

            ResponseUtils.send(this.response, Result.successResult(thirdPayResultDto));
//            write.write(Result.successResult(response.getBody()).toString());
//            write.flush();
//            write.close();
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
