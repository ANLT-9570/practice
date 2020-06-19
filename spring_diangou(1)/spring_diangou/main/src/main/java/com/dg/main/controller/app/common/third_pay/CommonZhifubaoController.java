package com.dg.main.controller.app.common.third_pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.dg.main.Entity.logs.CallbackFailureLog;
import com.dg.main.Entity.logs.ZhifubaoCallbackLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.logs.CallbackFailureLogService;
import com.dg.main.serviceImpl.logs.WeixinCallbackLogService;
import com.dg.main.serviceImpl.logs.ZhifubaoCallbackLogService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.factory.DepositMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.DepositPlatformMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.ThirdPayedSuccessFactory;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.update_status.ThirdDepositMoneyPayedSuccessProccess;
import com.dg.main.serviceImpl.orders.update_status.ThirdDepositPlatformMoneyPayedSuccessProccess;
import com.dg.main.serviceImpl.orders.update_status.ThirdPayedSuccessProccess;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/common/zhifubao")
@Api(tags = "通用-支付宝支付")
public class CommonZhifubaoController {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    OrdersService ordersService;

    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    WeixinCallbackLogService weixinCallbackLogService;
    @Autowired
    ZhifubaoCallbackLogService zhifubaoCallbackLogService;
    @Autowired
    private ThirdPayedSuccessService thirdPayedSuccessService;
    @Autowired
    UserDepositMoneyService userDepositMoneyService;
    @Autowired
    ThirdDepositMoneyPayedSuccessService thirdDepositMoneyPayedSuccessService;
    @Autowired
    ThirdDepositPlatformMoneyPayedSuccessService thirdDepositPlatformMoneyPayedSuccessService;
    @Autowired
    UserDepositPlatformMoneyService userDepositPlatformMoneyService;
    @Autowired
    CallbackFailureLogService callbackFailureLogService;
    @RequestMapping("/sandbox.test")
    public void sandbox(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest)throws Exception{
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                ZhifubaoPay.Config.appid, ZhifubaoPay.Config.appPrivateKey, "json", ZhifubaoPay.Config.charset,  ZhifubaoPay.Config.alipayPublicKey, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("fwe111lfjkfewjlew");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(ZhifubaoPay.Config.notifyUrl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //        String orderId=request.getParameter("orderId");
//        if(orderId==null){
//            return Result.failureResult();
//        }
//        Orders orders=ordersService.findByCode(orderId);
//        if(orders==null){
//
//        }
//        UserBalance buyer=userBalanceService.findByUserId(orders.getCustomerId());
//        UserBalance seller=userBalanceService.findByUserId(orders.getBusinessId());
//        if(buyer==null){
//
//        }
//        if(seller==null){
//
//        }
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
//                ZhifubaoPay.Config.appid, ZhifubaoPay.Config.appPrivateKey, "json", ZhifubaoPay.Config.charset,
//                ZhifubaoPay.Config.alipayPublicKey, "RSA2");
//        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
//        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
//        request.setBizModel(model);
//        model.setOutTradeNo(System.currentTimeMillis()+"");
//        model.setTotalAmount("88.88");
//        model.setSubject("Iphone6 16G");
//        AlipayTradePrecreateResponse response = alipayClient.execute(request);
//        System.out.print(response.getBody());
//        System.out.print(response.getQrCode());
 //       AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",ZhifubaoPay.Config.appid,
             //   ZhifubaoPay.Config.appPrivateKey,"json","GBK",ZhifubaoPay.Config.alipayPublicKey,"RSA2");
       // AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
     //   AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
     //   request.setNotifyUrl(ZhifubaoPay.Config.notifyUrl);
      //  request.setReturnUrl(ZhifubaoPay.Config.returnUrl);
//        request.setBizContent("{" +
//                "\"out_trade_no\":\"20150320010101001\"," +
//                "\"total_amount\":88.88\"," +
//
//                "\"subject\":\"Iphone6 16G\"," +
//                "\"timeout_express\":\"90m\"}"+"");
//        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//
//        model.setOutTradeNo(System.currentTimeMillis()+"");
//        model.setTotalAmount("88.88");
//        model.setSubject("Iphone6 16G");
//        model.setPassbackParams(URLEncoder.encode("1"));
//        request.setBizModel(model);
//        String form = alipayClient.pageExecute(request).getBody();
//        response.setContentType("text/html;charset=GBK");
//        response.getWriter().write(form);
//        response.getWriter().flush();
//        response.getWriter().close();
    }
    @RequestMapping("/testApp")
    @ResponseBody
    public Result testApp(){
//实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",ZhifubaoPay.Config.appid,
                ZhifubaoPay.Config.appPrivateKey,"json",ZhifubaoPay.Config.charset,ZhifubaoPay.Config.alipayPublicKey,"RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("241431254fw55f121e");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
      //  model.setPassbackParams("1");
        model.setPassbackParams(URLEncoder.encode("1"));
        request.setBizModel(model);
        request.setNotifyUrl( ZhifubaoPay.Config.notifyUrl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return Result.successResult(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return Result.successResult();
    }
    @RequestMapping("/transTo")
    @ResponseBody
    public String transTo() throws AlipayApiException {
//        AlipayClient alipayClient =new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",ZhifubaoPay.Config.appid,
//                ZhifubaoPay.Config.appPrivateKey,"json","GBK",ZhifubaoPay.Config.alipayPublicKey,"RSA2");
//        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
//        TransTo transTo=new TransTo();
//        Gson g=new Gson();
//        transTo.setOut_biz_no(System.currentTimeMillis()+"");
//        transTo.setPayee_account("abc@sina.com");
//        transTo.setPayee_real_name("张三");
//        transTo.setPayer_show_name("上海交通卡退款");
//
//        request.setBizContent(g.toJson(transTo));
//        AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//
//        }
        return "---------";
    }
    @RequestMapping(value = "/alipayReturnNotice")
    @ResponseBody
    public void alipayReturnNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {

       // log.info("支付成功, 进入同步通知接口...");

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println(params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ZhifubaoPay.Config.alipayPublicKey,"GBK", "RSA2"); //调用SDK验证签名

        ModelAndView mv = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水





        }else {
          //  log.info("支付, 验签失败...");
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Insert title here</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<h1>hello</h1>\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
        writer.close();
     //   return Result.successResult();
    }

//    @Autowired
//    private AlipayTradeService alipayTradeService;
    /**
     * 支付异步通知
     *
     * 接收到异步通知并验签通过后，一定要检查通知内容，包括通知中的app_id、out_trade_no、total_amount是否与请求中的一致，并根据trade_status进行后续业务处理。
     *
     * https://docs.open.alipay.com/194/103296
     */
    @RequestMapping("/alipayNotifyNotice")
    @ResponseBody
    public String notify(HttpServletRequest request) throws Exception {
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder("/****************************** alipay notify ******************************/\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n") );
       // log.info(notifyBuild.toString());
        System.out.println(notifyBuild.toString());


        boolean flag = this.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            String typeString=new String(request.getParameter("passback_params").getBytes("ISO-8859-1"),"UTF-8");
            Integer type=Integer.parseInt(URLDecoder.decode( typeString));
            if(tradeStatus.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

//                ZhifubaoCallbackLog zhifubaoCallbackLog=new ZhifubaoCallbackLog();
//                zhifubaoCallbackLog.setCodes(trade_no);
//                zhifubaoCallbackLog.setReason(tradeStatus);
//                Gson gson=new Gson();
//                zhifubaoCallbackLog.setJson(gson.toJson(parameterMap));
//                zhifubaoCallbackLog.setTypes(1);
//                zhifubaoCallbackLog.setIsSuccess(1);
//                zhifubaoCallbackLogService.save(zhifubaoCallbackLog);
//
//                switch (type){
//                    case 1:
//                        Orders orders=ordersService.findByCode(out_trade_no);
//                        if(orders==null){
//                            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS_CODE);
//                        }
//                        orders.setThirdOrdersNumber(trade_no);
//                      //  orders.setThirdPlatformAction(tradeStatus);
//                        BaseProccess proccess=new ThirdPayedSuccessProccess(ThirdPayedSuccessFactory.newInstance(orders,trade_no),thirdPayedSuccessService);
//                        proccess.exec();
//                        if(proccess.getException().size()==0){
//                            return Result.successResult();
//                        }else{
//                            return Result.failureResult(proccess.getException().get(0));
//                        }
//
//                    case 2:
//                        UserDepositMoney userDepositMoney=userDepositMoneyService.findByCode(out_trade_no);
//                        if(userDepositMoney==null){
//                            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS_CODE);
//                        }
//                        userDepositMoney.setThirdCode(trade_no);
//                        userDepositMoney.setThirdNumber(tradeStatus);
//                        UserBalance userBalance=userBalanceService.findByUserId(userDepositMoney.getUserId());
//                        BaseProccess proccess1=new ThirdDepositMoneyPayedSuccessProccess(DepositMoneyWrapperFactory.newInstance(userBalance,userDepositMoney),thirdDepositMoneyPayedSuccessService);
//                        proccess1.exec();
//                        if(proccess1.getException().size()==0){
//                            return Result.successResult();
//                        }else{
//                            return Result.failureResult(proccess1.getException().get(0));
//                        }
//
//
//                    case 3:
//
//                        UserDepositPlatformMoney userDepositPlatformMoney=userDepositPlatformMoneyService.findByCode(out_trade_no);
//                        if(userDepositPlatformMoney==null){
//                            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS_CODE);
//                        }
//                        if(userDepositPlatformMoney.getIsSuccess()!=1){
//                        userDepositPlatformMoney.setThirdCode(trade_no);
//                        userDepositPlatformMoney.setThirdNumber(tradeStatus);
//                        UserBalance userBalance1=userBalanceService.findOneByUserIdNoLock(userDepositPlatformMoney.getUserId());
//                        BaseProccess proccess2=new ThirdDepositPlatformMoneyPayedSuccessProccess(DepositPlatformMoneyWrapperFactory.newInstance(userBalance1,userDepositPlatformMoney),thirdDepositPlatformMoneyPayedSuccessService);
//                        proccess2.exec();
//                        if(proccess2.getException().size()==0){
//                            return Result.successResult();
//                        }else{
//                            return Result.failureResult(proccess2.getException().get(0));
//                        }
//                        }
//                }



              //  orders.setThirdOrdersNumber(trade_no);




            } else if (tradeStatus.equals("TRADE_SUCCESS")){


                ZhifubaoCallbackLog zhifubaoCallbackLog=new ZhifubaoCallbackLog();
                zhifubaoCallbackLog.setCodes(trade_no);
                zhifubaoCallbackLog.setReason(tradeStatus);
                Gson gson=new Gson();
                zhifubaoCallbackLog.setJson(gson.toJson(parameterMap));
                zhifubaoCallbackLog.setTypes(1);
                zhifubaoCallbackLog.setIsSuccess(1);
             //   zhifubaoCallbackLogService.save(zhifubaoCallbackLog);

                switch (type){
                    case 1:
                        List<OrderUpdateWrapper> orders=ordersService.findByDirectCode(out_trade_no);
                        if(orders.size()==0){
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage("找不到订单");
                            callbackFailureLog.setTypes(1);
                            callbackFailureLog.setLocations("支付宝");
                            callbackFailureLog.setCode(out_trade_no);
                            callbackFailureLog.setThirdCode(trade_no);
                            callbackFailureLogService.save(callbackFailureLog);
                            return "success";
                        }
                     //   orders.setThirdOrdersNumber(trade_no);
                        //  orders.setThirdPlatformAction(tradeStatus);
                        BaseProccess proccess=new ThirdPayedSuccessProccess(orders,thirdPayedSuccessService,trade_no);
                        proccess.exec();
                        if(proccess.getException().size()==0){
                            return "success";
                        }else{
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage(proccess.getException().get(0).getMessage());
                            callbackFailureLog.setTypes(2);
                            callbackFailureLog.setLocations("支付宝");
                            callbackFailureLog.setCode(out_trade_no);
                            callbackFailureLog.setThirdCode(trade_no);
                            callbackFailureLogService.save(callbackFailureLog);
                            return "success";
                        }

                    case 2:
                        UserDepositMoney userDepositMoney=userDepositMoneyService.findByCode(out_trade_no);
                        if(userDepositMoney==null){
                            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS_CODE).toString();
                        }
                        userDepositMoney.setThirdCode(trade_no);
                        userDepositMoney.setThirdNumber(tradeStatus);
                        UserBalance userBalance=userBalanceService.findByUserId(userDepositMoney.getUserId());
                        BaseProccess proccess1=new ThirdDepositMoneyPayedSuccessProccess(DepositMoneyWrapperFactory.newInstance(userBalance,userDepositMoney),thirdDepositMoneyPayedSuccessService);
                        proccess1.exec();
                        if(proccess1.getException().size()==0){
                            return "success";
                        }else{
                            return "success";
                        }


                    case 3:

                        UserDepositPlatformMoney userDepositPlatformMoney=userDepositPlatformMoneyService.findByCode(out_trade_no);
                        if(userDepositPlatformMoney==null){
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage("找不到提现点币订单");
                            callbackFailureLog.setTypes(3);
                            callbackFailureLog.setLocations("支付宝");
                          //  callbackFailureLog.setCode(orders.getOrderCode());
                            callbackFailureLog.setThirdCode(trade_no);
                            callbackFailureLogService.save(callbackFailureLog);
                            return "success";
                        }
                        if(userDepositPlatformMoney.getIsSuccess()!=1) {
                            userDepositPlatformMoney.setThirdCode(trade_no);
                            userDepositPlatformMoney.setThirdNumber(tradeStatus);
                            UserBalance userBalance1 = userBalanceService.findByUserId(userDepositPlatformMoney.getUserId());
                            BaseProccess proccess2 = new ThirdDepositPlatformMoneyPayedSuccessProccess(DepositPlatformMoneyWrapperFactory.newInstance(userBalance1, userDepositPlatformMoney), thirdDepositPlatformMoneyPayedSuccessService);
                            proccess2.exec();
                            if (proccess2.getException().size() == 0) {

                                return "success";
                            } else {
                                CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                                callbackFailureLog.setMessage(proccess2.getException().get(0).getMessage());
                                callbackFailureLog.setTypes(4);
                                callbackFailureLog.setLocations("支付宝");
                                callbackFailureLog.setCode(userDepositPlatformMoney.getDepositStreamCode());
                                callbackFailureLog.setThirdCode(trade_no);
                                callbackFailureLogService.save(callbackFailureLog);
                                return "success";
                            }
                        }
                }

                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }else{
                ZhifubaoCallbackLog zhifubaoCallbackLog=new ZhifubaoCallbackLog();
                zhifubaoCallbackLog.setCodes(trade_no);
                zhifubaoCallbackLog.setReason(tradeStatus);
                Gson gson=new Gson();
                zhifubaoCallbackLog.setJson(gson.toJson(parameterMap));
                zhifubaoCallbackLog.setTypes(1);
                zhifubaoCallbackLog.setIsSuccess(2);
               // zhifubaoCallbackLogService.save(zhifubaoCallbackLog);
               // zhifubaoCallbackLog.setCreateTime();
            }

            return "success";
        }

        return "success";
    }

    /**
     * 订单查询(最主要用于查询订单的支付状态)
     * @param orderNo 商户订单号
     * @return
     */
    @GetMapping("/query")
    public String query(String orderNo){

//        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
//                .setOutTradeNo(orderNo);
//        AlipayF2FQueryResult result = alipayTradeService.queryTradeResult(builder);
//        switch (result.getTradeStatus()) {
//            case SUCCESS:
//                log.info("查询返回该订单支付成功: )");
//
//                AlipayTradeQueryResponse resp = result.getResponse();
//                log.info(resp.getTradeStatus());
////                log.info(resp.getFundBillList());
//                break;
//
//            case FAILED:
//                log.error("查询返回该订单支付失败!!!");
//                break;
//
//            case UNKNOWN:
//                log.error("系统异常，订单支付状态未知!!!");
//                break;
//
//            default:
//                log.error("不支持的交易状态，交易返回异常!!!");
//                break;
//        }
       // return result.getResponse().getBody();
        return "";
    }

    /**
     * 退款
     * @param orderNo 商户订单号
     * @return
     */
    @PostMapping("/refund")
    @ResponseBody
    public String refund(String orderNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        // 商户订单号
        model.setOutTradeNo(orderNo);
        // 退款金额
        model.setRefundAmount("0.01");
        // 退款原因
        model.setRefundReason("无理由退货");
        // 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
//        model.setOutRequestNo(UUID.randomUUID().toString());
        alipayRequest.setBizModel(model);

        AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }

    /**
     * 退款查询
     * @param orderNo 商户订单号
     * @param refundOrderNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部订单号
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/refundQuery")
    @ResponseBody
    public String refundQuery(String orderNo, String refundOrderNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model=new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderNo);
        model.setOutRequestNo(refundOrderNo);
        alipayRequest.setBizModel(model);

        AlipayTradeFastpayRefundQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }

    /**
     * 关闭交易
     * @param orderNo
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/close")
    @ResponseBody
    public String close(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model =new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        alipayRequest.setBizModel(model);

        AlipayTradeCloseResponse alipayResponse = alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }


    /**
     * billDate : 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
     * 查询对账单下载地址: https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query/
     * @param billDate
     */
    @GetMapping("/bill")
    @ResponseBody
    public void queryBill(String billDate) {
        // 1. 查询对账单下载地址
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
        model.setBillType("trade");
        model.setBillDate(billDate);
        request.setBizModel(model);
        try {
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                String billDownloadUrl = response.getBillDownloadUrl();
                System.out.println(billDownloadUrl);

                // 2. 下载对账单
                List<String> orderList = this.downloadBill(billDownloadUrl);
                System.out.println(orderList);
                // 3. 先比较支付宝的交易合计/退款合计笔数/实收金额是否和自己数据库中的数据一致，如果不一致证明有异常，再具体找出那些订单有异常
                // 查找支付宝支付成功而自己支付失败的记录和支付宝支付失败而自己认为支付成功的异常订单记录到数据库

            } else {
                // 失败
                String code = response.getCode();
                String msg = response.getMsg();
                String subCode = response.getSubCode();
                String subMsg = response.getSubMsg();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验签名
     * @param request
     * @return
     */
    public boolean rsaCheckV1(HttpServletRequest request){
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    ZhifubaoPay.Config.alipayPublicKey,
                    ZhifubaoPay.Config.charset,
                    ZhifubaoPay.Config.signType);

            return verifyResult;
        } catch (AlipayApiException e) {
         //   log.debug("verify sigin error, exception is:{}", e);
            return false;
        }
    }

    /**
     * 下载下来的是一个【账号_日期.csv.zip】文件（zip压缩文件名，里面有多个.csv文件）
     * 账号_日期_业务明细 ： 支付宝业务明细查询
     * 账号_日期_业务明细(汇总)：支付宝业务汇总查询
     *
     * 注意：如果数据量比较大，该方法可能需要更长的执行时间
     * @param billDownLoadUrl
     * @return
     * @throws IOException
     */
    private List<String> downloadBill(String billDownLoadUrl) throws IOException {
//        String ordersStr = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        RequestConfig config = RequestConfig.custom()
//                .setConnectTimeout(60000)
//                .setConnectionRequestTimeout(60000)
//                .setSocketTimeout(60000)
//                .build();
//        HttpGet httpRequest = new HttpGet(billDownLoadUrl);
//        httpRequest.setConfig(config);
//        CloseableHttpResponse response = null;
//        byte[] data = null;
//        try {
//            response = httpClient.execute(httpRequest);
//            HttpEntity entity = response.getEntity();
//            data = EntityUtils.toByteArray(entity);
//        } finally {
//            response.close();
//            httpClient.close();
//        }
//        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(data), Charset.forName("GBK"));
//        ZipEntry zipEntry = null;
//        try{
//            while( (zipEntry = zipInputStream.getNextEntry()) != null){
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                try{
//                    String name = zipEntry.getName();
//                    // 只要明细不要汇总
//                    if(name.contains("汇总")){
//                        continue;
//                    }
//                    byte[] byteBuff = new byte[4096];
//                    int bytesRead = 0;
//                    while ((bytesRead = zipInputStream.read(byteBuff)) != -1) {
//                        byteArrayOutputStream.write(byteBuff, 0, bytesRead);
//                    }
//                    ordersStr = byteArrayOutputStream.toString("GBK");
//                }finally {
//                    byteArrayOutputStream.close();
//                    zipInputStream.closeEntry();
//                }
//            }
//        } finally {
//            zipInputStream.close();
//        }
//
//        if (ordersStr.equals("")) {
//            return null;
//        }
//        String[] bills = ordersStr.split("\r\n");
//        List<String> billList = Arrays.asList(bills);
//        billList = billList.parallelStream().map(item -> item.replace("\t", "")).collect(Collectors.toList());

        return new ArrayList<>();
    }
}
