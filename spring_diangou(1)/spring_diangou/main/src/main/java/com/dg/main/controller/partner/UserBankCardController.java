package com.dg.main.controller.partner;

import com.dg.main.Entity.users.UserBankCard;
import com.dg.main.serviceImpl.users.UserBankCardService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "员工端-我的银行卡")
@RequestMapping("/v1/partner/userBankCard")
public class UserBankCardController {
    @Autowired
    UserBankCardService userBankCardService;
    @PostMapping("/save")
    public Result save(@RequestBody UserBankCard item){
        userBankCardService.save(item);
        return Result.successResult();
    }
    @PostMapping("/update")
    public Result update(@RequestBody UserBankCard item){
        userBankCardService.update(item);
        return Result.successResult();
    }
    @GetMapping("/listByUserId")
    public Result listByUserId(@RequestParam Long userId){
        return userBankCardService.list(userId);
    }
    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam Long id){
        return userBankCardService.deleteById(id);
    }
}
