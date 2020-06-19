package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;
import com.dg.main.Entity.settings.CompanyWithDrawPlatformMoneyRate;
import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import com.dg.main.Entity.users.UserBalance;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.param.orders.WithdrawPlatformMoneyParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.setting.CompanyWithDrawPlatformMoneyRateService;
import com.dg.main.serviceImpl.orders.service.MoneyTransToPlatformMoneyRateService;
import com.dg.main.serviceImpl.orders.wrapper.WithdrawPlatformMoneyWrapper;
import com.dg.main.util.Tuple2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class WithdrawPlatformMoneyWrapperFactory {

	public static WithdrawPlatformMoneyWrapper newInstance(Long userId,Long money){
		Gson gson=new Gson();
		UserWithdrawPlatformMoney userWithdrawPlatformMoney=new UserWithdrawPlatformMoney();
		UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog=new UserWithdrawPlatformMoneyLog();
		// userWithdrawPlatformMoney.setCreateTime(new Date());
		userWithdrawPlatformMoney.setCurrentRate(0l);//todo
		userWithdrawPlatformMoney.setPlatformMoney(money);
		userWithdrawPlatformMoney.setUserId(userId);
		userWithdrawPlatformMoney.setWithdrawStreamCode(CodeGenerator.withdrawPlatformMoneyCode.create(userWithdrawPlatformMoney));

		userWithdrawPlatformMoneyLog.setCreateTime(new Date());
		userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawPlatformMoney));
		userWithdrawPlatformMoneyLog.setCurrentRate(0l);
		userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
		userWithdrawPlatformMoneyLog.setUserId(userId);
		userWithdrawPlatformMoneyLog.setPlatformMoney(money);
		UserBalance userBalance=new UserBalance();
		return new WithdrawPlatformMoneyWrapper(userWithdrawPlatformMoney,userWithdrawPlatformMoneyLog,userBalance,null);
	}
	public static Tuple2<UserWithdrawPlatformMoney,UserWithdrawPlatformMoneyLog> newInstance(WithdrawPlatformMoneyParam withdrawPlatformMoneyParam,
																							 CompanyWithDrawPlatformMoneyRateService companyWithDrawPlatformMoneyRateService,
																							 MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateServiceService){
		Gson gson=new Gson();
		UserWithdrawPlatformMoney userWithdrawPlatformMoney=new UserWithdrawPlatformMoney();
		UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog=new UserWithdrawPlatformMoneyLog();
		CompanyWithDrawPlatformMoneyRate companyWithDrawPlatformMoneyRate=companyWithDrawPlatformMoneyRateService.getRate();
		MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateServiceService.getRate();

		BigDecimal platformMoney=new BigDecimal(withdrawPlatformMoneyParam.getMoney());
		// BigDecimal leftPlatformMoney=platformMoney/companyWithDrawPlatformMoneyRate.getRate();
		//  BigDecimal leftPlatformMoney=platformMoney.multiply(new BigDecimal(companyWithDrawPlatformMoneyRate.getRate()).divide(new BigDecimal(100)));
		// Long money=new BigDecimal(new BigDecimal(platformMoney-leftPlatformMoney))/moneyTransToPlatformMoneyRate.getRate());
		//  Long money=platformMoney.subtract(leftPlatformMoney).
		// divide(new BigDecimal(moneyTransToPlatformMoneyRate.getRate()),2, RoundingMode.HALF_UP).
		//   multiply(new BigDecimal(100)).longValue();
		BigDecimal tranToMoney=platformMoney.divide(new BigDecimal(moneyTransToPlatformMoneyRate.getRate()));
		BigDecimal leftMoney=tranToMoney.multiply(new BigDecimal(companyWithDrawPlatformMoneyRate.getRate())).divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
		BigDecimal money=tranToMoney.subtract(leftMoney);
		userWithdrawPlatformMoney.setCurrentRate(companyWithDrawPlatformMoneyRate.getRate());//todo
		userWithdrawPlatformMoney.setPlatformMoney(withdrawPlatformMoneyParam.getMoney());
		userWithdrawPlatformMoney.setMoney(money.multiply(new BigDecimal(100)).longValue());
		userWithdrawPlatformMoney.setLeftMoney(leftMoney.multiply(new BigDecimal(100)).longValue());
		userWithdrawPlatformMoney.setUserId(withdrawPlatformMoneyParam.getUserId());
		userWithdrawPlatformMoney.setWithdrawStreamCode(CodeGenerator.withdrawPlatformMoneyCode.create(userWithdrawPlatformMoney));

		userWithdrawPlatformMoneyLog.setCreateTime(new Date());
		userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawPlatformMoney));
		userWithdrawPlatformMoneyLog.setCurrentRate(companyWithDrawPlatformMoneyRate.getRate());
		userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
		userWithdrawPlatformMoneyLog.setUserId(withdrawPlatformMoneyParam.getUserId());
		userWithdrawPlatformMoneyLog.setPlatformMoney(withdrawPlatformMoneyParam.getMoney());

		return new Tuple2<UserWithdrawPlatformMoney,UserWithdrawPlatformMoneyLog>(userWithdrawPlatformMoney,userWithdrawPlatformMoneyLog);
	}

	public static WithdrawPlatformMoneyWrapper newInstance(UserBalance userBalance, WithdrawPlatformMoneyParam withdrawPlatformMoneyParam,CompanyWithDrawPlatformMoneyRateService companyWithDrawPlatformMoneyRateService,
														   MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateServiceService){
		Gson gson=new Gson();
		UserWithdrawPlatformMoney userWithdrawPlatformMoney=new UserWithdrawPlatformMoney();
		UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog=new UserWithdrawPlatformMoneyLog();
		CompanyWithDrawPlatformMoneyRate companyWithDrawPlatformMoneyRate=companyWithDrawPlatformMoneyRateService.getRate();
		MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateServiceService.getRate();

		BigDecimal platformMoney=new BigDecimal(withdrawPlatformMoneyParam.getMoney());
		// BigDecimal leftPlatformMoney=platformMoney/companyWithDrawPlatformMoneyRate.getRate();
		//  BigDecimal leftPlatformMoney=platformMoney.multiply(new BigDecimal(companyWithDrawPlatformMoneyRate.getRate()).divide(new BigDecimal(100)));
		// Long money=new BigDecimal(new BigDecimal(platformMoney-leftPlatformMoney))/moneyTransToPlatformMoneyRate.getRate());
		//  Long money=platformMoney.subtract(leftPlatformMoney).
		// divide(new BigDecimal(moneyTransToPlatformMoneyRate.getRate()),2, RoundingMode.HALF_UP).
		//   multiply(new BigDecimal(100)).longValue();
		BigDecimal tranToMoney=platformMoney.divide(new BigDecimal(moneyTransToPlatformMoneyRate.getRate()));
		BigDecimal leftMoney=tranToMoney.multiply(new BigDecimal(companyWithDrawPlatformMoneyRate.getRate())).divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
		BigDecimal money=tranToMoney.subtract(leftMoney);
		userWithdrawPlatformMoney.setCurrentRate(companyWithDrawPlatformMoneyRate.getRate());//todo
		userWithdrawPlatformMoney.setPlatformMoney(withdrawPlatformMoneyParam.getMoney());
		userWithdrawPlatformMoney.setMoney(money.multiply(new BigDecimal(100)).longValue());
		userWithdrawPlatformMoney.setLeftMoney(leftMoney.multiply(new BigDecimal(100)).longValue());
		userWithdrawPlatformMoney.setUserId(withdrawPlatformMoneyParam.getUserId());
		userWithdrawPlatformMoney.setWithdrawStreamCode(CodeGenerator.withdrawPlatformMoneyCode.create(userWithdrawPlatformMoney));
		userWithdrawPlatformMoney.setDirection(withdrawPlatformMoneyParam.getType());

		userWithdrawPlatformMoneyLog.setCreateTime(new Date());
		userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawPlatformMoney));
		userWithdrawPlatformMoneyLog.setCurrentRate(0l);
		userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
		userWithdrawPlatformMoneyLog.setUserId(userBalance.getUserId());
		userWithdrawPlatformMoneyLog.setPlatformMoney(withdrawPlatformMoneyParam.getMoney());
		//  UserBalance userBalance=new UserBalance();
		return new WithdrawPlatformMoneyWrapper(userWithdrawPlatformMoney,userWithdrawPlatformMoneyLog,userBalance,null);
	}
	public static WithdrawPlatformMoneyWrapper newInstance(UserWithdrawPlatformMoney userWithdrawPlatformMoney ){
		return new WithdrawPlatformMoneyWrapper(userWithdrawPlatformMoney,null,null,null);
	}
	public static WithdrawPlatformMoneyWrapper newInstance(UserBalance userBalance,UserWithdrawPlatformMoney userWithdrawPlatformMoney ){
		Gson gson=new Gson();
		UserWithdrawPlatformMoneyLog userWithdrawPlatformMoneyLog=new UserWithdrawPlatformMoneyLog();
		userWithdrawPlatformMoneyLog.setCreateTime(new Date());
		userWithdrawPlatformMoneyLog.setJsonOfCurrentStatus(gson.toJson(userWithdrawPlatformMoney));
		userWithdrawPlatformMoneyLog.setCurrentRate(0l);
		userWithdrawPlatformMoneyLog.setWithdrawStreamCode(userWithdrawPlatformMoney.getWithdrawStreamCode());
		userWithdrawPlatformMoneyLog.setUserId(userBalance.getUserId());
		userWithdrawPlatformMoneyLog.setPlatformMoney(userWithdrawPlatformMoney.getPlatformMoney());
		return new WithdrawPlatformMoneyWrapper(userWithdrawPlatformMoney,userWithdrawPlatformMoneyLog,userBalance,null);
	}
}
