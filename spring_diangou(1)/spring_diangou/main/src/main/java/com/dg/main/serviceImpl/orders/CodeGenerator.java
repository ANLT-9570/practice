package com.dg.main.serviceImpl.orders;

import com.dg.main.Entity.orders.*;
import com.dg.main.util.DateUtils;

import java.util.concurrent.atomic.AtomicLong;

public class CodeGenerator {
    public static String DEPOSIT_MONEY_CODE_POSTFIX="DMC";
    public static String DEPOSIT_PLATFORM_MONEY_CODE_POSTFIX="DPMC";
    public static String WITHDRAW_MONEY_CODE_POSTFIX="WMC";
    public static String WITHDRAW_PLAMFORM_MONEY_CODE_POSTFIX="WPMC";

    public static String ORDER_CODE_PREFIX="MM";
    public static String MONEY_TRANS_TO_PLATFORM_MONEY="MTPM";
    public static String REDPACK_CODE="RC";
    public static final Long ORDERITEMPRIMES=133L;
    public static final Long ORDERPRIMES=133L;
    public static AtomicLong ORDERITEMCOUNTER=new AtomicLong(1);
    public static AtomicLong ORDERCOUNTER=new AtomicLong(1);
    public static String UNITE_ORDER_CODE_POSTFIX="UOS";
    final public static ICodeGenerator<Object> redpackCode=o -> {
        String code= DateUtils.currentTime()+CodeGenerator.REDPACK_CODE;
        return code;
    };
    final public static ICodeGenerator<Void> uniteOrderStreamCode=i->{
        String code= DateUtils.currentTime()+CodeGenerator.UNITE_ORDER_CODE_POSTFIX;
        return code;
    };
    final public static ICodeGenerator<Orders> thirdCode= orders -> {
        Long number=CodeGenerator.ORDERITEMCOUNTER.getAndIncrement()%CodeGenerator.ORDERITEMPRIMES;
        String numberStr=number.toString();
        String ZeroNumber="";
        if(numberStr.length()==1){
            ZeroNumber="00"+numberStr;
        }else if(numberStr.length()==2){
            ZeroNumber="0"+numberStr;
        }
        String code= CodeGenerator.ORDER_CODE_PREFIX+DateUtils.currentTime()+ZeroNumber+"00";
        System.out.println("--------------code----------------");
        System.out.println(code.length());
        System.out.println("--------------code----------------");
        return code;
    };
    final public static ICodeGenerator<Orders> orderCode=orders -> {
        Long number=CodeGenerator.ORDERCOUNTER.getAndIncrement()%CodeGenerator.ORDERPRIMES;
        String numberStr=number.toString();
        String ZeroNumber="";
        if(numberStr.length()==1){
            ZeroNumber="00"+numberStr;
        }else if(numberStr.length()==2){
            ZeroNumber="0"+numberStr;
        }
        String code= CodeGenerator.ORDER_CODE_PREFIX+DateUtils.currentTime()+ZeroNumber;
        return code;
    };
    final public static ICodeGenerator<MoneyTransToPlatformMoney> moneyTransToPlatformMoneyCode= orders -> {
        String code= DateUtils.currentTime()+CodeGenerator.MONEY_TRANS_TO_PLATFORM_MONEY;
        return code;
    };
    final public static ICodeGenerator<UserWithdrawMoney> withdrawMoneyCode=UserWithdrawMoney->{
        String code= DateUtils.currentTime()+CodeGenerator.WITHDRAW_MONEY_CODE_POSTFIX;
        return code;
    };

    final public static ICodeGenerator<UserDepositMoney> depositMoneyCode= UserWithdrawMoney->{
       // String code=Date
        String code= DateUtils.currentTime()+CodeGenerator.DEPOSIT_MONEY_CODE_POSTFIX;
        return code;
    };
    final public static ICodeGenerator<UserWithdrawPlatformMoney> withdrawPlatformMoneyCode= UserWithdrawPlatformMoney->{
        String code= DateUtils.currentTime()+CodeGenerator.WITHDRAW_PLAMFORM_MONEY_CODE_POSTFIX;
        return code;
    };

    final public static ICodeGenerator<UserDepositPlatformMoney> depositPlatformMoneyCode= UserDepositPlatformMoney->{
        String code= DateUtils.currentTime()+CodeGenerator.DEPOSIT_PLATFORM_MONEY_CODE_POSTFIX;
        return code;
    };
}
