package com.dg.main.param.orders;

import lombok.Data;

@Data
public class OrderItemsParam {
    private Long money;//人民币
    private String platformMoney;//点币
    private Long total;//总额
    private Long number=0l;//数量
    private Long specificationId;//规格ID
    private String  specification;//尺寸ID
    private String addressId;//地址
    private Long postMoney=0l;
    private Long sizeId;
    private String image;
    private String mark="";
    private Long couponId;
    private Long couponPrice;
    private Long shopId;
    private Long sellerId;
    private Long buyerId;
}
