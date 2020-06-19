package com.dg.main.serviceImpl.orders.service.buyer;

import org.springframework.stereotype.Service;

@Service
public class OrderUnpayToPayedService {
//    @Autowired
//    private UserBalanceService userBalanceService;
//    @Autowired
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    @Autowired
//    private OrdersService ordersService;
//    @Autowired
//    private OrderStreamLogService orderStreamLogService;
//    @Transactional
//    public void action(OrdersWrapper ordersWrapper){
//        Orders _old=ordersWrapper.getOrders();
//        Orders _new= OrderFactory.newInstance(_old);
//        UserBalance oldBalance=ordersWrapper.getUserBalance();
//        UserBalance newBalance= UserBalanceFactory.newInstance(oldBalance);
//      //  _new.setPhase(2);
////        _new.setModifyTime(new Timestamp(new Date().getTime()));
////        if(_old.getThirdPlatformAction()==1){
////            newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);
////            newBalance.setModifyTime(new Timestamp(new Date().getTime()));
////            newBalance.setMoney(oldBalance.getMoney()-_old.getMoney());
////
////        }else if(_old.getThirdPlatformAction()==2){
////            newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);
////            newBalance.setModifyTime(new Timestamp(new Date().getTime()));
////            newBalance.setPlatformMoney(oldBalance.getPlatformMoney()-_old.getPlatformMoney());
////        }
//        ordersService.update(_new);
//        userBalanceService.update(newBalance);
//        orderStreamLogService.saveOrders(_old,_new);
//        userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance,"OrderUnpayToPayedService","action");
//    }
//    @Transactional
//    public void actionList(ListOrdersWrapper listOrdersWrapper){
//
//    }

}
