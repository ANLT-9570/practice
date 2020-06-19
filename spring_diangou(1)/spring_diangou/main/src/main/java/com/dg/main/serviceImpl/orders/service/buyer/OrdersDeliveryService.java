package com.dg.main.serviceImpl.orders.service.buyer;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import com.dg.main.Entity.logs.CompanyMoneyStreamLog;
import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.logs.SellerBuyUseThirdPayLog;
import com.dg.main.Entity.settings.CompanyBalance;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.logs.*;
import com.dg.main.serviceImpl.notification.JGSenderParam;
import com.dg.main.serviceImpl.notification.notification.JGNotificationService;
import com.dg.main.serviceImpl.orders.service.OrderItemsService;
import com.dg.main.serviceImpl.orders.service.OrdersService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import com.dg.main.Entity.User;
import com.dg.main.Entity.orders.*;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;

import jodd.util.ArraysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrdersDeliveryService {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private CompanyBalanceService companyBalanceService;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    CompanyMoneyStreamLogService companyMoneyStreamLogService;
    @Autowired
    CompanyDailyStreamLogService companyDailyStreamLogService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    SellerBuyUseThirdPayLogService sellerBuyUseThirdPayLogService;
    @Autowired
    JGNotificationService notificationService;
    @Transactional
    public void action(OrderUpdateWrapper wrapper){
        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        String users[]={};
        // Orders newOrder= OrderFactory.newInstance(oldOrder);
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();
        if(oldOrder!=null){
            ArraysUtil.append(users, JGSenderParam.PREVIOUS+oldOrder.getBusinessId());
           // Orders newOrder= OrderFactory.newInstance(oldOrder);
            UserBalance _oldBalance=userBalanceService.findByUserId(oldOrder.getCustomerId());
            UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
            CompanyBalance _oldCompanyBalance=companyBalanceService.getBalance();
            CompanyBalance _newCompanyBalance=companyBalanceService.getBalance();
            CompanyMoneyStreamLog companyMoneyStreamLog=new CompanyMoneyStreamLog();
            companyMoneyStreamLog.setStatus(1);
            companyMoneyStreamLog.setTypes(1);
            companyMoneyStreamLog.setBuyerId(oldOrder.getCustomerId());
            companyMoneyStreamLog.setSellerId(oldOrder.getBusinessId());
            companyMoneyStreamLog.setCode(oldOrder.getOrderCode());
            CompanyDailyStreamLog companyDailyStreamLog=new CompanyDailyStreamLog();
            LocalDateTime localDateTime=LocalDateTime.now();
            companyDailyStreamLog.setDayOfyear(localDateTime.getDayOfYear());
            companyDailyStreamLog.setMonth(localDateTime.getMonthValue());
            companyDailyStreamLog.setYears(localDateTime.getYear());
             SellerBuyUseThirdPayLog sellerBuyUseThirdPayLog=new SellerBuyUseThirdPayLog();
            sellerBuyUseThirdPayLog.setDayOfyear(localDateTime.getDayOfYear());
            sellerBuyUseThirdPayLog.setYears(localDateTime.getYear());
            sellerBuyUseThirdPayLog.setMonths(localDateTime.getMonthValue());
            oldOrder.setPhase(10);

            oldOrder.setModifyTime(new Timestamp(new Date().getTime()));

            if(oldOrder.getThirdPlatformAction()==2){
            _newBalance.setMoney(_oldBalance.getMoney()+oldOrder.getTotalMoney());
            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));


            Long platformMoney=oldOrder.getTotalPlatformMoney();
            Long multiMoney=new BigDecimal(platformMoney).multiply(new BigDecimal(1.3)).longValue();
            _newCompanyBalance.setPlatformMoney(_oldCompanyBalance.getPlatformMoney()+oldOrder.getTotalCompanyIncome());

        }else if(oldOrder.getThirdPlatformAction()==3){
            _newBalance.setMoney(_oldBalance.getMoney() + oldOrder.getTotalSellerIncome());

            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));

            _newCompanyBalance.setMoney(_oldCompanyBalance.getMoney()+oldOrder.getTotalCompanyIncome());
            _newCompanyBalance.setZhifubaoMoney(_oldCompanyBalance.getZhifubaoMoney()+oldOrder.getTotalThirdIncome());

            companyMoneyStreamLog.setMoney(oldOrder.getTotalMoney());
            companyMoneyStreamLog.setStatus(1);
            sellerBuyUseThirdPayLog.setIncome(oldOrder.getTotalThirdIncome());
            sellerBuyUseThirdPayLog.setZhifubaoIncome(oldOrder.getTotalThirdIncome());
            sellerBuyUseThirdPayLogService.save(sellerBuyUseThirdPayLog);
        }else if(oldOrder.getThirdPlatformAction()==4){
            _newBalance.setMoney(_oldBalance.getMoney() + oldOrder.getTotalSellerIncome());
//
            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));

            _newCompanyBalance.setMoney(_oldCompanyBalance.getMoney()+oldOrder.getTotalCompanyIncome());
            _newCompanyBalance.setWeixinMoney(_oldCompanyBalance.getWeixinMoney()+oldOrder.getTotalThirdIncome());
            companyMoneyStreamLog.setMoney(oldOrder.getTotalCompanyIncome());
            companyMoneyStreamLog.setStatus(2);
            sellerBuyUseThirdPayLog.setIncome(oldOrder.getTotalThirdIncome());
            sellerBuyUseThirdPayLog.setWeixinIncome(oldOrder.getTotalThirdIncome());
            sellerBuyUseThirdPayLogService.save(sellerBuyUseThirdPayLog);
             }
            ordersService.update(oldOrder);
            userBalanceService.update(_newBalance);
            companyBalanceService.update(_newCompanyBalance);
            switch (oldOrder.getThirdPlatformAction()){
                case 1:
                    userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
                            UserStreamEnum.SELLER_MONEY_GET_ORDERS.getIndex(),oldOrder.getOrderCode());
                    break;
                case 2:
                    userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
                            UserStreamEnum.SELLER_PLATFORM_FORM_GET_ORDERS.getIndex(),oldOrder.getOrderCode());
                    break;
                case 3:
                    userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
                            UserStreamEnum.SELLER_ZHIFUBAO_GET_ORDERS.getIndex(),oldOrder.getOrderCode());
                    break;
                case 4:
                    userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"OrdersDeliveryService","action",
                            UserStreamEnum.SELLER_WEIXIN_GET_ORDER.getIndex(),oldOrder.getOrderCode());
                    break;
            }

            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=newOrderItems.get(i);

                orderItemsService.update(items);
                OrderItemsLog log=new OrderItemsLog();
                log.setBusinessId(items.getSellerId());
                log.setCustomerId(items.getUserId());
                log.setOrderCode(items.getItemCode());
                log.setCurrentStatus(items.getPhase());
                log.setNewStatusOfItem(gson.toJson(items));
                log.setOldStatusOfItem(gson.toJson(oldOrderItems.get(i)));
                orderItemsLogService.save(log);
            }

        }
        System.out.println(users);
        JGSenderParam param=new JGSenderParam();
        param.setUserIds(users);
        param.setTitle("用户已成功收货");
        // param.setContent("用户已买单,请发货");
        notificationService.sendAll(param);

    }
}
