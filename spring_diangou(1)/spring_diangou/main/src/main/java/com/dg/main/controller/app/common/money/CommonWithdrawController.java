package com.dg.main.controller.app.common.money;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.repository.orders.UserWithdrawMoneyRepository;
import com.dg.main.repository.orders.UserWithdrawPlatformMoneyRepository;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
import com.dg.main.serviceImpl.orders.payment.WeixinPay;
import com.dg.main.serviceImpl.setting.CompanyWithDrawPlatformMoneyRateService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.serviceImpl.users.UserThirdAccountService;
import com.github.wxpay.sdk.WXPayConfig;
import com.dg.main.Entity.orders.*;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.WithdrawMoneyParam;
import com.dg.main.param.orders.WithdrawPlatformMoneyParam;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.*;
import com.dg.main.serviceImpl.orders.factory.WithdrawMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.factory.WithdrawPlatformMoneyWrapperFactory;
import com.dg.main.serviceImpl.orders.payment.ZhifubaoWithdrawPay;
import com.dg.main.serviceImpl.orders.service.*;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/common/v1/withdraw")
@Api(tags = "通用-用户提现")
public class CommonWithdrawController {
    @Autowired
    UserWithdrawMoneyService userWithdrawMoneyService;
    @Autowired
    UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
    @Autowired
    UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
    @Autowired
    UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    WXPayConfig wxPayConfig;
    @Autowired
    private WithDrawMoneyCreateService withDrawMoneyCreateService;
    @Autowired
    WithDrawPlatformMoneyCreateService withDrawPlatformMoneyCreateService;
    @Autowired
    CompanyWithDrawPlatformMoneyRateService companyWithDrawPlatformMoneyRateService;
    @Autowired
    MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateService;
    @Autowired
    ManualWithdrawMoneyProccessService manualWithdrawMoneyProccessService;
    @Autowired
    private   ManualWithdrawPlatformMoneyProccessService manualWithdrawPlatformMoneyProccessService;
    @Autowired
    UserThirdAccountService userThirdAccountService;
    @Autowired
    UserWithdrawPlatformMoneyRepository userWithdrawPlatformMoneyRepository;
    @Autowired
    UserWithdrawMoneyRepository userWithdrawMoneyRepository;
    @GetMapping("/userWithdrawLog")
    public Result userWithdrawLog(@RequestParam(name = "userId") Long userId, @RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if( userId==null){
            return null;
        }
        PageRequest pageRequest = PageRequest.of(offset, limit);

        return Result.successResult(userWithdrawPlatformMoneyRepository.findByUserId(userId,pageRequest));

    }
    @GetMapping("/businessWithdrawLog")
    public Result businessWithdrawLog(@RequestParam(name = "userId")Integer userId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if(userId==null){
            return null;
        }
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return Result.successResult(userWithdrawMoneyRepository.findByUserId(userId,pageRequest));
    }
    @GetMapping("/withdrawMoney")
    public Result withdrawMoney(String code) throws Exception {

        if(code==null){
           return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        UserWithdrawMoney userWithdrawMoney=userWithdrawMoneyService.findByCode(code);
        if(userWithdrawMoney==null){
           return Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawMoney.getUserId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }

        BaseProccess baseProccess=new WithDrawMoneyCreateProccess(WithdrawMoneyWrapperFactory.newInstance(userWithdrawMoney),
                withDrawMoneyCreateService);
        if(userWithdrawMoney.getDirection()==1){
            ((WithDrawMoneyCreateProccess)baseProccess).setiPay(new ZhifubaoWithdrawPay());
        }else{
            ((WithDrawMoneyCreateProccess)baseProccess).setiPay(new WeixinPay(wxPayConfig));
        }
        baseProccess.exec();
        if(baseProccess.getException().size()==0){
            return Result.successResult("");
        }else{
            return Result.failureResult(baseProccess.getException().get(0));
        }

    }
    //提现人民币
    @ResponseBody
    @PostMapping(value = "/createwithdrawMoney")
    @ApiOperation(value = "提现人民币",notes = "createwithdrawMoney")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createwithdrawMoney",value = "提现人民币信息")

    })
    public Result createwithdrawMoney(@RequestBody WithdrawMoneyParam withdrawMoneyParam) throws Exception {
        UserBalance userBalance=userBalanceService.findByUserId(withdrawMoneyParam.getUserId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        if(StringUtils.isEmpty(withdrawMoneyParam.getMoney())){
            return  Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        BigDecimal bigDecimal=new BigDecimal(withdrawMoneyParam.getMoney());
        Long money=bigDecimal.multiply(new BigDecimal(100)).longValue();
        if(userBalance.getMoney()<money){
            return Result.failureResult(ExceptionCode.Failure.NOT__ENOUGH_USERS_MONEY);
        }
        UserThirdAccount userThirdAccount=userThirdAccountService.findByUserId(withdrawMoneyParam.getUserId());
        if(userThirdAccount!=null){
            if(userThirdAccount.getZhifubaoPayeeAccount().equals("")&&userThirdAccount.getZhifubaoPayeeRealName().equals("")){
                return Result.failureResult(ExceptionCode.Failure.NOT_BINDING_ZHIFUBAO);
            }
        }
        BaseProccess proccess=new CreateWithdrawMoneyProcess(WithdrawMoneyWrapperFactory.newInstance(userBalance,withdrawMoneyParam),manualWithdrawMoneyProccessService);

        if(withdrawMoneyParam.getType()==1){
            (  (CreateWithdrawMoneyProcess)proccess).setiPay(new ZhifubaoWithdrawPay());
        }
        proccess.exec();
        if(proccess.getException().size()==0){
            // ((CreateWithdrawMoneyProcess)proccess).getWithdrawMoneyWrapper()
            return Result.successResult(  ((CreateWithdrawMoneyProcess)proccess).getWithdrawMoneyWrapper().getUserWithdrawMoney().getWithdrawStreamCode());
        }else{
            return Result.failureResult(proccess.getException().get(0));
        }
    }

    //提现点币
    @ResponseBody
    @PostMapping(value = "/createWithdrawPlatformMoney")
    @ApiOperation(value = "提现点币",notes = "createWithdrawPlatformMoney")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createWithdrawPlatformMoney",value = "提现点币信息")

    })
    public Result createWithdrawPlatformMoney(@RequestBody WithdrawPlatformMoneyParam withdrawPlatformMoneyParam)throws Exception{
        UserBalance userBalance=userBalanceService.findByUserId(withdrawPlatformMoneyParam.getUserId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        if(StringUtils.isEmpty(withdrawPlatformMoneyParam.getMoney())){
            return  Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        if(userBalance.getPlatformMoney()<withdrawPlatformMoneyParam.getMoney()){
            return Result.failureResult(ExceptionCode.Failure.NOT__ENOUGH_USERS_MONEY);
        }
        UserThirdAccount userThirdAccount=userThirdAccountService.findByUserId(withdrawPlatformMoneyParam.getUserId());
        if(userThirdAccount!=null){
            if(userThirdAccount.getZhifubaoPayeeAccount().equals("")&&userThirdAccount.getZhifubaoPayeeRealName().equals("")){
                return Result.failureResult(ExceptionCode.Failure.NOT_BINDING_ZHIFUBAO);
            }
        }
        BaseProccess proccess=new CreateWithdrawPlatformMoneyProcess(WithdrawPlatformMoneyWrapperFactory.newInstance(userBalance,withdrawPlatformMoneyParam,companyWithDrawPlatformMoneyRateService,moneyTransToPlatformMoneyRateService),manualWithdrawPlatformMoneyProccessService);

        if(withdrawPlatformMoneyParam.getType()==1){
            (  (CreateWithdrawMoneyProcess)proccess).setiPay(new ZhifubaoWithdrawPay());
        }
        proccess.exec();
        if(proccess.getException().size()==0){
            // ((CreateWithdrawMoneyProcess)proccess).getWithdrawMoneyWrapper()
            return Result.successResult(((CreateWithdrawPlatformMoneyProcess)proccess).getWithdrawPlatformMoneyWrapper().getUserWithdrawPlatformMoney().getWithdrawStreamCode());
        }else{
            return Result.failureResult(proccess.getException().get(0));
        }

    }


    @GetMapping("/withdrawPlatformMoney")
    public Result withdrawPlatformMoney(String code) throws Exception {
        if(code==null){
            Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        UserWithdrawPlatformMoney userWithdrawPlatformMoney=userWithdrawPlatformMoneyService.findByCode(code);
        if(userWithdrawPlatformMoney==null){
            Result.failureResult(ExceptionCode.Failure.NO_ORDERS);
        }
        UserBalance userBalance=userBalanceService.findByUserId(userWithdrawPlatformMoney.getUserId());
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        BaseProccess baseProccess=new WithDrawPlatformMoneyCreateProccess(WithdrawPlatformMoneyWrapperFactory.newInstance(userWithdrawPlatformMoney)
               , withDrawPlatformMoneyCreateService);
        if(userWithdrawPlatformMoney.getDirection()==1){
            ((WithDrawPlatformMoneyCreateProccess)baseProccess).setiPay(new ZhifubaoWithdrawPay());
        //    ((DepositPlatformMoneyCreateProccess)baseProccess).setResponse(response);
        }else{
            ((WithDrawPlatformMoneyCreateProccess)baseProccess).setiPay(new WeixinPay(wxPayConfig));
        }
        baseProccess.exec();
        if(baseProccess.getException().size()!=0){
            return Result.failureResult(baseProccess.getException().get(0));
        }
        return Result.successResult();
    }
    @GetMapping("/countUserWithdraw")
    public Result countUserWithdraw(Integer userId){
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
      //  return Result.successResult(userWithdrawMoneyService.countUserWithdraw(userId));
        return Result.successResult();
    }
    @GetMapping("/userWithdrawStream")
    public Result userWithdrawStream(Integer userId){
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
      //  return Result.successResult(userWithdrawMoneyService.userWithdrawStream(userId));
        return Result.successResult();
    }
}
