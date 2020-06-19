package com.dg.main.controller.app.common;

import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.serviceImpl.logs.CompanyMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.OrderRefundLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;

import com.dg.main.serviceImpl.orders.service.buyer.OrderUnpayToPayedService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersDeliveryService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersRefundService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersUnPayedCreateService;
import com.dg.main.serviceImpl.orders.service.seller.OrderBusinessSendedService;
import com.dg.main.serviceImpl.orders.service.seller.SellerAgreeInPhaseTwoService;
import com.dg.main.serviceImpl.setting.BuyGoodsRateService;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.setting.PlatformRateService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import com.github.wxpay.sdk.WXPayConfig;

import com.dg.main.service.SpecificationsServer;

import com.dg.main.serviceImpl.orders.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/v1/orders")
@Api(value = "通用-商品订单")
public class CommonOrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CompanyBalanceService companyBalanceService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private CompanyMoneyStreamLogService companyMoneyStreamLogService;
    @Autowired
    private OrderRefundLogService orderRefundLogService;
    @Autowired
    private OrderStreamLogService orderStreamLogService;

    @Autowired
    SpecificationsServer specificationsServer;
    @Autowired
    OrdersPayedCreateService ordersPayedCreateService;
    @Autowired
    OrderUnpayToPayedService orderUnpayToPayedService;
    @Autowired
    private OrdersRefundService ordersRefundService;
    @Autowired
    private OrdersDeliveryService ordersDeliveryService;
    @Autowired
    private OrderBusinessSendedService orderBusinessSendedService;
    @Autowired
    private OrdersUnPayedCreateService ordersUnPayedCreateService;

    @Autowired
    CustomerAddressRepository repository;
    @Autowired
    MoneyPayService moneyPayService;
    @Autowired
    PlatformPayService platformPayService;

    @Autowired
    PlatformRateService platformRateService;

    @Autowired
    WXPayConfig config;
    @Autowired
    BuyGoodsRateService buyGoodsRateService;
    @Autowired
    MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateService;
    @Autowired
    SellerAgreeInPhaseTwoService sellerAgreeInPhaseTwoService;
    @Autowired
    LogisticsStateService logisticsStateService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    CreateOrdersByItemCodeService createOrdersByItemCodeService;



//    @GetMapping("/getUserUnpayedOrders")
//    @ApiOperation(value = "根据用户id查询未支付订单",notes = "getUserUnpayedOrders")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "条件"),
//            @ApiImplicitParam(name = "offset",value = "起始页"),
//            @ApiImplicitParam(name = "limit",value = "每页显示数量")
//    })
//    public Result getUserUnpayedOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        return ordersService.getUserUnPayedOrderList(userId);
//
//    }
//    @GetMapping("/getUserUnsendOrders")
//    @ApiOperation(value = "根据用户id查询未发货订单",notes = "getUserUnsendOrders")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "条件"),
//            @ApiImplicitParam(name = "offset",value = "起始页"),
//            @ApiImplicitParam(name = "limit",value = "每页显示数量")
//    })
//    public Result getUserUnsendOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        return ordersService.getUserUnsendOrderList(userId);
//
//    }
//    @GetMapping("/getUserUndeliveryOrders")
//    @ApiOperation(value = "根据用户id查询未交付订单",notes = "getUserUndeliveryOrders")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "条件"),
//            @ApiImplicitParam(name = "offset",value = "起始页"),
//            @ApiImplicitParam(name = "limit",value = "每页显示数量")
//    })
//    public Result getUserUndeliveryOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        return ordersService.getUserUndeliveryOrderList(userId);
//
//    }
//    @GetMapping("/getUserUnrankOrders")
//    @ApiOperation(value = "根据用户id查询排名订单",notes = "getUserUnrankOrders")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "条件"),
//            @ApiImplicitParam(name = "offset",value = "起始页"),
//            @ApiImplicitParam(name = "limit",value = "每页显示数量")
//    })
//    public Result getUserUnrankOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        return ordersService.getUserUncommentOrders(userId);
//    }
//    @PostMapping("/addRank")
//    @ApiOperation(value = "添加",notes = "addRank")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code",value = "订单号"),
//            @ApiImplicitParam(name = "info",value = "信息"),
//            @ApiImplicitParam(name = "rank",value = "排名")
//    })
//    public Result addRank(String code,String info,Integer rank){
//        return orderItemsService.addRank(code,info,rank);
//    }
//    @GetMapping("/commentList")
//    @ApiOperation(value = "根据商品id查询",notes = "commentList")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "specificationId",value = "商品id"),
//    })
//    public Result commentList(Long specificationId){
//        return orderItemsService.commentList(specificationId);
//    }

//    @RequestMapping("/customerGetByType")// type:1 //未付款 2://已付款 3://付款中 10:完成 买家
//    @ApiOperation(value = "获取客户订单列表")
//    public Result customerGetByType(Integer userId,Integer type){
//        if(userId==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        if(type==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        return Result.successResult(ordersService.customerList(userId,type));
//    }
//    @RequestMapping("/businessGetByType")
//    @ApiOperation(value = "获取商家订单列表")
//    public Result businessGetByType(Integer userId,Integer type){
//        if(userId==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        if(type==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        return Result.successResult(ordersService.businessList(userId,type));
//    }

//    @RequestMapping("/refundOrdersInPhaseTen")//七天退款
//    @ApiOperation(value = "七天退款")
//    public Result refundOrdersInPhaseTen(String code,String reason) throws Exception {
//        if(code==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        Orders orders=ordersService.findByCode(code);
//        if(orders==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserBalance userBalance=userBalanceService.findByUserId(orders.getBusinessId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USER_BALANCE);
//        }
//        if(orders.getThirdPlatformAction()==2){
//            if(userBalance.getPlatformMoney()<orders.getPlatformMoney()){
//                return Result.failureResult(ExceptionCode.Failure.NOT__ENOUGH_USERS_MONEY);
//            }
//        }else{
//            if(userBalance.getMoney()<orders.getPayMoney()){
//                return Result.failureResult(ExceptionCode.Failure.NOT__ENOUGH_USERS_MONEY);
//            }
//        }
//        orders.setRefundReason(reason);
//        BaseProccess baseProccess=new RefundOrdersInPhaseTenProccess(OrdersRefundWrapperFactory.newInstance(userBalance,orders),refundOrdersInPhaseTenService);
//        baseProccess.exec();
//
//        if(baseProccess.getException().size()==0){
//            return Result.successResult("");
//        }else{
//            return Result.failureResult(baseProccess.getException().get(0));
//        }
//    }
//    @RequestMapping("/buyerSendedInPhaseTen")////七天退款--买家发货
//    @ApiOperation(value = "七天退款--买家发货")
//    public Result buyerSendedInPhaseTen(String code) throws Exception {
//        if(code==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        Orders orders=ordersService.findByCode(code);
//        if(orders==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserBalance userBalance=userBalanceService.findByUserId(orders.getCustomerId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USER_BALANCE);
//        }
//        BaseProccess baseProccess=new BuyerSendedInPhaseTenProccess(OrdersRefundWrapperFactory.newInstance(userBalance,orders),buyerSendedInPhaseTenService);
//        baseProccess.exec();
//
//        if(baseProccess.getException().size()==0){
//            return Result.successResult("");
//        }else{
//            return Result.failureResult(baseProccess.getException().get(0));
//        }
//    }
//    @RequestMapping("/sellerDeliveriedInPhaseTen")////七天退款--卖家收货
//    @ApiOperation(value = "七天退款--卖家收货")
//    public Result sellerDeliveriedInPhaseTen(String code) throws Exception {
//        if(code==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        Orders orders=ordersService.findByCode(code);
//        if(orders==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserBalance userBalance=userBalanceService.findByUserId(orders.getCustomerId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USER_BALANCE);
//        }
//        BaseProccess baseProccess=new SellerDeliveriedInPhaseTenProccess(OrdersRefundWrapperFactory.newInstance(userBalance,orders),sellerDeliveriedInPhaseTenService);
//        baseProccess.exec();
//
//        if(baseProccess.getException().size()==0){
//            return Result.successResult("");
//        }else{
//            return Result.failureResult(baseProccess.getException().get(0));
//        }
//    }
//
    @DeleteMapping("/deletedById")
    @ApiOperation(value = "根据id删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id")
    })
    public Result deletedById(Long id){
        ordersService.fakeDeleteOrder(id);
        return Result.successResult();
    }
//    @DeleteMapping("/deletedByCode")
//    @ApiOperation(value = "根据订单号删除订单")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code",value = "订单")
//    })
//    public Result deletedByCode(List<String> code){
//        ordersService.fakeDeletedByCode(code);
//        return Result.successResult();
//    }
//    @RequestMapping("/agreeInPhaseTen")////七天退款--卖家是否同意 type=1 同意 type=2 不同意
//    @ApiOperation(value = "七天退款,卖家是否同意")
//    public Result agreeInPhaseTen(String code,Integer type)throws Exception{
//        if(code==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//
//        if(type==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
//        Orders orders=ordersService.findByCode(code);
//        if(orders==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        orders.setIsSellerAgreeInPhaseTen(type);
//        BaseProccess proccess=new SellerAgreeInPhaseTenProcess(OrderBusinessSendedWrapperFactory.newInstance(orders),sellerAgreeInPhaseTenService);
//        proccess.exec();
//        if(proccess.getException().size()==0){
//            return Result.successResult("");
//        }else{
//            return Result.failureResult(proccess.getException().get(0));
//        }
//
//    }




}
