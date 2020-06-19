package com.dg.main.serviceImpl.partner.me;

import com.dg.main.Entity.ChatJson;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.*;
import com.dg.main.dto.PersonDto;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.repository.users.UserRelationShipRepository;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.serviceImpl.users.UserAddressService;
import com.dg.main.serviceImpl.users.UserRealNameService;
import com.dg.main.serviceImpl.users.UserThirdAccountService;
import com.dg.main.util.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.System.out;

@Service
public class PartnerUserService {

    @Autowired
    MobileUserRepository mobileUserRepository;
    @Autowired
    UserRelationShipRepository userRelationShipRepository;
    @Autowired
    ShopsService shopsService;
    @Autowired
    UserBalanceRepository userBalanceRepository;
    @Autowired
    UserThirdAccountService userThirdAccountService;
    @Autowired
    UserRealNameRepository userRealNameRepository;
    @Autowired
    UserAddressService userAddressService;
    @Autowired
    UserRealNameService userRealNameService;

    public Result comradeLogin(String phone, String password) {
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        if(!ValidateCode.verifyPhone(phone)){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser mobileUser = mobileUserRepository.findByPhoneAndIsCompanyEmployee(phone,1);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ENROL);
        }
        if(!mobileUser.getLoginPassword().equals(password)){
            return Result.failureResult(ExceptionCode.Failure.ERROR_PWD);
        }
//        MobileUserVo mobileUserVo = new MobileUserVo();
//        BeanUtils.copyProperties(mobileUser,mobileUserVo);
//        mobileUserVo.setLoginPassword(null);
        return Result.successResult(mapTo.apply(mobileUser));
    }

    public Result comradeSignUp(String phone, String verifyCode, String password) {
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        if(!ValidateCode.verifyPhone(phone)){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        if(StringUtils.isEmpty(password)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PWD);
        }
//        if(StringUtils.isEmpty(verifyCode)){
//            return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
//        }
//        String newVerify = SMSContainerUtils.container.get(phone);
//		if (newVerify == null || newVerify == ""){
//			return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
//		}
//		if(!verify.equals(newVerify)){
//			return Result.failureResult(ExceptionCode.Failure.VERIFY_ERROR);
//		}
        MobileUser mobileUser = mobileUserRepository.findByPhoneAndIsCompanyEmployee(phone, 1);
        if(mobileUser!= null){
            return Result.failureResult(ExceptionCode.Failure.ALREADY_ENROL);
        }
        MobileUser user = enrol(phone, password, verifyCode, 2, 1);
        return Result.successResult(mapTo.apply(user));
    }

    public MobileUser enrol(String phone,String password,String verify,Integer role,Integer isEmployee) {
        MobileUser mobileUser = new MobileUser();

        mobileUser.setLoginPassword(password);
        mobileUser.setPhone(phone);
        mobileUser.setCreatetime(new Timestamp(new Date().getTime()));
        mobileUser.setRole(role);
       // mobileUser.setInviteCode();
        String registerID = phone + new Date().getTime();
        String token = shopsService.getChart(registerID, phone);
        mobileUser.setChatToken(token);
        mobileUser.setRegisterID(registerID);
        if(isEmployee == 1){
            mobileUser.setIsCompanyEmployee(isEmployee);
            String inviteCode = CodeGenerator.getInviteCode();
            mobileUser.setInviteCode(inviteCode);
            Result result = QRUtils.generateQRCode(inviteCode);
            Map map =(Map) result.getData();
            Object thumbnailImage = map.get("thumbnailImage");
            mobileUser.setInviteCodeImage(thumbnailImage.toString());
            out.println(result);
        }
        MobileUser user = mobileUserRepository.save(mobileUser);
        String chatToken = user.getChatToken();
        if (StringUtils.isNotEmpty(chatToken)) {
            Gson gson = new Gson();
            ChatJson chatJson = gson.fromJson(chatToken, ChatJson.class);
            mobileUser.setChatToken(chatJson.getInfo().getToken());
        }
        UserBalance userBalance = new UserBalance();
        userBalance.setUserId(mobileUser.getMobileUseId());

        UserThirdAccount userThirdAccount = new UserThirdAccount();
        userThirdAccount.setUserId(mobileUser.getMobileUseId());
        userBalanceRepository.save(userBalance);
        userThirdAccountService.save(userThirdAccount);
        if (role == 2) {
            UserRealName userRealName = new UserRealName();
            userRealName.setUserId(mobileUser.getMobileUseId());
            userRealNameRepository.save(userRealName);
        }
        return mobileUser;
    }

    public Result comradeVerifyLogin(String phone, String verify) {
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        if(!ValidateCode.verifyPhone(phone)){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        if(StringUtils.isEmpty(verify)){
            return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
        }
        String newVerify = SMSContainerUtils.container.get(phone);
		if (newVerify == null || newVerify == ""){
			return Result.failureResult(ExceptionCode.Failure.NO_VERIFY);
		}
		if(!verify.equals(newVerify)){
			return Result.failureResult(ExceptionCode.Failure.VERIFY_ERROR);
		}
        MobileUser mobileUser = mobileUserRepository.findByPhoneAndIsCompanyEmployee(phone, 1);
        //        MobileUserVo mobileUserVo = new MobileUserVo();
//        BeanUtils.copyProperties(mobileUser,mobileUserVo);
//        mobileUserVo.setLoginPassword(null);
        return Result.successResult(mapTo.apply(mobileUser));
    }

    public Result viewVerify(Long mobileUserId) {
        if(mobileUserId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(mobileUserId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        String inviteCode = CodeGenerator.getInviteCode();
        mobileUse.setInviteCode(inviteCode);
        Result result = QRUtils.generateQRCode(inviteCode);
        Map map =(Map) result.getData();
        Object thumbnailImage = map.get("thumbnailImage");
        mobileUse.setInviteCodeImage(thumbnailImage.toString());
        mobileUserRepository.save(mobileUse);
        Map<String,String> map1 = new HashMap<>();
        map1.put("inviteCode",inviteCode);
        map1.put("inviteCodeImage",thumbnailImage.toString());
        return Result.successResult(map1);
    }

    public Result upComrade(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUse = mobileUserRepository.selectMobileUse(userId);
        if(mobileUse == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        UserRelationShip userRelationShip = userRelationShipRepository.findByShipUserId(userId);
        if(userRelationShip != null){
            return Result.successResult(userRelationShip);
        }
        return Result.successResult();
    }

    public Function<MobileUser, PersonDto> mapTo= mobileUser -> {
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
        personDto.setInviteCode(mobileUser.getInviteCode());
        personDto.setInviteCodeImage(mobileUser.getInviteCodeImage());
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

    public Result getQR(String imageName,HttpServletResponse response) {
        if(StringUtils.isEmpty(imageName)){
            return Result.failureResult(ExceptionCode.Failure.NOT_IMG);
        }
        OutputStream outStream = null;
        try {
            FileInputStream inStream = new FileInputStream("D:\\upload\\"+imageName+".png");
            //byte数组用于存放图片字节数据
            byte[] buff = new byte[inStream.available()];
            inStream.read(buff);
            inStream.close();
            //设置发送到客户端的响应内容类型
            response.setContentType("image/*");

             outStream = response.getOutputStream();
            out.write(buff);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.successResult(outStream);
    }

    public Result getQR2(String imageName, HttpServletResponse response) {
        if(StringUtils.isEmpty(imageName)){
            return Result.failureResult(ExceptionCode.Failure.NOT_IMG);
        }
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            String imgPath = "D:\\upload\\"+imageName+".png";
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("image/*");
            response.addHeader("Content-Disposition", "attachment;filename=image.png");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();
        }catch (Exception e){
            return Result.failureResult(ExceptionCode.Failure.NOT_IMAGE);
        }finally {
            try {
                if(out != null && ips != null){
                    out.close();
                    ips.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.successResult();
    }

    public Result getMyPartnerNumber(Long userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<UserRelationShip> list = userRelationShipRepository.findByUserId(userId);
        return Result.successResult(list.size());
    }
}
