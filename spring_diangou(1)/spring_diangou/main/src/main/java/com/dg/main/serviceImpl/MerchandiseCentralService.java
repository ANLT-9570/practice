package com.dg.main.serviceImpl;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MerchandiseCentralService {

    @Autowired
    SpecificationsRepository specificationsRepository;
    @Autowired
    ShopsRepository shopsRepository;

//    public Result getInSales(Long userId) {
//        if (userId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
//        }
//        List<Specifications> list = specificationsRepository.findByUserIdAndStatus(userId,1);
//        return Result.successResult(list);
//    }
//    public Result getUnShelve(Long userId) {
//        if (userId == null){
//            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
//        }
//        List<Specifications> list = specificationsRepository.findByUserIdAndStatus(userId, 2);
//
//        return Result.successResult(list);
//    }

    public Result getUpAndDown(Long shopId,Integer status,Integer offset,Integer limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        if (shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        List<Specifications> list = specificationsRepository.findByShopIdAndStatus(shopId, status,offset,limit);
        return Result.successResult(list);
    }


    @Transactional
    public Result updateStatus(Long id, Integer status) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<Specifications> optional = specificationsRepository.findById(id);
        if (!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_GOODS);
        }
        if(status == 1 && optional.get().getStatus() == 1){
            return Result.failureResult(ExceptionCode.Failure.ALREADY_SOlDOUT);
        }
        if(status == 2 && optional.get().getStatus() == 2){
            return Result.failureResult(ExceptionCode.Failure.ALREADY_PUTAWAY);
        }
        specificationsRepository.updateStatus(id,status);
        return Result.successResult();
    }


}
