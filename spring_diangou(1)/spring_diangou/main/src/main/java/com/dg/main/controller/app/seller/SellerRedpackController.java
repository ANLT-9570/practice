package com.dg.main.controller.app.seller;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.RedPackParam;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.RedPackCreateProccess;
import com.dg.main.serviceImpl.orders.factory.RedPackWrapperFactory;
import com.dg.main.serviceImpl.orders.service.RedPackCreateService;
import com.dg.main.serviceImpl.orders.service.RedPackService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seller/v1/redPack/")
@Api(tags = "商家-红包")
public class SellerRedpackController {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    RedPackService redPackService;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private RedPackCreateService redPackCreateService;

    @PostMapping(value = "/create" )//发红包
    @ApiOperation(value = "创建红包",notes = "create")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redPackParam",value = "红包信息")
    })
    public Result create(@RequestBody RedPackParam redPackParam) throws Exception {

        //long time = new Date().getTime()+10*60*1000;
        long time = new Date().getTime()+2*60*1000;
//    	long time = new Date().getTime()+10*60*1000;
        //long time = new Date().getTime()+10*60*1000;
//        long time = new Date().getTime()+10*60*1000;
        redPackParam.setSendTime(new Timestamp(time));
        if(redPackParam.getShopId()==null){
            return   Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        if(redPackParam.getUserId()==null){
            return   Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        UserBalance userBalance=userBalanceService.findByUserId(redPackParam.getUserId());
        if(userBalance==null){
            return   Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        if(redPackParam.getNumber()>redPackParam.getPlatformMoney()){
            return   Result.failureResult(ExceptionCode.Failure.NUMBER_GREATER_MONEY);
        }
        List<RedPack> redPackList=redPackService.isSend(redPackParam.getUserId());
        if(redPackList.size()>0){
            return   Result.failureResult(ExceptionCode.Failure.IS_SENDED_REDPACK);
        }


        BaseProccess proccess=new RedPackCreateProccess(RedPackWrapperFactory.newInstance(userBalance,redPackParam),redPackCreateService
        );
        proccess.exec();
        if(proccess.getException().size()!=0){
            return Result.failureResult(proccess.getException().get(0));
        }
        return Result.successResult();
    }
}
