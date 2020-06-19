package com.dg.main.enums;

public enum UserStreamEnum {
   WITHDRAW_MONEY(1,"提现人民币"),WITHDRAW_PLATFORM_MONEY(2,"提现点币"),
    CANCEL_WITHDRAW_MOENY(3,"取消提现人民币"),CANCEL_WITHDRAW_PLATFROM_MONEY(4,"取消提现点币"),
    ZHIFUBAO_DEPOSIT_PLATFORM_MONEY(5,"支付宝充值点币"),WEIXIN_DEPOSIT_PLATFORM_MONEY(6,"微信充值点币"),
    MONEY_TO_PLATFORM_MONEY(7,"余额充值点币"),
    SEND_REDPACK(8,"发送红包"),TAKE_REDPACK(9,"获得红包"),SCHEDULER_REDPACK(10,"定时返回红包"),
  //  SCHEDULER_ORDERS(11,"定时结算"),
    BUYER_MONEY_PAY_ORDERS(11,"用户余额购买"),BUYER_PLATFORM_MONEY_PAY_ORDERS(12,"用户点币购买"),
    BUYER_MONEY_REFUND_ORDERS(13,"用户余额退款"),BUYER_PLATFORM_MONEY_REFUND_ORDERS(14,"用户点币退款"),
    SELLER_MONEY_GET_ORDERS(15,"商家获得余额"),SELLER_PLATFORM_FORM_GET_ORDERS(16,"商家获得点币"),
    SELLER_ZHIFUBAO_GET_ORDERS(17,"商家支付宝获得余额"),SELLER_WEIXIN_GET_ORDER(18,"商家微信获得余额"),

    SCHEDULER_SELLER_MONEY_GET_ORDERS(19,"定时商家获得余额"),SCHEDULER_SELLER_PLATFORM_FORM_GET_ORDERS(20,"定时商家获得点币"),
    SCHEDULER_SELLER_ZHIFUBAO_GET_ORDERS(21,"定时商家支付宝获得余额"),SCHEDULER_SELLER_WEIXIN_GET_ORDER(22,"定时商家微信获得余额");
    private Integer index;
    private String name;
    private UserStreamEnum(Integer index,String name){
        this.index=index;
        this.name=name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserStreamEnum{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
