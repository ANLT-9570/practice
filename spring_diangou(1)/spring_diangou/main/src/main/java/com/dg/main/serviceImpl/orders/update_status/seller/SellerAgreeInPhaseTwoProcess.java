package com.dg.main.serviceImpl.orders.update_status.seller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.payment.IPay;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.dg.main.serviceImpl.orders.payment.WeixinRefundPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoRefundPay;
import com.dg.main.serviceImpl.orders.service.seller.SellerAgreeInPhaseTwoService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SellerAgreeInPhaseTwoProcess  extends BaseProccess  implements ZhifubaoRefundPay.ZhifubaoRefundCallback, WeixinRefundPay.WeixinRefundPayCallback{
    private final OrderUpdateWrapper  wrapper;
    private SellerAgreeInPhaseTwoService sellerAgreeInPhaseTwoService;
    private IPay iPay;
    private AlipayTradeRefundModel model=new AlipayTradeRefundModel();
    private Map<String,String> paramMap=new HashMap<>();
    public SellerAgreeInPhaseTwoProcess(OrderUpdateWrapper  wrapper, SellerAgreeInPhaseTwoService sellerAgreeInPhaseTwoService) {
        this.wrapper = wrapper;
        this.sellerAgreeInPhaseTwoService = sellerAgreeInPhaseTwoService;
    }
    public void setiPay(IPay iPay) {
        this.iPay = iPay;
    }
    private final Function<Orders,Boolean> check= orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    private final Function<OrderItems,Boolean> checkOrderItems= orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    private final Function<OrderItems,Boolean> check1=orders -> {
        if(orders.getIsRefundInPhaseTwo()==2){
            return false;
        }
        return true;
    };
    @Override
    public boolean validate() {
        if(wrapper.getOrders()!=null){
            if(!check.apply(wrapper.getOrders())){
                exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND));
                return false;
            }
        }else{

        }

        return true;
    }

    @Override
    public void action() throws IOException, AlipayApiException {

            if(wrapper.getOrders().getIsSellerAgreeInPhaseTwo()==1) {
                if (wrapper.getOrders().getThirdPlatformAction() == 1) {
                    sellerAgreeInPhaseTwoService.action(wrapper);
                } else if (wrapper.getOrders().getThirdPlatformAction() == 2) {
                    sellerAgreeInPhaseTwoService.action(wrapper);
                } else if (wrapper.getOrders().getThirdPlatformAction() == 3) {
                    if(wrapper.getOrders().getIsSinglePay()==1) {
                        Orders orders = wrapper.getOrders();
                        model.setOutTradeNo(orders.getOrderCode());
                        //  model.setTradeNo();
                        // 退款金额
                        model.setRefundAmount(new BigDecimal(orders.getTotalMoney()).divide(new BigDecimal(100)).toString());
                        // 退款原因
                        model.setRefundReason("no reason");
                        ((ZhifubaoRefundPay) iPay).setModel(model);
                        //   ((ZhifubaoRefundPay)iPay).setResponse();
                        ((ZhifubaoRefundPay) iPay).setZhifubaoRefundCallback(this);

                        try {
                            iPay.pay();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else if (wrapper.getOrders().getThirdPlatformAction() == 4) {
                    if (wrapper.getOrders().getIsSinglePay() == 1) {
                        Orders orders = wrapper.getOrders();
                        // 商户订单号
                        paramMap.put("out_trade_no", orders.getOrderCode());
                        // 授权码
                        //  paramMap.put("transaction_id", orders.getThirdOrdersNumber());
                        // 订单总金额，单位为分，只能为整数
                        paramMap.put("total_fee", orders.getTotalMoney() + "");
                        paramMap.put("refund_fee", orders.getTotalMoney() + "");
                        paramMap.put("refund_fee_type", "CNY");
                        paramMap.put("out_refund_no", System.currentTimeMillis() + "");
                        paramMap.put("appid", WeixinPay.Config.appID);
                        paramMap.put("mch_id", WeixinPay.Config.mchID);
                        //退款金额
                        //   paramMap.put("refund_fee", "2");
                        ((WeixinRefundPay) iPay).setWeixinRefundPayCallback(this);
                        ((WeixinRefundPay) iPay).setParamMap(paramMap);
                        try {
                            iPay.pay();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else if(wrapper.getOrders().getIsSellerAgreeInPhaseTwo()==2){
                sellerAgreeInPhaseTwoService.disagree(wrapper);
            }


    }

    @Override
    public void notifyChange() {

    }

    @Override
    public void weixinRefundPaySuccess() {
        sellerAgreeInPhaseTwoService.thirdCallbackSuccess(wrapper);
    }

    @Override
    public void weixinRefundPayFailure() {

    }

    @Override
    public void zhifubaoRefundSuccess() {
        sellerAgreeInPhaseTwoService.thirdCallbackSuccess(wrapper);
    }

    @Override
    public void zhifubaoRefundFailure() {

    }
}
