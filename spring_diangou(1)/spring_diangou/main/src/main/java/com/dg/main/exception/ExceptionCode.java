package com.dg.main.exception;

import com.dg.main.util.Tuple2;

public class ExceptionCode {
    public static class Success{
      final static   public  Tuple2<String,String> success=new Tuple2<>("10000","success");
    }
    public static class Failure{
        final static public Tuple2<String,String> MONEY_NOT_ENOUGH=new Tuple2<>("20001","余额不够");
        final static public Tuple2<String,String> PLATFOMR_MONEY_NOT_ENOUGH=new Tuple2<>("20002","币不够");
        final static public Tuple2<String,String> GOODS_NOT_ENOUGH=new Tuple2<>("20002","存货不足");
        final static public Tuple2<String,String> ORDER_STATUS_CHANGE=new Tuple2<>("20003","操作失败");
        final static public Tuple2<String,String> NO_USERS=new Tuple2<>("20004","没有用户账户");
        final static public Tuple2<String,String> NO_ORDERS=new Tuple2<>("20005","没有订单");
        final static public Tuple2<String,String> NO_SPECIFICATIONS=new Tuple2<>("20006","没有物品");
        final static public Tuple2<String,String> ORDERS_REFUND=new Tuple2<>("20007","订单已退款");
        final static public Tuple2<String,String> ORDERS_REFUND_FINISHED=new Tuple2<>("20006","订单退款已完成");
        final static public Tuple2<String,String> ORDERS_SENDED=new Tuple2<>("20008","订单已发货");
        final static public Tuple2<String,String> NO_ORDERS_CODE=new Tuple2<>("20009","没有订单号");
        final static public Tuple2<String,String> ORDERS_NO_SENDED=new Tuple2<>("20010","卖家还没发货");
        final static public Tuple2<String,String> ORDERS_COMPLETED=new Tuple2<>("20011","交易已完成");
        final static public Tuple2<String,String> REDPACK_DONE=new Tuple2<>("20012","红包已取完");
        final static public Tuple2<String,String> REDPACK_NOT_EXISTS=new Tuple2<>("20013","没有红包");
        final static public Tuple2<String,String> WEIXIN_FAILURE=new Tuple2<>("20014","微信支付失败");
        final static public Tuple2<String,String> ZHIFUBAO_FAILURE=new Tuple2<>("20015","支付宝支付失败");
        final static public Tuple2<String,String> NO_CODE=new Tuple2<>("20016","没有订单号");
        final static public Tuple2<String,String> NOT__ENOUGH_USERS_MONEY=new Tuple2<>("20017","用户余额不足");
        final static public Tuple2<String,String> ZHIFUBAO_WITHDRAW_FAILURE=new Tuple2<>("20018","支付宝提现失败");
        final static public Tuple2<String,String> WEIXIN_WITHDRAW_FAILURE=new Tuple2<>("20019","微信提现失败");
        final static public Tuple2<String,String> NO_USER_BALANCE=new Tuple2<>("20020","没有用户账户");
        final static public Tuple2<String,String> SELLER_CANNOT_TAKE_RED_PACK=new Tuple2<>("20021","自己不能抢自己的红包");
        final static public Tuple2<String,String> USER_TAKED_RED_PACK=new Tuple2<>("20022","你已经抢过该红包");
        final static public Tuple2<String,String> NO_PARAMETERS=new Tuple2<>("20023","缺少参数");
        final static public Tuple2<String,String> PHOTO_UPLOAD_FAILED=new Tuple2<>("20024","文件上次失败");
        final static public Tuple2<String,String> PLATFORM_MONEY_ZERO=new Tuple2<>("20025","点币为零");
        final static public Tuple2<String,String> IS_SENDED_REDPACK=new Tuple2<>("20026","已发过红包");
        final static public Tuple2<String,String> IS_STARTED_SENDED_REDPACK=new Tuple2<>("20027","还未开始发红包");
        final static public Tuple2<String,String> SYSTEM_ERROR=new Tuple2<>("20028","系统失败");

        final static public Tuple2<String,String> USER_NO_BUYED=new Tuple2<>("20029","没有购买不能评论");
        final static public Tuple2<String,String> INSERT_FAILED=new Tuple2<>("20030","插入失败");
        final static public Tuple2<String,String> NUMBER_GREATER_MONEY=new Tuple2<>("20031","发放数量不能大于点币");
        final static public Tuple2<String,String> NOT_BINDING_ZHIFUBAO=new Tuple2<>("20032","没有绑定支付宝");
        final static public Tuple2<String,String> NOT_BINDING_WEIXIN=new Tuple2<>("20033","没有绑定微信");
        final static public Tuple2<String,String> NO_PHONE_NUMBER=new Tuple2<>("20034","没有手机号码");
        final static public Tuple2<String,String> SEND_SMS_FAILED=new Tuple2<>("20035","短信发送失败");
        final static public Tuple2<String,String> NO_PHONE_NUMBER_AND_CODE=new Tuple2<>("20036","没有手机号码和验证码");
        final static public Tuple2<String,String> ERROR_NUMBER=new Tuple2<>("20037","验证码错误");
        final static public Tuple2<String,String> DEPOSIT_ERROR=new Tuple2<>("20038","提现失败");
        final static public Tuple2<String,String> DEPOSIT_USER_ERROR=new Tuple2<>("20039","账号与用户名不一致");

        final static public Tuple2<String,String> NO_PHONE_NULL=new Tuple2<>("20040","不能为空");
        final static public Tuple2<String,String> NO_PHONEORVERIFY_NULL=new Tuple2<>("20041","手机号或验证码不能为空");
        final static public Tuple2<String,String> NO_VERIFY=new Tuple2<>("20042","没有对应的验证码");
        final static public Tuple2<String,String> ERROR_VERIFY=new Tuple2<>("20043","错误的验证码");

        final static public Tuple2<String,String> ADD_FAILURE=new Tuple2<>("20044","添加失败");

        final static public Tuple2<String,String> PAY_PASSWORD_ERROR=new Tuple2<>("20045","密码错误");

        final static public Tuple2<String,String> LOGIN_PASSWORD_ERROR=new Tuple2<>("43990","没有注册，先去注册吧");
        final static public Tuple2<String,String> NOT_ENROL=new Tuple2<>("43990","没有注册，先去注册吧");
        final static public Tuple2<String,String> VERIFY_ERROR=new Tuple2<>("20051","验证码错误");
        final static public Tuple2<String,String> ALREADY_ENROL=new Tuple2<>("20050","手机号已经注册");
        final static public Tuple2<String,String> NOT_SHOP=new Tuple2<>("20052","店铺不存在");
        final static public Tuple2<String,String> NOT_NULL=new Tuple2<>("20053","参数不能为空");
        final static public Tuple2<String,String> IS_EXIST=new Tuple2<>("20054","店铺名已存在");
        final static public Tuple2<String,String> NAME_NOT_NULL=new Tuple2<>("20053","名称不能为空");
        final static public Tuple2<String,String> NOT_EXIST=new Tuple2<>("20054","商店不存在");
        final static public Tuple2<String,String> NOT_PERMISSION=new Tuple2<>("20055","没有该认证信息");
        final static public Tuple2<String,String> NOT_USERID=new Tuple2<>("20056","用户id不能为空");
        final static public Tuple2<String,String> ALREADY_AUTHEN=new Tuple2<>("20056","该用户已认证过");
        final static public Tuple2<String,String> NOT_USER=new Tuple2<>("20057","用户不存在");
        final static public Tuple2<String,String> NOT_GOODS=new Tuple2<>("20058","商品不存在");
        final static public Tuple2<String,String> ALREADY_SOlDOUT=new Tuple2<>("20059","商品已下架");
        final static public Tuple2<String,String> ALREADY_PUTAWAY=new Tuple2<>("20060","商品已上架");
        final static public Tuple2<String,String> NOT_PARENTID=new Tuple2<>("20061","parentId不能为空");
        final static public Tuple2<String,String> NOT_DATETIME=new Tuple2<>("20062","时间不能为空");
        final static public Tuple2<String,String> NOT_REDPACK=new Tuple2<>("20063","您好像暂未发有红包哦");
        final static public Tuple2<String,String> NOT_REDPACKLOG=new Tuple2<>("20064","暂未有人领红包，不能排名");
        final static public Tuple2<String,String> NOT_AUTHENTICATION=new Tuple2<>("20065","暂未认证");
        final static public Tuple2<String,String> NOT_ID=new Tuple2<>("20066","id不能为空");
        final static public Tuple2<String,String> NOT_PHONE=new Tuple2<>("20067","手机号不能为空");
        final static public Tuple2<String,String> ERROR_PWD=new Tuple2<>("20068","用户名或密码错误");

        final static public Tuple2<String,String> NOT_PWD=new Tuple2<>("20069","密码不能为空");
        final static public Tuple2<String,String> NOT_TYPE=new Tuple2<>("20070","类型不能为空");
        final static public Tuple2<String,String> NOT_IMG=new Tuple2<>("20071","请输入图片名称");
        final static public Tuple2<String,String> NOT_IMAGE=new Tuple2<>("20072","没有该图片");
        final static public Tuple2<String,String> FORMAT_WRONG=new Tuple2<>("20073","手机号格式有误");
        final static public Tuple2<String,String> NOT_SHOPID=new Tuple2<>("20074","店铺id不能为空");
        final static public Tuple2<String,String> NOT_SPE=new Tuple2<>("20075","商品id不能为空");
        final static public Tuple2<String,String> NOT_SPECIFICATION=new Tuple2<>("20076","商品不能为空");
        final static public Tuple2<String,String> NOT_COUPON=new Tuple2<>("20077","优惠券id不能为空");
        final static public Tuple2<String,String> NOT_ADDRESS=new Tuple2<>("20078","地址id不能为空");
        final static public Tuple2<String,String> NOT_ADDRESSPO=new Tuple2<>("20078","没有该地址");

        final static public Tuple2<String,String> NOT_SHOPINGCART=new Tuple2<>("20079","没有该购物车");
        final static public Tuple2<String,String> NOT_SIZE=new Tuple2<>("20080","没有该尺寸");
    }
}
