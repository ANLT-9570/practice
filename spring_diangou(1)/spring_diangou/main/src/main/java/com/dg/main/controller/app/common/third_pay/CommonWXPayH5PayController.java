package com.dg.main.controller.app.common.third_pay;

import com.dg.main.Entity.logs.CallbackFailureLog;
import com.dg.main.Entity.logs.WeixinCallbackLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.logs.CallbackFailureLogService;
import com.dg.main.serviceImpl.logs.WeixinCallbackLogService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.factory.DepositMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.DepositPlatformMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.ThirdPayedSuccessFactory;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;

import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.update_status.ThirdDepositMoneyPayedSuccessProccess;
import com.dg.main.serviceImpl.orders.update_status.ThirdDepositPlatformMoneyPayedSuccessProccess;
import com.dg.main.serviceImpl.orders.update_status.ThirdPayedSuccessProccess;
import com.dg.main.util.*;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信支付-H5支付.
 * <p>
 * detailed description
 *
 * @author Mengday Zhang
 * @version 1.0
 * @since 2018/6/18
 */

@RestController
@RequestMapping("/common/wxpay/h5pay")
@Api(tags = "通用-微信支付")
public class CommonWXPayH5PayController {

    @Autowired
    private WXPay wxPay;

    @Autowired
    private WXPayConfig wxPayConfig;
    @Autowired
    OrdersService ordersService;

    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    WeixinCallbackLogService weixinCallbackLogService;
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
    @ResponseBody
    @RequestMapping(value = "/pay")
    public Result weixinPayWap(HttpServletRequest request, HttpServletResponse response) {
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
        Map<String,String> result=new HashMap<>();
        Result result1=null;
        try{
       // String APPID = "你的apid";
        String APPID = WeixinPay.Config.appID;
      //  String MERID = "你的商户号";
        String MERID = WeixinPay.Config.mchID;
      //  String SIGNKEY = "你的商户密钥";
        String SIGNKEY = WeixinPay.Config.key;
        String spbill_create_ip = IPUtils.getIpAddr(request);//生产

        //String spbill_create_ip = "";//测试地址，也就是本地真是ip，用于本地测试用
        String scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"这里写在h5支付配置的那个域名\",\"wap_name\": \"信息认证\"}}";//我这里是网页入口，app入口参考文档的安卓和ios写法
        String tradeType = "MWEB";//H5支付标记
        String MD5 = "MD5";//虽然官方文档不是必须参数，但是不送有时候会验签失败
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("appid", WeixinPay.Config.appID);
        paramMap.put("mch_id",WeixinPay.Config.mchID);
        paramMap.put("nonce_str",WXPayUtil.generateNonceStr());
        paramMap.put("body","XX商城-订单结算");
        paramMap.put("out_trade_no", System.currentTimeMillis()+"");
        paramMap.put("total_fee","1");
        paramMap.put("spbill_create_ip",spbill_create_ip);
        paramMap.put("notify_url",WeixinPay.Config.notifyUrl);
        paramMap.put("trade_type","MWEB");
        paramMap.put("scene_info", "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
                "   {\"type\": \"\",  //场景类型\n" +
                "    \"wap_url\": \"\",//ww.dgkjmm.com\n" +
                "    \"wap_name\": \"\"  //点购科技\n" +
                "    }\n" +
                "}");

        String sign = WXPayUtil.generateSignature(paramMap, WeixinPay.Config.key);
        paramMap.put("sign",sign);

        //    HttpClient

//        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//微信统一下单接口
             String mweb_url = "";
            String xml = WXPayUtil.mapToXml(paramMap);//将所有参数(map)转xml格式

            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
          //  String unifiedorder_url = https://api.mch.weixin.qq.com/pay/unifiedorder;

         String re = WebUtils.postData(WeixinPay.Config.URL, xml);//发送post请求"统一下单接口"
            result=WXPayUtil.xmlToMap(re);

            System.out.println("------------");
            if("SUCCESS".equals(result.get("return_code"))){
                String resultCode=(String)result.get("result_code");
                System.out.println("订单号：{}发起H5支付成功"+result.get("mweb_url"));
                result1=Result.successResult(result.get("mweb_url"));
            }else{
                System.out.println("订单号：{}发起H5支付(系统)失败:{}"+result.get("return_msg"));
                result1=Result.failureResult(new Tuple2<>("1232132",result.get("return_msg")));
            }
        } catch (Exception e) {
            System.out.println("统一支付接口获取预支付订单出错");
          //  result.put("msg", "支付错误");
           // return result.toString();
        }
        System.out.println(result);
     //   result.put("mwebUrl",mweb_url);

        //添加微信支付记录日志等操作


    //    result.put("msg", "success");
        return result1;
    }


    /**
     * 使用沙箱支付的金额必须是用例中指定的金额，也就是 1.01 元，1.02元等，不能是你自己的商品的实际价格，必须是这个数。
     * 否则会报错：沙箱支付金额(2000)无效，请检查需要验收的case
     * @return
     * @throws Exception
     */
    @PostMapping("/order")
    public Object h5pay(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("out_trade_no", String.valueOf(System.nanoTime()));
        reqData.put("trade_type", "MWEB");
        reqData.put("product_id", "1");
        reqData.put("body", "商户下单");
        // 订单总金额，单位为分
        reqData.put("total_fee", "101");
        // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        reqData.put("spbill_create_ip", "14.23.150.211");
        // 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        reqData.put("notify_url", "http://3sbqi7.natappfree.cc/wxpay/h5pay/notify");
        // 自定义参数, 可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        reqData.put("device_info", "");
        // 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
        reqData.put("attach", "");
        reqData.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"http://3sbqi7.natappfree.cc\",\"wap_name\": \"腾讯充值\"}}");

        Map<String, String> responseMap = wxPay.unifiedOrder(reqData);
     //   log.info(responseMap.toString());
        String returnCode = responseMap.get("return_code");
        String resultCode = responseMap.get("result_code");
        if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
            // 预支付交易会话标识
            String prepayId = responseMap.get("prepay_id");
            // 支付跳转链接(前端需要在该地址上拼接redirect_url,该参数不是必须的)
            // 正常流程用户支付完成后会返回至发起支付的页面，如需返回至指定页面，则可以在MWEB_URL后拼接上redirect_url参数，来指定回调页面
            // 需对redirect_url进行urlencode处理

            // TODO 正常情况下这里应该是普通的链接，不知道这里为何是weixin://这样的链接，不知道是不是微信公众平台上的配置少配置了；
            // 由于没有实际账号，还没找到为啥不是普通链接的原因
            String mwebUrl = responseMap.get("mweb_url");
        }

        return responseMap;
    }

    /**
     * 注意：如果是沙箱环境，一提交订单就会立即异步通知，而无需拉起微信支付收银台的中间页面
     * @param request
     * @throws Exception
     */
    @RequestMapping("/notify")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        WeixinPay ipay=new WeixinPay(wxPayConfig);
        Map<String, String> reqData = ipay.getNotifyParameter(request);
      //  log.info(reqData.toString());
        Map<String, String> responseMap1 = new HashMap<>();
        responseMap1.put("return_code", "SUCCESS");
        responseMap1.put("return_msg", "OK");
        String responseXml = WXPayUtil.mapToXml(responseMap1);


        String returnCode = reqData.get("return_code");
        String resultCode = reqData.get("result_code");
        Integer type=Integer.valueOf(reqData.get("attach"));
        if (WXPayConstants.SUCCESS.equals(returnCode) && WXPayConstants.SUCCESS.equals(resultCode)) {
            boolean signatureValid = wxPay.isPayResultNotifySignatureValid(reqData);

            if (signatureValid) {
                // TODO 业务处理


//                Map<String, String> responseMap = new HashMap<>(2);
//                responseMap.put("return_code", "SUCCESS");
//                responseMap.put("return_msg", "OK");

                //  orders.setThirdOrdersNumber(trade_no);


                WeixinCallbackLog weixinCallbackLog=new WeixinCallbackLog();
                weixinCallbackLog.setCodes(reqData.get("transaction_id"));
                weixinCallbackLog.setReason(returnCode);
                Gson gson=new Gson();
                weixinCallbackLog.setJson(gson.toJson(reqData));
                weixinCallbackLog.setTypes(2);
                weixinCallbackLog.setIsSuccess(1);
              //  weixinCallbackLogService.save(weixinCallbackLog);
                switch (type){
                    case 1:
                        List<OrderUpdateWrapper> orders=ordersService.findByDirectCode(reqData.get("out_trade_no"));
                        if(orders.size()==0){
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage("找不到订单");
                            callbackFailureLog.setTypes(1);
                            callbackFailureLog.setLocations("微信支付");
                              callbackFailureLog.setCode(reqData.get("out_trade_no"));
                            callbackFailureLog.setThirdCode(reqData.get("transaction_id"));
                            callbackFailureLogService.save(callbackFailureLog);
                            return responseXml;
                        }
                     //   orders.setThirdOrdersNumber(reqData.get("transaction_id"));
                        //  orders.setThirdPlatformAction(tradeStatus);
                        BaseProccess proccess=new ThirdPayedSuccessProccess(orders,thirdPayedSuccessService,reqData.get("out_trade_no"));
                        proccess.exec();
                        if(proccess.getException().size()==0){
                            return responseXml;
                        }else{
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage(proccess.getException().get(0).getMessage());
                            callbackFailureLog.setTypes(2);
                            callbackFailureLog.setLocations("微信支付");
                            callbackFailureLog.setCode(reqData.get("out_trade_no"));
                            callbackFailureLog.setThirdCode(reqData.get("transaction_id"));
                            callbackFailureLogService.save(callbackFailureLog);
                            return responseXml;
                        }

                    case 2:
                        UserDepositMoney userDepositMoney=userDepositMoneyService.findByCode(reqData.get("out_trade_no"));
                        if(userDepositMoney==null){
                            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS_CODE).toString();
                        }
                        userDepositMoney.setThirdCode(reqData.get("transaction_id"));
                        userDepositMoney.setThirdNumber("SUCCESS");
                        UserBalance userBalance=userBalanceService.findByUserId(userDepositMoney.getUserId());
                        BaseProccess proccess1=new ThirdDepositMoneyPayedSuccessProccess(DepositMoneyWrapperFactory.newInstance(userBalance,userDepositMoney),thirdDepositMoneyPayedSuccessService);
                        proccess1.exec();
                        if(proccess1.getException().size()==0){
                            return responseXml;
                        }else{
                            return responseXml;
                        }


                    case 3:

                        UserDepositPlatformMoney userDepositPlatformMoney=userDepositPlatformMoneyService.findByCode(reqData.get("out_trade_no"));
                        if(userDepositPlatformMoney==null){
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage("找不到提现点币订单");
                            callbackFailureLog.setTypes(3);
                            callbackFailureLog.setLocations("微信支付");
                            //  callbackFailureLog.setCode(orders.getOrderCode());
                            callbackFailureLog.setThirdCode(reqData.get("transaction_id"));
                            callbackFailureLogService.save(callbackFailureLog);
                            return responseXml;
                        }
                        if(userDepositPlatformMoney.getIsSuccess()!=1){
                        userDepositPlatformMoney.setThirdCode(reqData.get("transaction_id"));
                        userDepositPlatformMoney.setThirdNumber("SUCCESS");
                        UserBalance userBalance1=userBalanceService.findByUserId(userDepositPlatformMoney.getUserId());
                        BaseProccess proccess2=new ThirdDepositPlatformMoneyPayedSuccessProccess(DepositPlatformMoneyWrapperFactory.newInstance(userBalance1,userDepositPlatformMoney),thirdDepositPlatformMoneyPayedSuccessService);
                        proccess2.exec();
                        if(proccess2.getException().size()==0){
                             return responseXml;
                        }else{
                            CallbackFailureLog callbackFailureLog=new CallbackFailureLog();
                            callbackFailureLog.setMessage(proccess2.getException().get(0).getMessage());
                            callbackFailureLog.setTypes(4);
                            callbackFailureLog.setLocations("微信支付");
                            callbackFailureLog.setCode(userDepositPlatformMoney.getDepositStreamCode());
                            callbackFailureLog.setThirdCode(reqData.get("transaction_id"));
                            callbackFailureLogService.save(callbackFailureLog);
                            return responseXml;
                        }
                        }
                }
//                String responseXml = WXPayUtil.mapToXml(responseMap);
//
//                response.setContentType("text/xml");
//                response.getWriter().write(responseXml);
//                response.flushBuffer();
            }else{
                WeixinCallbackLog weixinCallbackLog=new WeixinCallbackLog();
                weixinCallbackLog.setCodes(reqData.get("transaction_id"));
                weixinCallbackLog.setReason(returnCode);
                Gson gson=new Gson();
                weixinCallbackLog.setJson(gson.toJson(reqData));
                weixinCallbackLog.setTypes(2);
                weixinCallbackLog.setIsSuccess(1);
              //  weixinCallbackLogService.save(weixinCallbackLog);
            }
        }else{
            WeixinCallbackLog weixinCallbackLog=new WeixinCallbackLog();
            weixinCallbackLog.setCodes(reqData.get("transaction_id"));
            weixinCallbackLog.setReason(returnCode);
            Gson gson=new Gson();
            weixinCallbackLog.setJson(gson.toJson(reqData));
            weixinCallbackLog.setTypes(2);
            weixinCallbackLog.setIsSuccess(1);
        //    weixinCallbackLogService.save(weixinCallbackLog);
        }
        return responseXml;
    }
}
