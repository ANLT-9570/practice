package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.github.wxpay.sdk.WXPayUtil;
import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.wrapper.DepositMoneyWrapper;
import com.dg.main.util.IPUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DepositMoneyCreateProccess extends BaseProccess {
     private IPay ipay;
    final private DepositMoneyWrapper depositMoneyWrapper;
//    private UserDepositMoneyService userDepositMoneyService;
//    private UserDepositMoneyLogService userDepositMoneyLogService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    private UserBalanceService userBalanceService;
    private DepositMoneyCreateService depositMoneyCreateService;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Map<String,String> paramMap=new HashMap<>();
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
   // AlipayTradeAppPayModel model=new AlipayTradeAppPayModel();
    public DepositMoneyCreateProccess(DepositMoneyWrapper depositMoneyWrapper, DepositMoneyCreateService depositMoneyCreateService) {
        this.depositMoneyWrapper = depositMoneyWrapper;
        this.depositMoneyCreateService = depositMoneyCreateService;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setIpay(IPay ipay) {
        this.ipay = ipay;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void action() {

            try {
                if(ipay!=null){
                    if(ipay instanceof ZhifubaoPay){
                        UserDepositMoney userDepositMoney=depositMoneyWrapper.getUserDepositMoney();
                        model.setTotalAmount(new BigDecimal(userDepositMoney.getMoney()).divide(new BigDecimal(100))+"");
                        model.setOutTradeNo(userDepositMoney.getDepositCode());
                        model.setSubject("充值人民币");
                        model.setPassbackParams(URLEncoder.encode("2"));
                        ((ZhifubaoPay) ipay).setModel(model);
                        ((ZhifubaoPay) ipay).setResponse(response);
                        ipay.pay();
                    }else if(ipay instanceof WeixinPay){
                        UserDepositMoney userDepositMoney=depositMoneyWrapper.getUserDepositMoney();
                        String spbill_create_ip = IPUtils.getIpAddr(request);//生产
                        paramMap.put("appid", WeixinPay.Config.appID);
                        paramMap.put("mch_id",WeixinPay.Config.mchID);
                        paramMap.put("attach","2");
                        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
                        paramMap.put("body","充值人民币");
                        paramMap.put("out_trade_no", userDepositMoney.getDepositCode());
                        paramMap.put("total_fee",userDepositMoney.getMoney()+"");
                        paramMap.put("spbill_create_ip",spbill_create_ip);
                        paramMap.put("notify_url",WeixinPay.Config.notifyUrl);
                        paramMap.put("trade_type","MWEB");
                        paramMap.put("scene_info", "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
                                "   {\"type\": \"\",  //场景类型\n" +
                                "    \"wap_url\": \"\",//ww.dgkjmm.com\n" +
                                "    \"wap_name\": \"\"  //点购科技\n" +
                                "    }\n" +
                                "}");
                        ((WeixinPay) ipay).setResponse(response);
                        ((WeixinPay) ipay).setParamMap(paramMap);
                        ipay.pay();
                    }
                }
                //depositMoneyCreateService.save(depositMoneyWrapper);

            } catch (Exception e) {
                e.printStackTrace();
            }


//        UserDepositMoney userDepositMoney=depositMoneyWrapper.getUserDepositMoney();
//        UserDepositMoneyLog userDepositMoneyLog=depositMoneyWrapper.getUserDepositMoneyLog();
//        UserMoneyStreamLog userMoneyStreamLog=new UserMoneyStreamLog();
//
//        UserBalance oldObj=depositMoneyWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
//        newObj.setMoney(oldObj.getMoney()+userDepositMoney.getMoney());
//        newObj.setModifyTime(new Date());
//        userBalanceService.update(newObj);
//        userMoneyStreamLog.setUserId(oldObj.getUserId());
//        userMoneyStreamLog.setCreateTime(new Date());
//        userMoneyStreamLog.setJsonOfPreviousUserBalance(gson.toJson(oldObj));
//        userMoneyStreamLog.setJsonOfCurrentUserBalance(gson.toJson(newObj));
//
//        userMoneyStreamLogService.save(userMoneyStreamLog);
//
//        userDepositMoneyService.save(userDepositMoney);
//        userDepositMoneyLogService.save(userDepositMoneyLog);

    }

    @Override
    public void notifyChange() {

    }
}
