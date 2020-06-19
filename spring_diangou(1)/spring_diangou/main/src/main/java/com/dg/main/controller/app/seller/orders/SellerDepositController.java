package com.dg.main.controller.app.seller.orders;

import com.dg.main.Entity.logs.UserDepositPlatformMoneyLog;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.logs.UserDepositMoneyLogService;
import com.dg.main.serviceImpl.logs.UserDepositPlatformMoneyLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.setting.CompanyWithDrawPlatformMoneyRateService;
import com.dg.main.serviceImpl.setting.PlatformRateService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.github.wxpay.sdk.WXPayConfig;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.DepositPlatformMoneyParam;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.DepositMoneyCreateProccess;
import com.dg.main.serviceImpl.orders.create.DepositPlatformMoneyCreateProccess;
import com.dg.main.serviceImpl.orders.create.MoneyTransToPlatformMoneyProccess;
import com.dg.main.serviceImpl.orders.factory.DepositMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.DepositPlatformMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.MoneyTransToPlatformMoneyV1Factory;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.util.ResponseUtils;
import com.dg.main.util.Result;
import com.dg.main.util.Tuple2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestController
@RequestMapping("/seller/v1/deposit")
@Api(value = "商家-购买点币")
public class SellerDepositController {
    @Autowired
    UserDepositPlatformMoneyService userDepositPlatformMoneyService;
    @Autowired
    UserDepositPlatformMoneyLogService userDepositPlatformMoneyLogService;
    @Autowired
    UserDepositMoneyService userDepositMoneyService;
    @Autowired
    UserDepositMoneyLogService userDepositMoneyLogService;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    private WXPayConfig wxPayConfig;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private DepositPlatformMoneyCreateService depositPlatformMoneyCreateService;
    @Autowired
    private DepositMoneyCreateService depositMoneyCreateService;
    @Autowired
    private MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
    @Autowired
    private PlatformRateService platformRateService;
    @Autowired
    CompanyWithDrawPlatformMoneyRateService companyWithDrawPlatformMoneyRateService;
    @Autowired
    private MoneyTransToPlatformMoneyServiceV1 moneyTransToPlatformMoneyServiceV1;
    @RequestMapping("/depositMoney")
    public void depositMoney(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String code=request.getParameter("code");
        if(code==null){
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_CODE));
        }

        UserDepositMoney userDepositMoney=userDepositMoneyService.findByCode(code);
        if(userDepositMoney==null){
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_CODE));
        }
        UserBalance userBalance=userBalanceService.findByUserId(userDepositMoney.getUserId());
        if(userBalance==null){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Gson gson=new Gson();
            writer.write(gson.toJson(Result.failureResult(ExceptionCode.Failure.NO_USERS)));
            //  return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }

        BaseProccess baseProccess=new DepositMoneyCreateProccess(DepositMoneyWrapperFactory.newInstance(userDepositMoney),
                depositMoneyCreateService);
        if(userDepositMoney.getDirection()==1){
            ((DepositMoneyCreateProccess)baseProccess).setIpay(new ZhifubaoPay());
            ((DepositMoneyCreateProccess)baseProccess).setResponse(response);
            ((DepositMoneyCreateProccess) baseProccess).setRequest(request);
        }else if(userDepositMoney.getDirection()==2){
            ((DepositMoneyCreateProccess)baseProccess).setIpay(new WeixinPay(wxPayConfig));
            ((DepositMoneyCreateProccess)baseProccess).setResponse(response);
            ((DepositMoneyCreateProccess) baseProccess).setRequest(request);
        }else {
            return;
        }
        baseProccess.exec();
        if(baseProccess.getException().size()!=0){
            ResponseUtils.send(response,Result.failureResult(baseProccess.getException().get(0)));
        }
    }
    @GetMapping("/createMoneyTransToPlatformMoney")
    public Result createMoneyTransToPlatformMoney(Long userId,Long money) throws Exception {
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        if(money==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        UserBalance userBalance=userBalanceService.findByUserId(userId);
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        if(userBalance.getMoney()<money){
            return Result.failureResult(ExceptionCode.Failure.MONEY_NOT_ENOUGH);
        }
        BaseProccess baseProccess=new MoneyTransToPlatformMoneyProccess(moneyTransToPlatformMoneyServiceV1,MoneyTransToPlatformMoneyV1Factory.newInstance(userBalance,money)
                );
        baseProccess.exec();
        if(baseProccess.getException().size()!=0){
            Result.failureResult(baseProccess.getException().get(0));
        }
        return Result.successResult();
    }
    //冲点币 1
    @PostMapping("/createDepositPlatformMoney")
    @ResponseBody
    @ApiOperation(value = "冲点币")
    public Result createDepositPlatformMoney(@RequestBody DepositPlatformMoneyParam depositPlatformMoneyParam){
        UserBalance userBalance=userBalanceService.findByUserId(depositPlatformMoneyParam.getUserId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        Tuple2<UserDepositPlatformMoney, UserDepositPlatformMoneyLog> tuple2=DepositPlatformMoneyWrapperFactory.newInstance(depositPlatformMoneyParam.getUserId(),
                depositPlatformMoneyParam.getMoney(),depositPlatformMoneyParam.getType(),platformRateService);

        userDepositPlatformMoneyService.save(tuple2._1);

        userDepositPlatformMoneyLogService.save(tuple2._2);
        return Result.successResult(tuple2._1.getDepositStreamCode());

    }
//    @PostMapping("/createDepositMoney")
//    @ResponseBody
//    public Result createDepositMoney(DepositMoneyParam depositMoneyParam){
//        UserBalance userBalance=userBalanceService.findByUserId(depositMoneyParam.getUserId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
//        }
//        Tuple2<UserDepositMoney, UserDepositMoneyLog> tuple2=DepositMoneyWrapperFactory.newInstance(depositMoneyParam.getUserId(),
//                depositMoneyParam.getMoney(),depositMoneyParam.getType());
//        userDepositMoneyService.save(tuple2._1);
//        userDepositMoneyLogService.save(tuple2._2);
//
//        return Result.successResult(tuple2._1.getDepositCode());
//
//    }
    //2
    @RequestMapping("/depositPlatformMoney")
    @ApiOperation(value = "支付点币")
    public void depositPlatformMoney(HttpServletRequest request,HttpServletResponse response)throws Exception{
       // System.out.println(depositPlatformMoneyParam);
        String code=request.getParameter("code");
        if(code==null){
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_CODE));
        }

        UserDepositPlatformMoney userDepositPlatformMoney=userDepositPlatformMoneyService.findByCode(code);
        if(userDepositPlatformMoney==null){
            ResponseUtils.send(response,Result.failureResult(ExceptionCode.Failure.NO_CODE));
        }
        UserBalance userBalance=userBalanceService.findByUserId(userDepositPlatformMoney.getUserId());
        if(userBalance==null){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Gson gson=new Gson();
            writer.write(gson.toJson(Result.failureResult(ExceptionCode.Failure.NO_USERS)));
          //  return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }


        BaseProccess baseProccess=new DepositPlatformMoneyCreateProccess(DepositPlatformMoneyWrapperFactory.newInstance(userDepositPlatformMoney),
                depositPlatformMoneyCreateService);

        if(userDepositPlatformMoney.getDirection()==1){
            ((DepositPlatformMoneyCreateProccess)baseProccess).setiPay(new ZhifubaoPay());
            ((DepositPlatformMoneyCreateProccess)baseProccess).setResponse(response);
            ((DepositPlatformMoneyCreateProccess) baseProccess).setRequest(request);
        }else if(userDepositPlatformMoney.getDirection()==2){
            ((DepositPlatformMoneyCreateProccess)baseProccess).setiPay(new WeixinPay(wxPayConfig));
            ((DepositPlatformMoneyCreateProccess)baseProccess).setResponse(response);
            ((DepositPlatformMoneyCreateProccess) baseProccess).setRequest(request);
        }else{
            return;
        }
        baseProccess.exec();
        if(baseProccess.getException().size()!=0){
            ResponseUtils.send(response,Result.failureResult(baseProccess.getException().get(0)));
        }
    }

}
