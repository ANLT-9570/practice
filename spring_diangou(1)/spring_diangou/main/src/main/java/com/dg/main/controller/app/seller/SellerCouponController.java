package com.dg.main.controller.app.seller;

import com.dg.main.Entity.users.Coupon;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.CouponServiceImpl;
import com.dg.main.serviceImpl.users.UserCouponsService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/v1/coupon/")
@Api(tags = "商家-优惠券")
public class SellerCouponController {
    @Autowired
    CouponServiceImpl couponService;
    @Autowired
    UserCouponsService userCouponsService;

//    @GetMapping("findAll")
//    @ResponseBody
//    @ApiOperation(value = "findAll",notes = "分页查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset",value = "第几页"),
//            @ApiImplicitParam(name = "limit",value = "每页显示数量")
//    })
//    public Result findAll(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//
//        return Result.successResult(couponService.selectAll(offset, limit,null));
//    }

    @PostMapping(value="/addCoupon")
    @ApiOperation(value = "商铺添加购物卷",notes = "addCoupon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon",value = "购物券信息"),
    })
    public Result addCoupon(@RequestBody Coupon coupon) {
        return couponService.addCoupon(coupon);
    }

    @PostMapping("/update")
    @ApiOperation(value = "update",notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coupon",value = "优惠券信息")
    })
    public Result update(Coupon coupon){
       return couponService.updateCoupon(coupon);
        //return Result.successResult("");
    }

    @ApiOperation(value = "findOne",notes = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "购物券id")
    })
    @GetMapping("/findOne")
    public Result findOne(@RequestParam Long id){
//        return Result.successResult(couponService.findBy(id));
        return Result.successResult();
    }
    @DeleteMapping("/delete")
    @ApiOperation(value = "delete",notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "优惠券id")
    })
    public Result delete(@RequestParam Long id){
     return   couponService.deleteBy(id);
      //  return Result.successResult("");
    }
    @GetMapping("/findByShopId")
    @ApiOperation(value = "根据商铺id查询",notes = "findByShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result findByShopId(@RequestParam("shopId") Long  shopId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit) {

        return userCouponsService.findByShopId(shopId,offset,limit);
    }


    //	删除购物卷
    @DeleteMapping(value="/delShopsCouponId")
    @ApiOperation(value = "店铺单个删除购物卷",notes = "delShopsCouponId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopsCouponId",value = "店铺购物券id")
    })
    public Result delShopsCouponId(Long shopsCouponId) {
        if(shopsCouponId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        return userCouponsService.delShopsCouponId(shopsCouponId);
    }
}
