package com.dg.main.controller.app.common;

import com.dg.main.Entity.ChatJson;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserRealName;
import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.dto.PersonDto;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRealNameRepository;

import com.dg.main.serviceImpl.partner.home.PartnerHomeService;
import com.dg.main.serviceImpl.partner.me.PartnerUserService;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.serviceImpl.users.UserAddressService;
import com.dg.main.serviceImpl.users.UserRealNameService;
import com.dg.main.serviceImpl.users.UserThirdAccountService;
import com.dg.main.util.*;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("common/login")
@Api(tags = "通用-用户商家登录注册")
public class CommonLoginController {

	@Autowired
	private MobileUserRepository mobileUserRepository;
	@Autowired
	UserThirdAccountService userThirdAccountService;
	@Autowired
	UserAddressService userAddressService;
	@Autowired
	UserRealNameService userRealNameService;

	@Autowired
	UserRealNameRepository userRealNameRepository;
	@Autowired
	UserBalanceRepository userBalanceRepository;
	@Autowired
	ShopsService shopsService;
	@Autowired
	PartnerUserService partnerUserService;

//	@GetMapping("/sendSMS")
//	@ApiOperation(value = "sendSMS",notes = "用户或商家手机号发验证码")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "phone",value = "手机号")
//
//	})
//	public Result sendSMS(String phone){//String verify
//		if (phone == null || phone == ""){
//			return Result.failureResult(ExceptionCode.Failure.NO_PHONE_NULL);
//		}
//		//生成验证码
//		String code = SMSCodeGenerator.code4();
//		//把手机号对应的验证码存储
//		SMSContainerUtils.container.put(phone, code);
//		SendSmsResponse sms = new SendSmsResponse();
//		try {
//			//发送短信验证码
//			sms = AliyunSmsUtils.sendSms(phone, code);
//
//		} catch (ClientException e) {
//
//			e.printStackTrace();
//		}
//		return Result.successResult(sms);
//	}

	@GetMapping("/SMSLogin")
	@ApiOperation(value = "SMSLogin",notes = "使用验证码登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone",value = "手机号"),
			@ApiImplicitParam(name = "verify",value = "验证码"),
			@ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
	})
	public Result SMSLogin(String phone,String verify,Integer role){//String verify

		if (verify == null || verify == "" || phone == null || phone == ""){
			return Result.failureResult(ExceptionCode.Failure.NO_PHONEORVERIFY_NULL);
		}
		Boolean aBoolean = ValidateCode.verifyPhone(phone);
		if(!aBoolean){
			return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
		}
		//根据手机号获取验证码
		String newVerify = SMSContainerUtils.container.get(phone);
		if(newVerify == null || newVerify == ""){
			return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
		}
		//判断验证码是否正确
		if(!newVerify.equals(verify)){
			return Result.failureResult(ExceptionCode.Failure.ERROR_VERIFY);
		}
		MobileUser mobileUser = mobileUserRepository.findByPhoneAndRole(phone, role);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ENROL);
		}
		return Result.successResult(mapTo.apply(mobileUser));
	}

	@GetMapping("/enroll")
	@ApiOperation(value = "enroll",notes = "注册",tags = "注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone",value = "手机号"),
			@ApiImplicitParam(name = "password",value = "密码"),
			@ApiImplicitParam(name = "verify",value = "验证码"),
			@ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
	})
	public Result enroll(String phone,String password,String verify,Integer role) {
		if(StringUtils.isEmpty(phone)){
			return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
		}
		String newVerify = SMSContainerUtils.container.get(phone);
//		if (newVerify == null || newVerify == ""){
//			return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
//		}
//		if(!verify.equals(newVerify)){
//			return Result.failureResult(ExceptionCode.Failure.VERIFY_ERROR);
//		}
		//查询手机号是否注册
//		AppUsers users = usersRepository.findByPhone(phone);
		Boolean aBoolean = ValidateCode.verifyPhone(phone);
		if(!aBoolean){
			return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
		}
		MobileUser users = mobileUserRepository.findByPhoneAndRole(phone,role);
		if (users != null) {
			return Result.failureResult(ExceptionCode.Failure.ALREADY_ENROL);
		}
		MobileUser mobileUser = partnerUserService.enrol(phone, password, verify, role,2);
//		MobileUser mobileUser = new MobileUser();
//
////		mobileUser.setPassword(password);
//		mobileUser.setLoginPassword(password);
//		mobileUser.setPhone(phone);
//		mobileUser.setCreatetime(new Timestamp(new Date().getTime()));
//		mobileUser.setRole(role);
//		String registerID=phone+new Date().getTime();
//		String token = shopsService.getChart(registerID,phone);
//		mobileUser.setChatToken(token);
//		mobileUser.setRegisterID(registerID);
////		mobileUser.setInviteCode(CodeGenerator.getInviteCode());
////		AppUsers save = usersRepository.save(appUsers);
//		MobileUser user = mobileUserRepository.save(mobileUser);
//		String chatToken = user.getChatToken();
//		if(StringUtils.isNotEmpty(chatToken)){
//			Gson gson = new Gson();
//			ChatJson chatJson = gson.fromJson(chatToken, ChatJson.class);
//			mobileUser.setChatToken(chatJson.getInfo().getToken());
//		}
//		UserBalance userBalance=new UserBalance();
//		userBalance.setUserId(mobileUser.getMobileUseId());
//
//		UserThirdAccount userThirdAccount=new UserThirdAccount();
//		userThirdAccount.setUserId(mobileUser.getMobileUseId());
//		userBalanceRepository.save(userBalance);
//		userThirdAccountService.save(userThirdAccount);
//		if(role == 2){
//			UserRealName userRealName=new UserRealName();
//			userRealName.setUserId(mobileUser.getMobileUseId());
//			userRealNameRepository.save(userRealName);
//		}

		return Result.successResult(mapTo.apply(mobileUser));
	}



	@GetMapping("/PassLogin")
	@ApiOperation(value = "PassLogin",notes = "用户密码登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone",value = "手机号"),
			@ApiImplicitParam(name = "password",value = "密码"),
			@ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
	})

	public Result PassLogin(String phone,String password,Integer role) {
		if(StringUtils.isEmpty(phone)){
			return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
		}
		Boolean aBoolean = ValidateCode.verifyPhone(phone);
		if(!aBoolean){
			return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
		}
		MobileUser mobileUser = mobileUserRepository.findByPhoneAndRole(phone,role);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ENROL);
		}

		if (!mobileUser.getLoginPassword().equals(password)){
			return Result.failureResult(ExceptionCode.Failure.PAY_PASSWORD_ERROR);
		}
		String chatToken = mobileUser.getChatToken();
		if(StringUtils.isNotEmpty(chatToken)){
			Gson gson = new Gson();
			ChatJson chatJson = gson.fromJson(chatToken, ChatJson.class);
			mobileUser.setChatToken(chatJson.getInfo().getToken());
		}
		return Result.successResult(mapTo.apply(mobileUser));
	}
	private Function<MobileUser, PersonDto> mapTo=mobileUser -> {
		PersonDto personDto=new PersonDto();
		personDto.setUserId(mobileUser.getMobileUseId());
		personDto.setAvatarImg(mobileUser.getImage());
		personDto.setIMID(mobileUser.getRegisterID());
		personDto.setChatToken(mobileUser.getChatToken());
		personDto.setIntroduction(mobileUser.getIntroduction());
		personDto.setNickName(mobileUser.getName());
		personDto.setSex(mobileUser.getSex());
		personDto.setPhone(mobileUser.getPhone());
		personDto.setRole(mobileUser.getRole());
		//personDto.setPayPassword(mobileUser.getPayPassword());
		UserThirdAccount userThirdAccount=userThirdAccountService.findByUserId(mobileUser.getMobileUseId());
		if(userThirdAccount.getZhifubaoPayeeAccount().equals("")){
			personDto.setZhifubaoAccountStatus(2);
		}else{
			personDto.setZhifubaoAccountStatus(1);
		}
		if(userThirdAccount.getWeixinOpenId().equals("")){
			personDto.setWeixinAccountStatus(2);
		}else{
			personDto.setWeixinAccountStatus(1);
		}
		personDto.setZhifubaoAccount(userThirdAccount.getZhifubaoPayeeAccount());
		personDto.setZhifubaoAccountName(userThirdAccount.getZhifubaoPayeeRealName());
		personDto.setWeixinAccount(userThirdAccount.getWeixinOpenId());
		personDto.setWeixinAccountName(userThirdAccount.getWeixinRealName());

		CustomerAddress customerAddress=userAddressService.getDefaultAddress(mobileUser.getMobileUseId());
		if(customerAddress!=null){
			personDto.setSendPhone(customerAddress.getPhone());
			personDto.setAddress(customerAddress.getAddress());
			personDto.setCity(customerAddress.getCity());
			personDto.setConsignee(customerAddress.getConsignee());
			personDto.setZipCode(customerAddress.getZipCode());
			personDto.setProvince(customerAddress.getProvince());
			personDto.setAddressId(customerAddress.getId());
		}
		if(mobileUser.getRole() == 2){
			UserRealName userRealName=userRealNameService.findByUserId(mobileUser.getMobileUseId());
			if(userRealName!=null){
				personDto.setStatus(userRealName.getStatus());
			}else {
				personDto.setStatus(2);
			}
		}

		UserBalance userBalance=userBalanceRepository.findByUserId(mobileUser.getMobileUseId());
		if(userBalance!=null){
			personDto.setMoney(userBalance.getMoney());
			personDto.setPlatformMoney(userBalance.getPlatformMoney());
		}
		int role = mobileUser.getRole();
		if(role == 2){
			Shops shops = shopsService.findByUserId(mobileUser.getMobileUseId());
			if(shops != null){
				personDto.setShopIMID(shops.getRegisterID());
				personDto.setShopChatToken(shops.getChatToken());
				personDto.setShopId(shops.getId());
			}else{
				personDto.setShopId(0L);
			}
		}
		return personDto;
	};

}
