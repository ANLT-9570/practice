package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.AlipayApiException;
import com.dg.main.serviceImpl.orders.BaseProccess;

import java.io.IOException;

public class OrdersPayedCreateProccess extends BaseProccess {
    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void action() throws IOException, AlipayApiException {

    }

    @Override
    public void notifyChange() {

    }
//    private IPay iPay;
//    private final OrdersWrapper ordersWrapper;
//    private OrdersPayedCreateService ordersPayedCreateService;
////    private OrdersService ordersService;
////    private CompanyBalanceService companyBalanceService;
////    private UserBalanceService userBalanceService;
////    private UserMoneyStreamLogService userMoneyStreamLogService;
////    private CompanyMoneyStreamLogService companyMoneyStreamLogService;
////    private OrderRefundLogService orderRefundLogService;
////    private OrderStreamLogService orderStreamLogService;
////    private SpecificationsServer specificationsServer;
//    public final static Function<Specifications,Function<Orders,Boolean>> checkSpecificationStore=specification->orders -> {
////        if(specification.getNumber()-orders.getNumber()<0){
////            return false;
////        }
//        return true;
//    };
//    public final static Function<UserBalance,Function<Orders,Boolean>> checkUserMoney=userBalance->orders -> {
////      if(userBalance.getMoney()<orders.getMoney()){
////          return false;
////      }
//      return true;
//    };
//    public final static Function<UserBalance,Function<Orders,Boolean>> checkUserPlatformMoney=userBalance->orders->{
////        if(userBalance.getPlatformMoney()<orders.getPlatformMoney()){
////            return false;
////        }
//        return true;
//    };
//    public OrdersPayedCreateProccess(OrdersWrapper ordersWrapper, OrdersService ordersService,
//                                CompanyBalanceService companyBalanceService, UserBalanceService userBalanceService,
//                                UserMoneyStreamLogService userMoneyStreamLogService,
//                                CompanyMoneyStreamLogService companyMoneyStreamLogService,
//                                OrderRefundLogService orderRefundLogService,
//                                OrderStreamLogService orderStreamLogService,
//                                     SpecificationsServer specificationsServer,OrdersPayedCreateService ordersPayedCreateService) {
//        this.ordersWrapper = ordersWrapper;
////        this.ordersService = ordersService;
////        this.companyBalanceService = companyBalanceService;
////        this.userBalanceService = userBalanceService;
////        this.userMoneyStreamLogService = userMoneyStreamLogService;
////        this.companyMoneyStreamLogService = companyMoneyStreamLogService;
////        this.orderRefundLogService = orderRefundLogService;
////        this.orderStreamLogService=orderStreamLogService;
////        this.specificationsServer=specificationsServer;
//        this.ordersPayedCreateService=ordersPayedCreateService;
//    }
//
//    public void setiPay(IPay iPay) {
//        this.iPay = iPay;
//    }
//
//    @Override
//    @Transactional
//    public boolean validate() {
//        if(!checkSpecificationStore.apply(ordersWrapper.getSpecifications()).apply(ordersWrapper.getOrders())){
//            exceptions.add(new GoodsNotEnoughException(ExceptionCode.Failure.GOODS_NOT_ENOUGH));
//            return false;
//        }
//        if(!checkUserMoney.apply(ordersWrapper.getUserBalance()).apply(ordersWrapper.getOrders())){
//            exceptions.add(new MoneyNotEnoughException(ExceptionCode.Failure.MONEY_NOT_ENOUGH));
//            return false;
//        }
//        if(!checkUserPlatformMoney.apply(ordersWrapper.getUserBalance()).apply(ordersWrapper.getOrders())){
//            exceptions.add(new PlatformMoneyNotEnoughException(ExceptionCode.Failure.PLATFOMR_MONEY_NOT_ENOUGH));
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void action() {
//        if(iPay!=null){
//            try {
//                ordersPayedCreateService.save(ordersWrapper);
//                iPay.pay();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//
//    @Override
//    public void notifyChange() {
////        if(iPay!=null){
////            iPay.pay();
////        }
//    }
}
