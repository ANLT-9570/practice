package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.github.wxpay.sdk.WXPayUtil;
import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.wrapper.DepositPlatformMoneyWrapper;
import com.dg.main.util.IPUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DepositPlatformMoneyCreateProccess extends BaseProccess {
    private IPay iPay;
    private HttpServletResponse response;
    final private DepositPlatformMoneyWrapper depositPlatformMoneyWrapper;
    private DepositPlatformMoneyCreateService depositPlatformMoneyCreateService;
    private HttpServletRequest request;
    private Map<String,String> paramMap=new HashMap<>();
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
   // AlipayTradeAppPayModel model=new AlipayTradeAppPayModel();
    public DepositPlatformMoneyCreateProccess(DepositPlatformMoneyWrapper depositPlatformMoneyWrapper, DepositPlatformMoneyCreateService depositPlatformMoneyCreateService) {
        this.depositPlatformMoneyWrapper = depositPlatformMoneyWrapper;
        this.depositPlatformMoneyCreateService = depositPlatformMoneyCreateService;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {
        try{
            if(iPay!=null){
                if(iPay instanceof ZhifubaoPay){
                  //  depositPlatformMoneyCreateService.save(depositPlatformMoneyWrapper);
                    UserDepositPlatformMoney userDepositPlatformMoney=depositPlatformMoneyWrapper.getUserDepositPlatformMoney();
                    model.setTotalAmount(new BigDecimal(userDepositPlatformMoney.getMoney()).divide(new BigDecimal(100))+"");
                    model.setOutTradeNo(userDepositPlatformMoney.getDepositStreamCode());
                    model.setSubject(userDepositPlatformMoney.getDepositStreamCode());
                    model.setPassbackParams(URLEncoder.encode("3"));
                    ((ZhifubaoPay) iPay).setModel(model);
                    ((ZhifubaoPay) iPay).setResponse(response);
                    iPay.pay();
                }else if(iPay instanceof WeixinPay){
                    UserDepositPlatformMoney userDepositPlatformMoney=depositPlatformMoneyWrapper.getUserDepositPlatformMoney();
                    String spbill_create_ip = IPUtils.getIpAddr(request);//生产
                    paramMap.put("appid", WeixinPay.Config.appID);
                    paramMap.put("mch_id",WeixinPay.Config.mchID);
                    paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
                    paramMap.put("attach","3");
                    paramMap.put("body",userDepositPlatformMoney.getDepositStreamCode());
                    paramMap.put("out_trade_no", userDepositPlatformMoney.getDepositStreamCode());
                    paramMap.put("total_fee",userDepositPlatformMoney.getMoney()+"");
                    paramMap.put("spbill_create_ip",spbill_create_ip);
                    paramMap.put("notify_url",WeixinPay.Config.notifyUrl);
                    paramMap.put("trade_type","MWEB");
                    paramMap.put("scene_info", "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
                            "   {\"type\": \"\",  //场景类型\n" +
                            "    \"wap_url\": \"\",//ww.dgkjmm.com\n" +
                            "    \"wap_name\": \"\"  //点购科技\n" +
                            "    }\n" +
                            "}");
                    ((WeixinPay) iPay).setResponse(response);
                    ((WeixinPay) iPay).setParamMap(paramMap);
                    iPay.pay();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


//        UserDepositPlatformMoney userDepositPlatformMoney=depositPlatformMoneyWrapper.getUserDepositPlatformMoney();
//        UserBalance oldObj=depositPlatformMoneyWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
//        newObj.setModifyTime(new Date());
//        newObj.setPlatformMoney(oldObj.getPlatformMoney()+userDepositPlatformMoney.getPlatformMoney());
//        userBalanceService.update(newObj);
//
//        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
//        userMoneyStreamLog.setUserId(oldObj.getUserId());
//        userMoneyStreamLog.setCreateTime(new Date());
//        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
//        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
//        userMoneyStreamLogService.save(userMoneyStreamLog);
//        userDepositPlatformMoneyService.save(userDepositPlatformMoney);
//        userDepositPlatformMoneyLogService.save(depositPlatformMoneyWrapper.getUserDepositPlatformMoneyLog());
    }

    @Override
    public void notifyChange() {

    }
}
