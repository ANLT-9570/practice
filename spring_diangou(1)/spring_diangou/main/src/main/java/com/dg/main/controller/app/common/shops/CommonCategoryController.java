package com.dg.main.controller.app.common.shops;

import com.dg.main.dto.PersonDto;
import com.dg.main.serviceImpl.shop.CategoryService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/v1/category")
@Api(tags = "通用---商品一级分类")
public class CommonCategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/all")
    @ApiOperation(value = "返回商品列表")
    public Result all(){
        return Result.successResult(categoryService.findAll());
    }
    @RequestMapping("/test")
    public Result test(){
        return Result.successResult(new PersonDto());
    }
    @GetMapping("/findById")
    @ApiOperation(value = "根据id查询",notes = "findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id")
    })
    public Result findById(Long id){
        return categoryService.findById(id);
    }

}
