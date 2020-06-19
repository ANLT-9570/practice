package com.dg.main.controller.app.buyer;

import java.util.List;

import com.dg.main.serviceImpl.users.UserAddressService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.users.CustomerAddress;

//地址表操作
@RestController
@RequestMapping("/buyer/App/appCustomerAddress/")
@Api(tags = "用户---地址")
public class BuyerCustomerAddressController {


    @Autowired
    UserAddressService service;

    @GetMapping("selectByID")
    @ApiOperation(value = "selectByID", notes = "查询地址")
    @ApiImplicitParams({@ApiImplicitParam(name = "userid", value = "用户id")
    })
    public Result selectByID(Long userid, @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "15") Integer limit) {

        return service.selectByID(userid,offset,limit);

    }

    //@PostMapping("addCustomerAddressID")
//@ApiOperation(value = "addCustomerAddressID",notes = "添加地址")
//@ApiImplicitParams({@ApiImplicitParam(name = "customerAddress",value = "地址信息")
//})
//public Result addCustomerAddressID(CustomerAddress customerAddress){
////	String address = customerAddress.getAddress();//获取地址
//	Long address = customerAddress.getCustomerAddress();
//	if(address != null) {//如果id不为空说明是修改地址
//		int update = cAddressServer.update(customerAddress);
//		if(update>0) {
//			return Result.successResult("up");
//		}else {
//			return Result.failureResult("400", "失败了");
//		}
//
//	}else {
//		int sizes=cAddressServer.save(customerAddress);
//		if(sizes > 0) {
//			return Result.successResult("add");
//		}else {
//			return Result.failureResult("400", "失败了");
//		}
//	}
//}
    @PostMapping(value = "/update")
    public Result update(@RequestBody CustomerAddress item) {

        return service.update(item);
    }
	@PostMapping(value = "/save")
    public Result save(@RequestBody CustomerAddress item) {

        return service.save(item);
    }


    //保存默认地址
    //修改
//    @GetMapping("defind")
//    @ApiOperation(value = "defind", notes = "保存默认地址")
//    @ApiImplicitParams({@ApiImplicitParam(name = "uid", value = "用户id")
//    })
//    public Result defind(Long uid) {
//
//        ModelMap map = new ModelMap();
//        int save = cAddressServer.defindUp(uid);
//        if (save > 0) {
//            map.put("data", 200);
//            return Result.successResult();
//        }
//        map.put("data", 400);
//        return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//    }

    @PostMapping("/setDefault")
    public Result setDefault(Long addressId, Long userId) {
       return service.setDefault(addressId,userId);
    }

    @DeleteMapping(value = "/deleteAddrsses")
    public Result deleteAddrsses(@RequestBody List<CustomerAddress> customerAddresses) {
      //  repository.deleteBy(customerAddresses);
      //  repository.deleteAll(customerAddresses);
        System.out.println(customerAddresses);
     return service.deleteAddrsses(customerAddresses);
    }
//    @RequestMapping(value = "/delete",produces="application/json;charset=UTF-8")
//    public Result delete(CustomerAddress item){
//       return service.
//    }

    @DeleteMapping("/deleteByUserId")
    public Result deleteByUserId(Long userId) {
       return service.deleteByUserId(userId);
    }


}
