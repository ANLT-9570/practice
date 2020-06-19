package com.dg.main.controller.app.common.money;

import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.serviceImpl.users.UserThirdAccountService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/v1/userThirdAccount")
@Api(tags ="通用------用户第三方账号")
public class CommonUserThirdAccountController {
    @Autowired
    UserThirdAccountService userThirdAccountService;
    
    //
//    @RequestMapping("/save")
//    public Result save(){
//      return userThirdAccountService.rxUpdate();
//    }
//    @PostMapping("/update")
//    @ApiOperation(value = "更新用户第三方账号")
//    public Result update(@RequestBody UserThirdAccount userThirdAccount){
//        System.out.println(userThirdAccount);
//        userThirdAccountService.update(userThirdAccount);
//        return Result.successResult();
//    }
    @PostMapping("/updateZhifubaoAccount")
    @ApiOperation(value = "更新用户第三方支付宝账号")
    public Result updateZhifubaoAccount(@RequestBody UserThirdAccount userThirdAccount){
        return userThirdAccountService.updateZhifubao(userThirdAccount);
    }
    @PostMapping("/updateWeixinAccount")
    @ApiOperation(value = "更新用户第三方微信账号")
    public Result updateWeixinAccount(@RequestBody UserThirdAccount userThirdAccount){
        return userThirdAccountService.updateWeixi(userThirdAccount);
    }
//    @RequestMapping("/findOne"
//    )
//    @ApiOperation(value = "根据ID获取用户第三方账号")
//    public Result findOne(Long id){
//        return Result.successResult(userThirdAccountService.findBy(id));
//    }
    @RequestMapping("/findByUserId")
    @ApiOperation(value = "根据用户ID获取用户第三方账号")
    public Result findByUserId(Long id){
        return Result.successResult(userThirdAccountService.findByUserId(id));
    }
}
