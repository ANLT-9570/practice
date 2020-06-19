package com.dg.main.controller.app.seller.orders;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.service.SpecificationsServer;
import com.dg.main.serviceImpl.logs.CompanyMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.OrderRefundLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.factory.OrderUpdateWrapperFactory;
import com.dg.main.serviceImpl.orders.payment.WeixinRefundPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoRefundPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.service.buyer.OrderUnpayToPayedService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersDeliveryService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersRefundService;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersUnPayedCreateService;
import com.dg.main.serviceImpl.orders.service.seller.OrderBusinessSendedService;
import com.dg.main.serviceImpl.orders.service.seller.SellerAgreeInPhaseTwoService;
import com.dg.main.serviceImpl.orders.service.seller.SellerOrdersService;
import com.dg.main.serviceImpl.orders.update_status.seller.OrderBusinessSendedProccess;
import com.dg.main.serviceImpl.orders.update_status.seller.SellerAgreeInPhaseTwoProcess;
import com.dg.main.serviceImpl.setting.BuyGoodsRateService;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.setting.PlatformRateService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import com.github.wxpay.sdk.WXPayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/v1/sellerOrders")
@Api(tags = "商家-----商品订单")
public class SellerOrdersController {
    @Autowired
    SellerOrdersService sellerOrdersService;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserBalanceService userBalanceService;


    @Autowired
    SpecificationsServer specificationsServer;
    @Autowired
    OrdersPayedCreateService ordersPayedCreateService;
    @Autowired
    OrderUnpayToPayedService orderUnpayToPayedService;

    @Autowired
    private OrderBusinessSendedService orderBusinessSendedService;


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

    @GetMapping("/findRefundOrdersByShopId")
    @ApiOperation(value = "店铺退货列表",notes = "findRefundOrdersByShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result findRefundOrdersByShopId(@RequestParam Long shopId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return sellerOrdersService.findRefundOrdersByShopId(shopId,offset,limit);
    }
    @GetMapping("/findUnpayOrdersByShopId")
    @ApiOperation(value = "店铺未付款列表",notes = "findUnpayOrdersByShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result findUnpayOrdersByShopId(@RequestParam Long shopId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return sellerOrdersService.findUnpayOrdersByShopId(shopId,offset,limit);
    }
    @GetMapping("/findUnsendOrdersByShopId")
    @ApiOperation(value = "店铺待发货列表",notes = "findUnsendOrdersByShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result findUnsendOrdersByShopId(@RequestParam Long shopId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return sellerOrdersService.findUnsendOrdersByShopId(shopId,offset,limit);
    }
    @GetMapping("/findUndeliveryOrdersByShopId")
   // @GetMapping("/waitForReceShops")
    @ApiOperation(value = "店铺待收货",notes = "findUndeliveryOrdersByShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result findUndeliveryOrdersByShopId(@RequestParam Long shopId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return sellerOrdersService.findUndeliveryOrdersByShopId(shopId,offset,limit);
    }


    @PostMapping("/sendOrders")//发货
    @ApiOperation(value = "卖家发货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单"),
            @ApiImplicitParam(name = "logisticsType",value = "物流类型"),
            @ApiImplicitParam(name = "logisticsCode",value = "物流编号")
    })
    public Result sendOrders(String code, String logisticsType, String logisticsCode) throws Exception {
        if(code==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        Orders orders=ordersService.findByCodeV1(code);
        if(orders==null){
            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);

        }
        List<OrderItems> orderItems=orderItemsService.findByOrderId(orders.getId());
        UserBalance userBalance=userBalanceService.findByUserId(orders.getCustomerId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);

        }

        BaseProccess proccess=new OrderBusinessSendedProccess(OrderUpdateWrapperFactory.setSellerLogistics(logisticsType,logisticsCode,orders,orderItems),orderBusinessSendedService);
        proccess.exec();
        if(proccess.getException().size()==0){
            return Result.successResult("");
        }else{
            return Result.failureResult(proccess.getException().get(0));
        }
    }
    @PostMapping("/agreeInPhaseTwo")//// type=1 同意 type=2 不同意
    @ApiOperation(value = "商家是否同意")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单"),
            @ApiImplicitParam(name = "type",value = "类型"),
    })
    public Result agreeInPhaseTwo(String code,Integer type)throws Exception{
        if(code==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        Orders orders=ordersService.findByCodeV1(code);
        if(orders==null){
            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);

        }
        List<OrderItems> orderItems=orderItemsService.findByOrderId(orders.getId());
        UserBalance userBalance=userBalanceService.findByUserId(orders.getCustomerId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);

        }
    //    orders.setIsSellerAgreeInPhaseTwo(type);
        BaseProccess proccess=new SellerAgreeInPhaseTwoProcess(OrderUpdateWrapperFactory.setSellerAggree(type,orders,orderItems),sellerAgreeInPhaseTwoService);
        if(orders.getThirdPlatformAction()==3){
            ((SellerAgreeInPhaseTwoProcess)proccess).setiPay(new ZhifubaoRefundPay());
        }else if(orders.getThirdPlatformAction()==4){
            ((SellerAgreeInPhaseTwoProcess)proccess).setiPay(new WeixinRefundPay(config));
        }
        proccess.exec();
        if(proccess.getException().size()==0){
            return Result.successResult("");
        }else{
            return Result.failureResult(proccess.getException().get(0));
        }

    }



}
