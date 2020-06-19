package com.dg.main.serviceImpl.orders.service;

import org.springframework.stereotype.Service;

@Service
public class OrdersPayedCreateService {
//    @Autowired
//    private OrdersService ordersService;
//    @Autowired
//    private CompanyBalanceService companyBalanceService;
//    @Autowired
//    private UserBalanceService userBalanceService;
//    @Autowired
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    @Autowired
//    private CompanyMoneyStreamLogService companyMoneyStreamLogService;
//    @Autowired
//    private OrderRefundLogService orderRefundLogService;
//    @Autowired
//    private OrderStreamLogService orderStreamLogService;
//    @Autowired
//    private SpecificationsServer specificationsServer;
//    @Transactional
//    public void save(OrdersWrapper ordersWrapper){
//     //   UserBalance oldObj=ordersWrapper.getUserBalance();
//        UserBalance newObj= UserBalanceFactory.newInstance(oldObj);
//        Orders orders=ordersWrapper.getOrders();
//        Orders newOrders= OrderFactory.newInstance(orders);
//        newObj.setModifyTime(new Date());
//        newObj.setOperationTimes(oldObj.getOperationTimes()+1);
////        if(orders.getThirdPlatformAction()==1){
////            newObj.setMoney(oldObj.getMoney()-orders.getMoney());
////        }else if(orders.getThirdPlatformAction()==2){
////            newObj.setPlatformMoney(oldObj.getPlatformMoney()-orders.getPlatformMoney());
////        }
////
////        newOrders.setPhase(2);
////        newOrders.setModifyTime(new Timestamp(new Date().getTime()));
//
//        Specifications specifications=ordersWrapper.getSpecifications();
//        specifications.setNumber(specifications.getNumber()-1);
//
//        specificationsServer.update(specifications);
//        userBalanceService.update(newObj);
//        userMoneyStreamLogService.saveUserBalances(oldObj,newObj,"OrdersPayedCreateService","save");
//        orderStreamLogService.saveOrders(orders,newOrders);
//
//        ordersService.save(newOrders);
//        orderRefundLogService.save(ordersWrapper.getOrderRefundLog());
//
//    }
}
