package com.dg.main.controller.app.common.money;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.RedPackLogVo;
import io.swagger.annotations.Api;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/common/v1/redPackLog/")
@Api(tags = "通用-红包领取记录")
public class CommonRedPackLogController {

    @Autowired
    RedPackLogRepository redPackLogRepository;
    @Autowired
    private ShopsRepository shopsRepository;
    @Autowired
    private MobileUserRepository mobileUserRepository;


    @GetMapping("/getUserRedpackLog")
    public Result getUserRedpackLog(@RequestParam("userId")Integer userId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if(userId==null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(Long.valueOf(userId));
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        PageRequest pageRequest = PageRequest.of(offset, limit);
        List<RedPackLog> list = redPackLogRepository.getByUserId(Long.valueOf(userId), pageRequest);
        List<RedPackLogVo> redPackLogVos = Lists.newArrayList();
        for(RedPackLog redPackLog:list){
            Long shopId = redPackLog.getShopId();
            Optional<Shops> optional = shopsRepository.findById(shopId);

            RedPackLogVo redPackLogVo = new RedPackLogVo();
            BeanUtils.copyProperties(redPackLog,redPackLogVo);
            redPackLogVo.setShopName(optional.get().getName());
            redPackLogVos.add(redPackLogVo);
        }
        return Result.successResult(redPackLogVos);
    }
    @GetMapping("/getShopRedpackLog")
    public Result getShopRedpackLog(@RequestParam("shopId")Long shopId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if (shopId==null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        PageRequest pageRequest = PageRequest.of(offset, limit);
        List<RedPackLog> logList = redPackLogRepository.getByShopId(shopId, pageRequest);
//        logList.stream().forEach(i->{
//
//        });
        return Result.successResult(logList);
    }

}
