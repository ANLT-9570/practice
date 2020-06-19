package com.dg.main.controller.partner.me;

import com.dg.main.serviceImpl.partner.me.PartnerUserService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "员工端-我的")
@RequestMapping("/v1/partner/user")
public class PartnerUserController {

    @Autowired
    private PartnerUserService partnerUserService;

    @GetMapping("/comradeLogin")
    @ApiOperation(value = "app伙伴登录",notes = "comradeLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    public Result comradeLogin(String phone,String password){
        return partnerUserService.comradeLogin(phone,password);
    }

    @GetMapping("/comradeVerifyLogin")
    @ApiOperation(value = "app伙伴验证码登录",notes = "comradeVerifyLogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "verify",value = "验证码")
    })
    public Result comradeVerifyLogin(String phone,String verify){
        return partnerUserService.comradeVerifyLogin(phone,verify);
    }

    @GetMapping("/comradeSignUp")
    @ResponseBody
    @ApiOperation(value = "app伙伴注册",notes = "comradeSignUp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "verifyCode",value = "验证码"),
            @ApiImplicitParam(name = "password",value = "密码")
    })
    public Result comradeSignUp(String phone,String verifyCode,String password){
        return partnerUserService.comradeSignUp( phone, verifyCode, password);
    }

    @GetMapping("/viewVerify")
    @ApiOperation(value = "查看邀请码",notes = "viewVerify")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobileUserId",value = "用户id")
    })
    public Result viewVerify(Long mobileUserId){
        return partnerUserService.viewVerify( mobileUserId);
    }

    @GetMapping("/upComrade")
    @ApiOperation(value = "上级伙伴",notes = "upComrade")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
    })
    public Result upComrade(Long userId){
        return partnerUserService.upComrade(userId);
    }

//    @GetMapping("/getQR")
//    @ApiOperation(value = "查看二维码",notes = "getQR")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "imageName",value = "图片名称"),
//    })
//    public Result getQR(String  imageName, HttpServletResponse response){
//        return partnerUserService.getQR(imageName,response);
//    }
    @GetMapping("/getQR2")
    @ApiOperation(value = "查看二维码",notes = "getQR")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageName",value = "图片名称"),
    })
    public Result getQR2(String  imageName, HttpServletResponse response){
        return partnerUserService.getQR2(imageName,response);
    }
    @GetMapping("/getMyPartnerNumber")
    @ApiOperation(value = "我的伙伴数量",notes = "getMyPartnerNumber")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
    })
    public Result getMyPartnerNumber(Long userId){
        return partnerUserService.getMyPartnerNumber(userId);
    }
}
