package com.dg.main.controller.app.buyer;

import com.dg.main.cache_service.SizeCacheService;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.serviceImpl.shop.SizeService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户--商品尺寸")
@RestController
@RequestMapping("/buyer/v1/buyerSizeCotroller")
public class BuyerSizeCotroller {
    @Autowired
    SizeService sizeService;
    @GetMapping(value = "/getSizes")
    public Result getSizes(@RequestParam Long specification){
        return sizeService.getList(specification);
    }
    @GetMapping(value = "/findByParamAndId")
    public Result findByParamAndId(@RequestParam Long specification,String color,String dimension){
        return sizeService.getListByParam(specification,color,dimension);
    }

    @GetMapping(value = "/normalSizes")
    public Result normalSizes(@RequestParam Long specification){
        return sizeService.getNormalList(specification);
    }
}
