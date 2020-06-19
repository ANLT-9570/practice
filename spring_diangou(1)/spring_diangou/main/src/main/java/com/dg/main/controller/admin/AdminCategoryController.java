package com.dg.main.controller.admin;

import com.dg.main.Entity.shop.Category;
import com.dg.main.util.slzcResult;
import com.dg.main.serviceImpl.shop.CategoryService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
@Api(tags = "后台-分类")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "保存",notes = "save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "分类信息",value = "category")
    })
    public Result save(Category category){
        System.out.println(category);
        categoryService.save(category);
        return Result.successResult();
    }
    @PostMapping("/up")
    @ResponseBody
    @ApiOperation(value = "修改",notes = "up")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "分类信息",value = "category")
    })
    public Result up( Category category){
        System.out.println(category);
        categoryService.save(category);
        return Result.successResult();
    }
    @PostMapping("/del")
    @ResponseBody
    @ApiOperation(value = "删除",notes = "del")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "id")
    })
    public Result del( Long [] ids){
        categoryService.delete(ids);
        return Result.successResult();
    }
    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(value = "全部分类",notes = "all")
    public slzcResult all(@RequestParam(defaultValue = "0")Integer offset, @RequestParam(defaultValue = "15")Integer limit){
        slzcResult result = categoryService.findAllPage(offset,limit);
        return result;
    }
    @GetMapping("/skip")
    @ApiOperation(value = "SkipPage",notes = "页面跳转")
    public String SkipPage() {
        System.out.println("-------------------------------");
        return "order/adminCategory";
    }
}
