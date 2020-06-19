package com.dg.main.controller.app.common.third_pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class TestWeixinController {
    @Autowired
    AlipayClient alipayClient;
    @PostMapping("/gotoPayPage")
    public void gotoPayPage(HttpServletResponse response) throws AlipayApiException, IOException {
        // 订单模型
        String productCode="QUICK_WAP_WAY";

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(UUID.randomUUID().toString());
        model.setSubject("支付测试");
        model.setTotalAmount("0.01");
        model.setBody("支付测试，共0.01元");
        model.setTimeoutExpress("5m");
        model.setProductCode(productCode);

        AlipayTradeWapPayRequest wapPayRequest =new AlipayTradeWapPayRequest();
        wapPayRequest.setReturnUrl("http://yxep7y.natappfree.cc/alipay/wap/returnUrl");
        wapPayRequest.setNotifyUrl(ZhifubaoPay.Config.notifyUrl);
        wapPayRequest.setBizModel(model);

        // 调用SDK生成表单, 并直接将完整的表单html输出到页面
        String form = alipayClient.pageExecute(wapPayRequest).getBody();
        System.out.println(form);
        response.setContentType("text/html;charset=" + ZhifubaoPay.Config.charset);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
