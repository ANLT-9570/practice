package com.dg.main.controller.admin;

import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/manualWithdraw")
@Api(tags = "后台-手动转账")
public class ManualWithdrawController {
    @Autowired
    UserWithdrawMoneyService userWithdrawMoneyService;
    @Autowired
    UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
    @Autowired
    UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
    @Autowired
    UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService;
    @Autowired
    private ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService;
    @Autowired
    ManualCancelWithdrawMoneyService manualCancelWithdrawMoneyService;
    @Autowired
    ManualCancelWithdrawPlatformMoneyService manualCancelWithdrawPlatformMoneyService;

    @GetMapping("/isTransMoney")
    @ResponseBody
    @ApiOperation(value = "isTransMoney",notes = "转账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "编号")
    })
    public Result isTransMoney(@RequestParam String code)throws Exception{
        return Result.successResult();
//        if(code==null){
//          return  Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserWithdrawMoney userWithdrawMoney=userWithdrawMoneyService.findByCode(code);
//        if(userWithdrawMoney==null){
//          return  Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawMoney.getUserId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
//        }
//        BaseProccess proccess1=new ManualWithdrawMoneyProccess(WithdrawMoneyWrapperFactory.newInstance(userBalance,userWithdrawMoney),manualWithdrawMoneyProccessService);
//        proccess1.exec();
//        if(proccess1.getException().size()==0){
//            return Result.successResult();
//        }else{
//            return Result.failureResult(proccess1.getException().get(0));
//        }
        // return Result.successResult();
    }
    @RequestMapping("/isTransPlatformMoney")
    @ResponseBody
    @ApiOperation(value = "isTransPlatformMoney",notes = "平台转账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "编号")
    })
    public Result isTransPlatformMoney(@RequestParam String code)throws Exception{
        return Result.successResult();
//        if(code==null){
//          return  Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserWithdrawPlatformMoney userWithdrawPlatformMoney=userWithdrawPlatformMoneyService.findByCode(code);
//        if(userWithdrawPlatformMoney==null){
//           return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawPlatformMoney.getUserId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
//        }
//        BaseProccess proccess2=new ManualWithdrawPlatformMoneyProccess(manualWithdrawPlatformMoneyProccessService,WithdrawPlatformMoneyWrapperFactory.newInstance(userBalance,userWithdrawPlatformMoney));
//        proccess2.exec();
//        if(proccess2.getException().size()==0){
//            return Result.successResult();
//        }else{
//            return Result.failureResult(proccess2.getException().get(0));
//        }
        // return Result.successResult();
    }
    @RequestMapping("/cancelPlatformMoney")
    @ResponseBody
    @ApiOperation(value = "cancelPlatformMoney",notes = "取消平台转账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "编号"),
            @ApiImplicitParam(name = "reason",value = "反馈信息")
    })
    public Result cancelPlatformMoney(@RequestParam String code,String reason)throws Exception{
        return Result.successResult();
//        if(code==null){
//         return   Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserWithdrawPlatformMoney userWithdrawPlatformMoney=userWithdrawPlatformMoneyService.findByCode(code);
//        if(userWithdrawPlatformMoney==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        userWithdrawPlatformMoney.setAuditFailed(reason);
//        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawPlatformMoney.getUserId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
//        }
//        BaseProccess proccess2=new ManualCancelWithdrawPlatformMoneyProccess(manualCancelWithdrawPlatformMoneyService,WithdrawPlatformMoneyWrapperFactory.newInstance(userBalance,userWithdrawPlatformMoney));
//        proccess2.exec();
//        if(proccess2.getException().size()==0){
//            return Result.successResult();
//        }else{
//            return Result.failureResult(proccess2.getException().get(0));
//        }
        // return Result.successResult();
    }
    @RequestMapping("/cancelMoney")
    @ResponseBody
    @ApiOperation(value = "cancelMoney",notes = "取消金钱转账")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "编号"),
            @ApiImplicitParam(name = "reason",value = "反馈信息")
    })
    public Result cancelMoney(@RequestParam String code,String reason)throws Exception{
        return Result.successResult();
//        if(code==null){
//            return  Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        UserWithdrawMoney userWithdrawMoney=userWithdrawMoneyService.findByCode(code);
//        if(userWithdrawMoney==null){
//            return  Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
//        }
//        userWithdrawMoney.setAuditFailed(reason);
//        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawMoney.getUserId());
//        if(userBalance==null){
//            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
//        }
//        BaseProccess proccess2=new ManualCancelWithdrawMoneyProccess(manualCancelWithdrawMoneyService,WithdrawMoneyWrapperFactory.newInstance(userBalance,userWithdrawMoney));
//        proccess2.exec();
//        if(proccess2.getException().size()==0){
//            return Result.successResult();
//        }else{
//            return Result.failureResult(proccess2.getException().get(0));
//        }
        // return Result.successResult();
    }
//    @RequestMapping("/withdrawMoneyList")
//    @ResponseBody
//    @ApiOperation(value = "withdrawMoneyList",notes = "查询所有")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset",value = "第几页"),
//            @ApiImplicitParam(name = "limit",value = "每页数量")
//    })
//    public slzcResult withdrawMoneyList(@RequestParam("0")Integer offset,@RequestParam("15")Integer limit){
//
//        return userWithdrawMoneyService.getUnPayedList();
//    }
//    @RequestMapping("/withdrawPlatformMoneyList")
//    @ResponseBody
//    @ApiOperation(value = "withdrawPlatformMoneyList",notes = "查询平台所有的转账记录")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset",value = "第几页"),
//            @ApiImplicitParam(name = "limit",value = "每页数量")
//    })
//    public slzcResult withdrawPlatformMoneyList(Integer offset,Integer limit){
//        return userWithdrawPlatformMoneyService.getUnpayedList();
//    }
    @RequestMapping("/page")
    public String page(){
        return "/order/manualWithdrawMoney";
    }
    @RequestMapping("/page1")
    public String page1(){
        return "/order/manualWithdrawPlatformMoney";
    }
}
