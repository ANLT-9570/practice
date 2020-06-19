package com.dg.main.param.orders;

import com.dg.main.Entity.orders.OrderItems;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
public class OrdersParam {
    private Long sellerId;//卖家ID商家
    private Long buyerId;//买家ID客户
    private Long shopId;//商家IDString
    private List<OrderItemsParam> items;
    private Integer action=1;//1人民币支付 2平台币支付 3 支付宝支付 4 微信支付
    private String money;//人民币
    private Long addressId;
    private Long couponId;
//    private String platformMoney;//点币
    private String total;//总额
//    private Long number=0l;//数量
//    private Long specificationId;//规格ID
//    private String  specification;//尺寸ID
//    private String addressId;//地址
    private Long postMoney=0l;
    private Long cutOffMoney=0l;
    private String mark;
    private Integer isDirectToPay=2;


}
