package com.dg.main.controller.admin;

import com.dg.main.Entity.shop.SubCategory;
import com.dg.main.serviceImpl.shop.SubCategoryService;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/sub/category")
public class AdminSubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/loadTree")
    @ResponseBody
    public Result loadTree(){
        return subCategoryService.loadTree();
    }


    @GetMapping("/add")
    @ResponseBody
    public Result add(SubCategory subCategory){
        return subCategoryService.add(subCategory);
    }


    @GetMapping("/all")
    @ResponseBody
    public slzcResult pageAll(@RequestParam(value = "",defaultValue = "0") Integer offset, @RequestParam(value = "",defaultValue = "15") Integer limit){
        return subCategoryService.pageAll(offset,limit);
    }


    @GetMapping("/del")
    @ResponseBody
    public Result del(@RequestParam(value = "ids",required = true) List<Long> ids){
        return subCategoryService.del(ids);
    }


    @PostMapping("/up")
    @ResponseBody
    public Result up(SubCategory subCategory){
        return subCategoryService.add(subCategory);
    }

//    @GetMapping("/getList")
//    @ApiOperation(value = "根据父级id分页查询",notes = "getList")
//    @ResponseBody
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "父级id")
//    })
//    public slzcResult getList(Long id, @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "15") Integer limit){
//
//        return subCategoryService.findByParentIdList(id,offset,limit);
//    }


    @GetMapping("/skip")
    @ApiOperation(value = "SkipPage",notes = "页面跳转")
    public String SkipPage() {
        System.out.println("-------------------------------");
        return "order/adminSubCategory";
    }
}
