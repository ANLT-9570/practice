package com.dg.main.controller.app.common;

import com.dg.main.serviceImpl.users.MobileUserService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/v2/app/users")
@Api(tags = "通用-用户")
public class CommonUserController {
        @Autowired
        MobileUserService service;
        @PostMapping("/updateUserPhone")
        @ApiOperation(value = "updateUserPhone",notes = "更新用户手机")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "phone",value = "手机号"),
                @ApiImplicitParam(name = "userId",value = "用户ID"),
            //    @ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
        })
        public Result updateUserPhone(Long userId,String phone){
                return   service.updateUserPhone(userId,phone);

        }
        @PostMapping("/changePayPassword")
        @ApiOperation(value = "changePayPassword",notes = "修改支付密码")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "phone",value = "手机号"),
                @ApiImplicitParam(name = "oldPassword",value = "旧密码"),
                @ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户"),
                @ApiImplicitParam(name = "newPassword",value = "新密码"),
        })
        public Result changePayPassword(String phone, String oldPassword, String newPassword,Integer role){
                return service.changePayPassword(phone,oldPassword,newPassword,role);
        }
        @PostMapping("/changeLoginPassword")
        @ApiOperation(value = "changeLoginPassword",notes = "修改登录密码")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "phone",value = "手机号"),
                @ApiImplicitParam(name = "oldPassword",value = "旧密码"),
                @ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户"),
                @ApiImplicitParam(name = "newPassword",value = "新密码"),
        })
        public Result changeLoginPassword(String phone, String oldPassword, String newPassword,Integer role){
                return service.changeLoginPassword(phone,oldPassword,newPassword,role);
        }
        @PostMapping("/updateUserInfo")
        @ApiOperation(value = "updateUserInfo",notes = "修改用户信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "userId",value = "用户ID"),
                @ApiImplicitParam(name = "sex",value = "性别"),
                @ApiImplicitParam(name = "nickName",value = "昵称"),
                @ApiImplicitParam(name = "avatarImg",value = "头像"),
        })
        public Result updateUserInfo(Long userId,Integer sex,String nickName,String avatarImg){
                return service.updateUserInfo(userId,sex,nickName,avatarImg);
        }
        @PostMapping("/setPayPassword")
        @ApiOperation(value = "setPayPassword",notes = "设置支付密码")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "phone",value = "手机号"),
                @ApiImplicitParam(name = "password",value = "密码MD5"),
                @ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
        })
        public Result setPayPassword(String phone, String password,Integer role){
                return service.setPayPassword(phone,password,role);
        }
        @PostMapping("/setLoginPassword")
        @ApiOperation(value = "setLoginPassword",notes = "设置登录密码")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "phone",value = "手机号"),
                @ApiImplicitParam(name = "password",value = "密码MD5"),
                @ApiImplicitParam(name = "role",value = "角色，2是商家，1是用户")
        })
        public Result setLoginPassword(String phone, String password,Integer role){
                return service.setLoginPassword(phone,password,role);
        }

}
