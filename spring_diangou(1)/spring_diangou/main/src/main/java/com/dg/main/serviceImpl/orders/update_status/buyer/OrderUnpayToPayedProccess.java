package com.dg.main.serviceImpl.orders.update_status.buyer;

import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.MoneyNotEnoughException;
import com.dg.main.exception.orders.PlatformMoneyNotEnoughException;
import com.dg.main.serviceImpl.orders.service.buyer.OrderUnpayToPayedService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.github.wxpay.sdk.WXPayUtil;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.*;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.util.IPUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OrderUnpayToPayedProccess extends BaseProccess {

    private IPay iPay;
    private final List<OrdersWrapper> ordersWrapper;
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    private OrdersService ordersService;
    private OrderUnpayToPayedService orderUnpayToPayedService;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private Map<String,String> paramMap=new HashMap<>();
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
private UserBalanceService userBalanceService;
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
  // AlipayTradeAppPayModel model=new AlipayTradeAppPayModel();
    public OrderUnpayToPayedProccess(List<OrdersWrapper> ordersWrapper, OrderUnpayToPayedService orderUnpayToPayedService,UserBalanceService userBalanceService) {
        this.ordersWrapper = ordersWrapper;
        this.orderUnpayToPayedService = orderUnpayToPayedService;
        this.userBalanceService=userBalanceService;
    }
    private Integer action=0;
    private Orders orders=null;
    Long totalMoney=0l;
    Long totalPlatformMoney=0l;
    String code="";
    UserBalance userBalance=null;
//
    private static final Function<UserBalance, Function<Long,Boolean>> checkMoney= userBalance -> money -> {

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
////        if(orders.getPayMoney()==0){
////            return false;
////        }
//        return true;
//    };
//
    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }
//
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
            this.code=orders.getOrderCode();
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
//
    @Override
    public void action() {
        try {
            if(iPay!=null){
                if(iPay instanceof ZhifubaoPay){
                  //  Orders orders=ordersWrapper.getOrders();
                    model.setTotalAmount(new BigDecimal(totalMoney).divide(new BigDecimal(100))+"");
                    model.setOutTradeNo(code);
                    model.setSubject(code);
                    model.setProductCode("QUICK_WAP_WAY");
                    model.setPassbackParams(URLEncoder.encode("1"));
                    ((ZhifubaoPay) iPay).setModel(model);
                    ((ZhifubaoPay) iPay).setResponse(response);
                    iPay.pay();
                }else if(iPay instanceof WeixinPay){
                 //   Orders orders=ordersWrapper.getOrders();
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
                }else if(iPay instanceof MoneyPay){
                    ((MoneyPay) iPay).setOrdersWrapper(ordersWrapper);
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

//        Orders _old=ordersWrapper.getOrders();
//        Orders _new= OrderFactory.newInstance(_old);
//        UserBalance oldBalance=ordersWrapper.getUserBalance();
//        UserBalance newBalance= UserBalanceFactory.newInstance(oldBalance);
//        _new.setPhase(2);
//        _new.setModifyTime(new Timestamp(new Date().getTime()));
//        if(_old.getThirdPlatformAction()==1){
//            newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);
//            newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//            newBalance.setMoney(oldBalance.getMoney()-_old.getMoney());
//
//        }else if(_old.getThirdPlatformAction()==2){
//            newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);
//            newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//            newBalance.setPlatformMoney(oldBalance.getPlatformMoney()-_old.getPlatformMoney());
//        }
//        ordersService.update(_new);
//        userBalanceService.update(newBalance);
     //   orderStreamLogService.saveOrders(_old,_new);
     //   userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance);

    }
//
    @Override
    public void notifyChange() {

    }
}
