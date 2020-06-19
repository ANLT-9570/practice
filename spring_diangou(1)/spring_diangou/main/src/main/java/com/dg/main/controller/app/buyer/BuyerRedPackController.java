package com.dg.main.controller.app.buyer;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.RedPackRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.service.AppRedPackService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.service.RedPackCreateService;
import com.dg.main.serviceImpl.orders.service.RedPackService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "领红包",tags = {"用户-领红包"})
@RequestMapping("/buyer/v1/AppRedPack")
public class BuyerRedPackController {
	@Autowired
	private AppRedPackService appRedPackService;
	@Autowired
	UserBalanceService userBalanceService;
	@Autowired
	RedPackService redPackService;
	@Autowired
	UserMoneyStreamLogService userMoneyStreamLogService;

	@Autowired
	private MobileUserRepository mobileUserRepository;


	@PostMapping("/take")//抢红包
	@ResponseBody
	@ApiOperation(value = "抢红包",notes = "take")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId",value = "用户id"),
			@ApiImplicitParam(name = "businessId",value = "商家id")
	})
	public Result take(Long userId,Long businessId){
		if(userId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		if(businessId == null){
			return Result.failureResult("43360","商户id不能为空");
		}
		MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		MobileUser business = mobileUserRepository.selectMobileUse(businessId);
		if(business == null){
			return Result.failureResult("43361","没有该商户");
		}
		List<RedPack> redPackList = redPackService.isSendv1(businessId);
		if (redPackList.size() > 0) {
			return Result.failureResult(ExceptionCode.Failure.IS_STARTED_SENDED_REDPACK);
		}
		return redPackService.takeRed(userId,businessId);
	}
	@GetMapping("/selectRedpack")
	@ApiOperation(value = "跑马灯",notes = "实时刷新")
	public Result selectAppRedPack (){
		Result result = appRedPackService.selectAppRedPack();
		return result;
	}

}
