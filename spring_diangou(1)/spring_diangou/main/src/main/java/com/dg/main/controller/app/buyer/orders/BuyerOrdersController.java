package com.dg.main.controller.app.buyer.orders;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.OrdersParam;
import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.service.SpecificationsServer;
import com.dg.main.serviceImpl.logs.CompanyMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.OrderRefundLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.OrdersUnPayedCreateProccess;
import com.dg.main.serviceImpl.orders.factory.OrderUpdateWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.OrdersWrapperFactory;
import com.dg.main.serviceImpl.orders.payment.*;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.orders.service.buyer.*;
import com.dg.main.serviceImpl.orders.service.seller.OrderBusinessSendedService;
import com.dg.main.serviceImpl.orders.service.seller.SellerAgreeInPhaseTwoService;
import com.dg.main.serviceImpl.orders.update_status.buyer.OrderUnpayToPayedProccess;
import com.dg.main.serviceImpl.orders.update_status.buyer.OrdersDeliveryProccess;
import com.dg.main.serviceImpl.orders.update_status.buyer.OrdersRefundProccess;
import com.dg.main.serviceImpl.setting.BuyGoodsRateService;
import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.serviceImpl.setting.PlatformRateService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.trans_mapper_obj.LogisticsObj;
import com.dg.main.util.LogisticsUtils;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;
import com.github.wxpay.sdk.WXPayConfig;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/buyer/v1/buyerOrders")
@Api(tags = "用户-----商品订单")
public class BuyerOrdersController {
    @Autowired
    BuyerOrdersService buyerOrdersService;
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
    private OrdersRefundService ordersRefundService;
    @Autowired
    private OrdersDeliveryService ordersDeliveryService;

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

    @GetMapping("/getUserUnpayedOrders")
    @ApiOperation(value = "根据用户id查询未支付订单",notes = "getUserUnpayedOrders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "条件"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result getUserUnpayedOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return buyerOrdersService.getUserUnPayedOrderList(userId,offset,limit);

    }
    @GetMapping("/getUserUnsendOrders")
    @ApiOperation(value = "根据用户id查询未发货订单",notes = "getUserUnsendOrders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "条件"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result getUserUnsendOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return buyerOrdersService.getUserUnsendOrderList(userId,offset,limit);

    }
    @GetMapping("/getUserUndeliveryOrders")
    @ApiOperation(value = "根据用户id查询未交付订单",notes = "getUserUndeliveryOrders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "条件"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result getUserUndeliveryOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return buyerOrdersService.getUserUndeliveryOrderList(userId,offset,limit);

    }
    @GetMapping("/getUserUnrankOrders")
    @ApiOperation(value = "根据用户id查询排名订单",notes = "getUserUnrankOrders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "条件"),
            @ApiImplicitParam(name = "offset",value = "起始页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result getUserUnrankOrders(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        return buyerOrdersService.getUserUncommentOrders(userId,offset,limit);
    }



       @PostMapping("/addRank")
    @ApiOperation(value = "添加",notes = "addRank")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单号"),
            @ApiImplicitParam(name = "info",value = "信息"),
            @ApiImplicitParam(name = "rank",value = "排名")
    })
    public Result addRank(String code,String info,Integer rank){
        return orderItemsService.addRank(code,info,rank);
    }
    @GetMapping("/commentList")
    @ApiOperation(value = "根据商品id查询",notes = "commentList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specificationId",value = "商品id"),
    })
    public Result commentList(Long specificationId){
        return orderItemsService.commentList(specificationId);
    }
    @DeleteMapping("/deletedByCode")
    @ApiOperation(value = "根据订单号删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单")
    })
    public Result deletedByCode(String code){
       // ordersService.fakeDeletedByCode(code);
        if(code==null){

        }
      return  buyerOrdersService.fakeDelete(code);
       // return Result.successResult();
    }
//    public Result deletedByCode(String code){
//
//    }
    @PostMapping("/refundOrders")//退款
    @ApiOperation(value = "用户退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单"),
            @ApiImplicitParam(name = "reason",value = "退款意见"),
    })
    public Result refundOrders( String code,String reason) throws Exception {
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
        BaseProccess baseProccess=new OrdersRefundProccess(OrderUpdateWrapperFactory.setBuyerReasonCode(reason,orders,orderItems),ordersRefundService);
        if(orders.getThirdPlatformAction()==3&&orders.getIsSinglePay()==1){
            ((OrdersRefundProccess)baseProccess).setiPay(new ZhifubaoRefundPay());
        }else if(orders.getThirdPlatformAction()==4&&orders.getIsSinglePay()==1){
            ((OrdersRefundProccess)baseProccess).setiPay(new WeixinRefundPay(config));
        }
        baseProccess.exec();

        if(baseProccess.getException().size()==0){
            return Result.successResult("");
        }else{
            return Result.failureResult(baseProccess.getException().get(0));
        }
    }
    @PostMapping("/deliveryOrders")//收货
    @ApiOperation(value = "买家收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单编号"),
    })
    public Result deliveryOrders(String code) throws Exception {
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
        BaseProccess baseProccess=new OrdersDeliveryProccess(OrderUpdateWrapperFactory.setBuyerGetPackage(orders,orderItems),
                ordersDeliveryService);

        baseProccess.exec();

        if(baseProccess.getException().size()==0){
            return Result.successResult("");
        }else{
            return Result.failureResult(baseProccess.getException().get(0));
        }
        //  return Result.successResult();
    }
    @GetMapping("/orderInfoByItemCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单")
    })
    public Result orderInfoByItemCode(String code){

        return orderItemsService.getInfo(code);
    }


    @RequestMapping("/queryLogistics")
    @ApiOperation(value = "查询物流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "订单"),
    })
    public Result queryLogistics(String code)throws Exception{
        if(code==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        Orders orders=ordersService.findByCodeV1(code);
        if(orders==null){
            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        LogisticsObj obj=null;
        obj=logisticsStateService.getLogisticsObj(logisticsStateService.findByCode(code));
        Gson gson=new Gson();
        if(obj!=null){

            return Result.successResult(gson.toJson(obj));
        }else{
            LogisticsUtils api = new LogisticsUtils();
            String result = api.getOrderTracesByJson(orders.getLogisticsType(), orders.getLogisticsCode());
            obj=gson.fromJson(result,LogisticsObj.class);
            logisticsStateService.save(obj,code);
            return Result.successResult(result);
        }

    }

    @PostMapping("/unToPayed")//付款
    @ApiOperation(value = "付款")
//    public Result UnToPayed(String code,Long money,Long platformMoney,Integer action) throws Exception {
    public void unToPayed(HttpServletResponse response, HttpServletRequest request) throws Exception {
        Integer action=Integer.valueOf(request.getParameter("action"));
        if(action==null){
            //  return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS));
        }
        String mark=request.getParameter("mark");
        String code=request.getParameter("code");
        if(code==null){
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS));
        }


        Orders orders=ordersService.findByCodeV1(code);
        if(orders==null){
            //  return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_ORDERS));
        }
        List<OrderItems> orderItems=orderItemsService.findByOrderId(orders.getId());

        UserBalance userBalance=userBalanceService.findByUserId(orders.getCustomerId());
        if(userBalance==null){
            //  return Result.failureResult(ExceptionCode.Failure.NO_USERS);
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_USERS));
        }


        BaseProccess proccess=new OrderUnpayToPayedProccess(OrdersWrapperFactory.newInstance(orders,action,orderItems,mark),
                orderUnpayToPayedService,userBalanceService);
        if(action==1){
            ((OrderUnpayToPayedProccess)proccess).setiPay(new MoneyPay(moneyPayService));

        }else if(action==2){
            ((OrderUnpayToPayedProccess)proccess).setiPay(new PlatformPay(platformPayService));
        }else if(action==3){
            ((OrderUnpayToPayedProccess)proccess).setiPay(new ZhifubaoPay());
            ((OrderUnpayToPayedProccess)proccess).setResponse(response);
            ((OrderUnpayToPayedProccess) proccess).setRequest(request);
            ordersService.update(orders);
        }else if(action==4){
            ((OrderUnpayToPayedProccess)proccess).setiPay(new WeixinPay(config));//todo
            ((OrderUnpayToPayedProccess)proccess).setResponse(response);
            ((OrderUnpayToPayedProccess) proccess).setRequest(request);
            ordersService.update(orders);
        }

        proccess.exec();
        if(proccess.getException().size()!=0){
            // return Result.failureResult(proccess.getException().get(0));
            ResponseUtils.send(response,Result.failureResult(proccess.getException().get(0)));
        }
        ResponseUtils.send(response,Result.successResult(""));


        // return Result.successResult("");
    }
    @PostMapping("/unPayedOrders")//未付订单
    @ApiOperation(value = "未付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ordersParam",value = "订单信息"),
    })
    public void unPayedOrders(@RequestBody List<OrdersParam> ordersParam,HttpServletResponse response, HttpServletRequest request) throws Exception {
//        UserBalance userBalance=userBalanceService.findByUserId(ordersParam.getBuyerId());
        if(ordersParam==null || ordersParam.size()==0){
             ResponseUtils.send(response, Result.failureResult((ExceptionCode.Failure.NO_PARAMETERS)));
        }
        Integer isSinglePay=2;
        if (ordersParam.size()==1){
            isSinglePay=1;
        }
        //   Specifications specifications=specificationsServer.findById(ordersParam.getSpecificationId());
        //  specifications.setNumber(2131232l);
//        if(specifications==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
//        }
        Integer action=ordersParam.get(0).getAction();
        BaseProccess baseProccess=new OrdersUnPayedCreateProccess(OrdersWrapperFactory.newInstance(ordersParam,buyGoodsRateService,isSinglePay),
                ordersUnPayedCreateService,userBalanceService);
        if(action==1){
            ((OrdersUnPayedCreateProccess)baseProccess).setiPay(new MoneyPay(moneyPayService));

        }else if(action==2){
            ((OrdersUnPayedCreateProccess)baseProccess).setiPay(new PlatformPay(platformPayService));
        }else if(action==3){
            ((OrdersUnPayedCreateProccess)baseProccess).setiPay(new AppZhifubaoPay());
            ((OrdersUnPayedCreateProccess)baseProccess).setResponse(response);
            ((OrdersUnPayedCreateProccess) baseProccess).setRequest(request);
           // ordersService.update(orders);
        }else if(action==4){
            ((OrdersUnPayedCreateProccess)baseProccess).setiPay(new AppWeinxinPay(config));//todo
            ((OrdersUnPayedCreateProccess)baseProccess).setResponse(response);
            ((OrdersUnPayedCreateProccess) baseProccess).setRequest(request);
          //  ordersService.update(orders);
        }
        baseProccess.exec();
        if(baseProccess.getException().size()!=0){
          //  return Result.failureResult(baseProccess.getException().get(0));
            ResponseUtils.send(response,Result.failureResult(baseProccess.getException().get(0)));
        }
        ResponseUtils.send(response,Result.successResult(""));
//        String code=((OrdersUnPayedCreateProccess)baseProccess).getOrdersWrapper().getOrders().getOrderCode();
//        return Result.successResult(code);
    }
}
