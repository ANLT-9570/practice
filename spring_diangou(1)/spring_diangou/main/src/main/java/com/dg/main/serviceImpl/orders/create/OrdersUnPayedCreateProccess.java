package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.MoneyNotEnoughException;
import com.dg.main.exception.orders.PlatformMoneyNotEnoughException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.*;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersUnPayedCreateService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.IPUtils;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;
import com.github.wxpay.sdk.WXPayUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OrdersUnPayedCreateProccess  extends BaseProccess {
    private final List<OrdersWrapper> ordersWrapper;
//    private OrdersService ordersService;
//    private SpecificationsServer specificationsServer;
//    private OrderRefundLogService orderRefundLogService;
//    private OrderStreamLogService orderStreamLogService;
    private OrdersUnPayedCreateService ordersService;
    private UserBalanceService userBalanceService;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Map<String,String> paramMap=new HashMap<>();
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }
    private IPay iPay;
    AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
    public OrdersUnPayedCreateProccess(List<OrdersWrapper> ordersWrapper, OrdersUnPayedCreateService ordersService,UserBalanceService userBalanceService) {
        this.ordersWrapper = ordersWrapper;
        this.ordersService = ordersService;
        this.userBalanceService=userBalanceService;
    }
    private static final Function<UserBalance,Function<Long,Boolean>> checkMoney= userBalance -> money -> {

        if(userBalance.getMoney()<money){
            return false;
        }
        return true;
    };
    private static final Function<UserBalance,Function<Long,Boolean>> checkPlatformMoney=userBalance -> money -> {
        if(userBalance.getPlatformMoney()<money){
            return false;
        }
        return true;
    };
//    private static final Function<Orders,Boolean> checkZero=orders -> {
//        if(orders.getPayMoney()==0){
//            return false;
//        }
//        return true;
//    };

    public OrdersWrapper getOrdersWrapper() {
        return null;
       // return null;
    }
    private Integer action=0;
    private Orders orders=null;
    Long totalMoney=0l;
    Long totalPlatformMoney=0l;
    String code="";
    UserBalance userBalance=null;
    @Override
    public boolean validate() {
        Long userId=ordersWrapper.get(0).getOrders().getCustomerId();
         userBalance=userBalanceService.findByUserId(userId);

         action=0;
        for(OrdersWrapper wrapper:ordersWrapper){
            Orders orders=wrapper.getOrders();
            totalMoney=totalMoney+orders.getTotalMoney();
            totalPlatformMoney=orders.getTotalPlatformMoney()+totalPlatformMoney;
            action=orders.getThirdPlatformAction();
            this.orders=orders;
            this.action=orders.getThirdPlatformAction();
            this.code=orders.getDirectCode();
        }
        if(action==1){
            if(!checkMoney.apply(userBalance).apply(totalMoney)){
                exceptions.add(new MoneyNotEnoughException(ExceptionCode.Failure.MONEY_NOT_ENOUGH));

                return false;
            }
        }else if(action==2){
                        if(!checkPlatformMoney.apply(userBalance).apply(totalPlatformMoney)){
                exceptions.add(new PlatformMoneyNotEnoughException(ExceptionCode.Failure.PLATFOMR_MONEY_NOT_ENOUGH));
                return false;
            }

        }

        return true;
    }

    @Override
    public void action()  {
        ordersService.save( ordersWrapper);

        //   Orders _old=ordersWrapper.getOrders();
       // Orders _new= OrderFactory.newInstance(_old);
     //   Specifications specifications=ordersWrapper.getSpecifications();
       // specifications.setNumber(specifications.getNumber()-_old.getNumber());
//        orderRefundLogService.save(ordersWrapper.getOrderRefundLog());
//        orderStreamLogService.saveOrders(_old,_new);
//        specificationsServer.update(specifications);
      //  ordersService.save(_new);
    }

    @Override
    public void notifyChange() {
        if(orders.getIsDirectToPay()==1){

            try {
                if(iPay!=null){
                    if(iPay instanceof AppZhifubaoPay){
                      //  Orders orders=ordersWrapper.getOrders();
                          model.setTotalAmount(new BigDecimal(totalMoney).divide(new BigDecimal(100))+"");
                        model.setOutTradeNo(code);
                        model.setSubject(code);
                        model.setProductCode("QUICK_MSECURITY_PAY");
                        model.setPassbackParams(URLEncoder.encode("1"));

                        ((AppZhifubaoPay) iPay).setModel(model);
                        ((AppZhifubaoPay) iPay).setResponse(response);
                        iPay.pay();
                    }else if(iPay instanceof AppWeinxinPay){
                      //  Orders orders=ordersWrapper.getOrders();
                        String spbill_create_ip = IPUtils.getIpAddr(request);//生产
                        paramMap.put("appid", WeixinPay.Config.appID);
                        paramMap.put("mch_id",WeixinPay.Config.mchID);
                        paramMap.put("attach","1");
                        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
                        paramMap.put("body",code);
                        paramMap.put("out_trade_no", code);
                        paramMap.put("total_fee",totalMoney+"");
                        paramMap.put("spbill_create_ip",spbill_create_ip);
                        paramMap.put("notify_url",WeixinPay.Config.notifyUrl);
                        paramMap.put("trade_type", "APP");
//                        paramMap.put("scene_info", "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
////                                "   {\"type\": \"\",  //场景类型\n" +
////                                "    \"wap_url\": \"\",//ww.dgkjmm.com\n" +
////                                "    \"wap_name\": \"\"  //点购科技\n" +
////                                "    }\n" +
////                                "}");
                        ((AppWeinxinPay) iPay).setResponse(response);
                        ((AppWeinxinPay) iPay).setParamMap(paramMap);
                        iPay.pay();
                    }else if(iPay instanceof MoneyPay){
                        ((MoneyPay) iPay).setOrdersWrapper(ordersWrapper);
                        ((MoneyPay) iPay).setUserBalance(userBalance);
                        ((MoneyPay) iPay).setUserBalanceService(userBalanceService);
                        iPay.pay();
                    }else if(iPay instanceof PlatformPay){
                        ((PlatformPay) iPay).setOrdersWrapper(ordersWrapper);
                        ((PlatformPay) iPay).setUserBalance(userBalance);
                        iPay.pay();
                    }
                }
                //depositMoneyCreateService.save(depositMoneyWrapper);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
