package com.dg.main.controller.app.common.shops;

import com.dg.main.Entity.shop.SubCategory;
import com.dg.main.serviceImpl.shop.SubCategoryService;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/v1/subCategory")
@Api(tags = "通用-二级分类")
public class CommonSubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;
    @GetMapping("/getAll")
    @ApiOperation(value = "根据父级id",notes = "getAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "父级id")
    })
    public Result getAll(Long id){

        return subCategoryService.findByParentId(id);
    }


    @GetMapping("/findById")
    @ApiOperation(value = "根据主键id",notes = "findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "主键id")
    })
    public Result findById(Long id){

        return subCategoryService.findById(id);
    }

//    @GetMapping("/deleteById")
//    @ApiOperation(value = "根据id删除",notes = "deleteById")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "主键id")
//    })
//    public Result deleteById(Long id){
//        return subCategoryService.deleteById(id);
//    }
//
//
//    @GetMapping("/deleteByParentId")
//    @ApiOperation(value = "根据父id删除",notes = "deleteByParentId")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "父id")
//    })
//    public Result deleteByParentId(Long id){
//        return subCategoryService.deleteByParentId(id);
//    }
//
//
//    @GetMapping("/save")
//    @ApiOperation(value = "添加",notes = "save")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "subCategory",value = "二级类别信息")
//    })
//    public Result save(@RequestBody SubCategory subCategory){
//        return subCategoryService.save(subCategory);
//    }
//
//
//    @GetMapping("/update")
//    @ApiOperation(value = "修改",notes = "update")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "subCategory",value = "二级类别信息")
//    })
//    public Result update(@RequestBody SubCategory subCategory){
//        return subCategoryService.update(subCategory);
//    }
}
