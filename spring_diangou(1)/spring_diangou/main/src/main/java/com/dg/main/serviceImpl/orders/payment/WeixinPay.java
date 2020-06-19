package com.dg.main.serviceImpl.orders.payment;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;

import com.dg.main.util.*;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WeixinPay extends WXPay implements IPay  {
    private Map<String,String> paramMap=null;
    private HttpServletResponse response=null;

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public WeixinPay(WXPayConfig config) {
        super(config);
    }

//    public WeixinPay(WeixinConfiguration weixinConfiguration, WXPayConstants.SignType md5, Boolean useSandbox) {
//        super(weixinConfiguration, md5, useSandbox);
//    }
//    private static WeixinPay.Config config=new WeixinPay.Config();
//
//    public static WeixinPay.Config newInstance(){1549076991
//
//        return config;
//    }
    static public class Config {
        public static String URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
       /** 公众账号ID */
       public static String appID="wxa15cdb81f4926410";

       /** 商户号 */
       public static String mchID="1549076991";

       /** API 密钥 */
      // public static String key="65036D5330C476D87C9EB0E27C33983103566616";
        public static String key="8F00EF1066847C072FD3965189090686";

       /** API 沙箱环境密钥 */
       public static String sandboxKey;

       /** API证书绝对路径 */
       public static String certPath="C:\\ceshi\\gxsl\\base\\apiclient_cert.p12";
       public static String tradeType="MWEB";

       /** 退款异步通知地址 */
       public static String notifyUrl="http://120.78.179.177:8080/gxsl/wxpay/h5pay/notify";

       public static Boolean useSandbox=true;

       /** HTTP(S) 连接超时时间，单位毫秒 */
       public static int httpConnectTimeoutMs = 8000;

       /** HTTP(S) 读数据超时时间，单位毫秒 */
       public static int httpReadTimeoutMs = 10000;

       /** 密钥算法 */
       public static final String ALGORITHM = "AES";
       /** 加解密算法/工作模式/填充方式 */
       public static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";
       /** 用户支付中，需要输入密码 */
       public static final String ERR_CODE_USERPAYING = "USERPAYING";
       public static final String ERR_CODE_AUTHCODEEXPIRE = "AUTHCODEEXPIRE";
       /** 交易状态: 未支付 */
       public static final String TRADE_STATE_NOTPAY = "NOTPAY";

       /** 用户输入密码，尝试30秒内去查询支付结果 */
       public static Integer remainingTimeMs = 10000;


    }

    @Override
    public void pay() {
        System.out.println("--------started-----------------wei xin-----------------paying-------------");
        Map<String,String> result=new HashMap<>();
        Result result1=null;
        try{


            String sign = WXPayUtil.generateSignature(paramMap, WeixinPay.Config.key);
            paramMap.put("sign",sign);
            String xml = WXPayUtil.mapToXml(paramMap);//将所有参数(map)转xml格式

            String re = WebUtils.postData(WeixinPay.Config.URL, xml);//发送post请求"统一下单接口"
            result=WXPayUtil.xmlToMap(re);

            System.out.println("------------");
            if("SUCCESS".equals(result.get("return_code"))){
                String resultCode=(String)result.get("result_code");
                if(!resultCode.equals("FAIL")){
                	System.out.println("订单号：{}发起H5支付成功"+result.get("mweb_url"));
                    result1=Result.successResult(result.get("mweb_url"));
                    System.out.println("--------ended-----------------wei xin-----------------paying-------------");
                    ResponseUtils.send(response,result1);
                }else{
                	 result1=Result.failureResult(new Tuple2<>("1232132",result.get("err_code_des")));
                	 ResponseUtils.send(response,result1);
                }
                
            }else{
                System.out.println("订单号：{}发起H5支付(系统)失败:{}"+result.get("return_msg"));
                result1=Result.failureResult(new Tuple2<>("1232132",result.get("return_msg")));
                System.out.println("--------ended-----------------wei xin-----------------paying-------------");
                ResponseUtils.send(response,result1);
            }
        } catch (Exception e) {
            System.out.println("统一支付接口获取预支付订单出错");
            //  result.put("msg", "支付错误");
            // return result.toString();
        }

      //  System.out.println(result);
        //   result.put("mwebUrl",mweb_url);

        //添加微信支付记录日志等操作


        //    result.put("msg", "success");
       // return result1;
    }
    /**
     *
     * 刷卡支付
     *
     * 对WXPay#microPay(Map)增加了当支付结果为USERPAYING时去轮询查询支付结果的逻辑处理
     *
     * 注意：该方法没有处理return_code=FAIL的情况，暂时不考虑网络问题，这种情况直接返回错误
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> microPayWithPOS(Map<String, String> reqData) throws Exception {
        // 开始时间(毫秒)
        long startTimestampMs = System.currentTimeMillis();

        Map<String, String> responseMapForPay = super.microPay(reqData);
        //log.info(responseMapForPay.toString());

        // // 先判断 协议字段返回(return_code)，再判断 业务返回，最后判断 交易状态(trade_state)
        // 通信标识，非交易标识
        String returnCode = responseMapForPay.get("return_code");
        if (WXPayConstants.SUCCESS.equals(returnCode)) {
            String errCode = responseMapForPay.get("err_code");
            // 余额不足，信用卡失效
            if (WeixinPay.Config.ERR_CODE_USERPAYING.equals(errCode) || "SYSTEMERROR".equals(errCode) || "BANKERROR".equals(errCode)) {
                Map<String, String> orderQueryMap = null;
                Map<String, String> requestData = new HashMap<>();
                requestData.put("out_trade_no", reqData.get("out_trade_no"));

                // 用户支付中，需要输入密码或系统错误则去重新查询订单API err_code, result_code, err_code_des
                // 每次循环时的当前系统时间 - 开始时记录的时间 > 设定的30秒时间就退出
                while (System.currentTimeMillis() - startTimestampMs < WeixinPay.Config.remainingTimeMs) {
                    // 商户收银台得到USERPAYING状态后，经过商户后台系统调用【查询订单API】查询实际支付结果。
                    orderQueryMap = super.orderQuery(requestData);
                    String returnCodeForQuery = orderQueryMap.get("return_code");
                    if (WXPayConstants.SUCCESS.equals(returnCodeForQuery)) {
                        // 通讯成功
                        String tradeState = orderQueryMap.get("trade_state");
                        if (WXPayConstants.SUCCESS.equals(tradeState)) {
                            // 如果成功了直接将查询结果返回
                            return orderQueryMap;
                        }
                        // 如果支付结果仍为USERPAYING，则每隔5秒循环调用【查询订单API】判断实际支付结果
                        Thread.sleep(1000);
                    }
                }

                // 如果用户取消支付或累计30秒用户都未支付，商户收银台退出查询流程后继续调用【撤销订单API】撤销支付交易。
                String tradeState = orderQueryMap.get("trade_state");
                if (WeixinPay.Config.TRADE_STATE_NOTPAY.equals(tradeState) || WeixinPay.Config.ERR_CODE_USERPAYING.equals(tradeState) ||  WeixinPay.Config.ERR_CODE_AUTHCODEEXPIRE.equals(tradeState)) {
                    Map<String, String> reverseMap = this.reverse(requestData);
                    String returnCodeForReverse = reverseMap.get("return_code");
                    String resultCode = reverseMap.get("result_code");
                    if (WXPayConstants.SUCCESS.equals(returnCodeForReverse) && WXPayConstants.SUCCESS.equals(resultCode)) {
                        // 如果撤销成功，需要告诉客户端已经撤销订单了
                        responseMapForPay.put("err_code_des", "用户取消支付或尚未支付，后台已经撤销该订单，请重新支付！");
                    }
                }
            }
        }

        return responseMapForPay;
    }

    /**
     * 从request的inputStream中获取参数
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, String> getNotifyParameter(HttpServletRequest request) throws Exception {
        InputStream inputStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, length);
        }
        outSteam.close();
        inputStream.close();

        // 获取微信调用我们notify_url的返回信息
        String resultXml = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(resultXml);

        return notifyMap;
    }

    /**
     * 解密退款通知
     *
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_16&index=11>退款结果通知文档</a>
     * @return
     * @throws Exception
     */
    public Map<String, String> decodeRefundNotify(HttpServletRequest request) throws Exception {
        // 从request的流中获取参数
        Map<String, String> notifyMap = this.getNotifyParameter(request);
        //log.info(notifyMap.toString());

        String reqInfo = notifyMap.get("req_info");
        //（1）对加密串A做base64解码，得到加密串B
        byte[] bytes = new BASE64Decoder().decodeBuffer(reqInfo);

        //（2）对商户key做md5，得到32位小写key* ( key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置 )
        Cipher cipher = Cipher.getInstance(WeixinPay.Config.ALGORITHM_MODE_PADDING);
        SecretKeySpec key = new SecretKeySpec(WXPayUtil.MD5(WeixinPay.Config.key).toLowerCase().getBytes(), WeixinPay.Config.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        //（3）用key*对加密串B做AES-256-ECB解密（PKCS7Padding）
        // java.security.InvalidKeyException: Illegal key size or default parameters
        // https://www.cnblogs.com/yaks/p/5608358.html
        String responseXml = new String(cipher.doFinal(bytes),"UTF-8");
        Map<String, String> responseMap = WXPayUtil.xmlToMap(responseXml);
        return responseMap;
    }

    /**
     * 获取沙箱环境验签秘钥API
     * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=23_1">获取验签秘钥API文档</a>
     * @return
     * @throws Exception
     */
    public Map<String, String> getSignKey() throws Exception {
        Map<String, String> reqData = new HashMap<>();
        reqData.put("appid", Config.appID);
        reqData.put("mch_id", Config.mchID);
        reqData.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(reqData, Config.key, WXPayConstants.SignType.MD5);
        reqData.put("sign", sign);
        String responseXml = this.requestWithoutCert("https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey", reqData,
                Config.httpConnectTimeoutMs, Config.httpReadTimeoutMs);

        Map<String, String> responseMap = WXPayUtil.xmlToMap(responseXml);

        return responseMap;
    }
}
