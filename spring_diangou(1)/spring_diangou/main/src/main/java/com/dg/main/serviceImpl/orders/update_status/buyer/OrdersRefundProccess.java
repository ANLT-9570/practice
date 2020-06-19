package com.dg.main.serviceImpl.orders.update_status.buyer;

import com.alipay.api.domain.AlipayTradeRefundModel;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersRefundService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.Entity.orders.*;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.WeixinRefundPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoRefundPay;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OrdersRefundProccess extends BaseProccess implements ZhifubaoRefundPay.ZhifubaoRefundCallback, WeixinRefundPay.WeixinRefundPayCallback {
    private final OrderUpdateWrapper wrapper;
    private IPay iPay;
    private AlipayTradeRefundModel model=new AlipayTradeRefundModel();
    private Map<String,String> paramMap=new HashMap<>();
//    private OrdersService ordersService;
//    private OrderStreamLogService orderStreamLogService;
//    private OrderRefundLogService orderRefundLogService;
//
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
    private OrdersRefundService ordersRefundService;

    public OrdersRefundProccess(OrderUpdateWrapper wrapper, OrdersRefundService ordersRefundService) {
        this.wrapper = wrapper;
        this.ordersRefundService = ordersRefundService;
    }

    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }

    private static final Function<Orders,Boolean> checkRefund= orders -> {
//            if(orders.getIsRefundInPhaseTwo()==1){
//                return false;
//            }
            return true;
    };
    private static final Function<Orders,Boolean> checkOrderState=orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    private static final Function<Orders,Boolean> checkFinished=orders -> {
        if(orders.getIsRefundFinished()==1){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> checkOrderItemState=orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> checkOrderItemFinished=orders -> {
        if(orders.getIsRefundFinished()==1){
            return false;
        }
        return true;
    };
    @Override
    public boolean validate() {
        if(wrapper.getOrders()!=null){
            if(!checkOrderState.apply(wrapper.getOrders())){
            exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDER_STATUS_CHANGE));
            return false;
        }
        if(!checkRefund.apply(wrapper.getOrders())){
            exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND));
            return false;
        }
        if(!checkFinished.apply(wrapper.getOrders())){
            exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND_FINISHED));
            return false;
        }
        }else {
            for (OrderItems items : wrapper.getOldItems()) {
                if (!checkOrderItemState.apply(items)) {
                    exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND));
                    return false;
                }
                if (!checkOrderItemFinished.apply(items)) {
                    exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_SENDED));
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void action() {

        ordersRefundService.refund(wrapper);

//        if(wrapper.getOrders()!=null){
//                    if(wrapper.getOrders().getThirdPlatformAction()==1){
//            ordersRefundService.refund(ordersRefundWrapper);
//        }else if(wrapper.getOrders().getThirdPlatformAction()==2){
//            ordersRefundService.action(wrapper);
//        }else if(wrapper.getOrders().getThirdPlatformAction()==3){
//            Orders orders=wrapper.getOrders();
//            model.setOutTradeNo(orders.getOrderCode());
//          //  model.setTradeNo();
//            // 退款金额
//            model.setRefundAmount(new BigDecimal(orders.getTotal()).divide(new BigDecimal(100)).toString());
//            // 退款原因
//            model.setRefundReason("no reason");
//            ((ZhifubaoRefundPay)iPay).setModel(model);
//         //   ((ZhifubaoRefundPay)iPay).setResponse();
//            ((ZhifubaoRefundPay)iPay).setZhifubaoRefundCallback(this);
//
//            try {
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }else if(wrapper.getOrders().getThirdPlatformAction()==4){
//            Orders orders=wrapper.getOrders();
//            // 商户订单号
//            paramMap.put("out_trade_no", orders.getOrderCode());
//            // 授权码
//          //  paramMap.put("transaction_id", orders.getThirdOrdersNumber());
//            // 订单总金额，单位为分，只能为整数
//            paramMap.put("total_fee", orders.getTotal()+"");
//            paramMap.put("refund_fee", orders.getTotal()+"");
//            paramMap.put("refund_fee_type", "CNY");
//            paramMap.put("out_refund_no",System.currentTimeMillis()+"");
//            paramMap.put("appid", WeixinPay.Config.appID);
//            paramMap.put("mch_id",WeixinPay.Config.mchID);
//            //退款金额
//         //   paramMap.put("refund_fee", "2");
//            ((WeixinRefundPay)iPay).setWeixinRefundPayCallback(this);
//            ((WeixinRefundPay)iPay).setParamMap(paramMap);
//            try {
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        }else{
//
//        }

//        if(ordersRefundWrapper.getOrders().getThirdPlatformAction()==1){
//           // ordersRefundService.refund(ordersRefundWrapper);
//        }else if(ordersRefundWrapper.getOrders().getThirdPlatformAction()==2){
//            ordersRefundService.action(ordersRefundWrapper);
//        }else if(ordersRefundWrapper.getOrders().getThirdPlatformAction()==3){
//            Orders orders=ordersRefundWrapper.getOrders();
//            model.setOutTradeNo(orders.getOrderCode());
//          //  model.setTradeNo();
//            // 退款金额
//            model.setRefundAmount(new BigDecimal(orders.getTotal()).divide(new BigDecimal(100)).toString());
//            // 退款原因
//            model.setRefundReason("no reason");
//            ((ZhifubaoRefundPay)iPay).setModel(model);
//         //   ((ZhifubaoRefundPay)iPay).setResponse();
//            ((ZhifubaoRefundPay)iPay).setZhifubaoRefundCallback(this);
//
//            try {
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }else if(ordersRefundWrapper.getOrders().getThirdPlatformAction()==4){
//            Orders orders=ordersRefundWrapper.getOrders();
//            // 商户订单号
//            paramMap.put("out_trade_no", orders.getOrderCode());
//            // 授权码
//          //  paramMap.put("transaction_id", orders.getThirdOrdersNumber());
//            // 订单总金额，单位为分，只能为整数
//            paramMap.put("total_fee", orders.getTotal()+"");
//            paramMap.put("refund_fee", orders.getTotal()+"");
//            paramMap.put("refund_fee_type", "CNY");
//            paramMap.put("out_refund_no",System.currentTimeMillis()+"");
//            paramMap.put("appid", WeixinPay.Config.appID);
//            paramMap.put("mch_id",WeixinPay.Config.mchID);
//            //退款金额
//         //   paramMap.put("refund_fee", "2");
//            ((WeixinRefundPay)iPay).setWeixinRefundPayCallback(this);
//            ((WeixinRefundPay)iPay).setParamMap(paramMap);
//            try {
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }



    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void zhifubaoRefundSuccess() {
       // ordersRefundService.thirdCallbackSuccess(ordersRefundWrapper);
    }

    @Override
    public void zhifubaoRefundFailure() {
        System.out.println("-----ZhifubaoRefundFailure-----------failed");
    }

    @Override
    public void weixinRefundPaySuccess() {
      //  ordersRefundService.thirdCallbackSuccess(ordersRefundWrapper);
    }

    @Override
    public void weixinRefundPayFailure() {
        System.out.println("-----WeixinRefundPayFailure-----------failed");
    }
}
