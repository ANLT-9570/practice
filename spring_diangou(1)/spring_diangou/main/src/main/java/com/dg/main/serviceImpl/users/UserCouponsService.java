package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.Coupon;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserCouponItem;
import com.dg.main.Entity.users.UserCoupons;
import com.dg.main.dto.UserCouponsDao;
import com.dg.main.dto.UserCouponsItemDao;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.CouponRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserCouponItemRepository;
import com.dg.main.repository.users.UserCouponsRepository;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserCouponsService {
    @Autowired
    UserCouponsRepository userCouponsRepository;
    @Autowired
    UserCouponItemRepository userCouponItemRepository;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    ShopsRepository shopsRepository;
    @Autowired
    private MobileUserRepository mobileUserRepository;


    @Transactional
    public Result add(Long userId, Long shopId,Long couponId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        if(shopId==null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        if(couponId==null){
            return Result.failureResult(ExceptionCode.Failure.NOT_COUPON);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }

        UserCoupons userCoupons=userCouponsRepository.findByUserIdAndShopId(userId,shopId);
        if(userCoupons==null){
            userCoupons=new UserCoupons();
            userCoupons.setShopId(shopId);
            userCoupons.setUserId(userId);
            userCouponsRepository.save(userCoupons);
            UserCouponItem userCouponItem=new UserCouponItem();
            userCouponItem.setUserCouponId(userCoupons.getId());
           // userCouponItem.setSpecificationId(specificationId);
            userCouponItem.setCouponId(couponId);
            userCouponItemRepository.save(userCouponItem);
            return Result.successResult();
        }else{
            UserCouponItem userCouponItem=userCouponItemRepository.findByUserCouponIdAndCouponId(userCoupons.getId(),couponId);
            if(userCouponItem==null){
                userCouponItem=new UserCouponItem();
             //   userCouponItem.setSpecificationId(specificationId);
                userCouponItem.setUserCouponId(userCoupons.getId());
                userCouponItem.setCouponId(couponId);
                userCouponItemRepository.save(userCouponItem);
                return Result.successResult();
            }else{
                return Result.failureResult(ExceptionCode.Failure.ADD_FAILURE);
            }
        }
    }
//    @Transactional
//    public Result delete(Long userId,Long shopId){
//        List<UserCoupons> userCoupons=userCouponsRepository.findByUserIdAndShopId(userId,shopId);
//
//    }
    @Transactional
    public Result getUserList(Long userId, Pageable pageable){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        List<UserCoupons> userCoupons=userCouponsRepository.findByUserId(userId,pageable);
        List<UserCouponsDao> userCouponsDaos= Lists.newArrayList();
        for (UserCoupons item:userCoupons){
            UserCouponsDao userCouponsDao=new UserCouponsDao();
            List<UserCouponsItemDao> userCouponsItemDaos=Lists.newArrayList();
            List<UserCouponItem> userCouponItems=userCouponItemRepository.findByUserCouponId(item.getId());
            Shops shops=shopsRepository.getOne(item.getShopId());
            for (UserCouponItem i:userCouponItems){
                Coupon coupon=couponRepository.getOne(i.getCouponId());
                UserCouponsItemDao userCouponsItemDao=new UserCouponsItemDao();
                BeanUtils.copyProperties(i,userCouponsItemDao);
                userCouponsItemDao.setCondition(coupon.getConditions());
                userCouponsItemDao.setStartTime(coupon.getStartTime());
                userCouponsItemDao.setEndTime(coupon.getEndTime());
                userCouponsItemDao.setPrice(coupon.getPrice().toString());
                userCouponsItemDao.setShopImage(shops.getDisplayImg());
                userCouponsItemDaos.add(userCouponsItemDao);
            }

            userCouponsDao.setShopName(shops.getName());
            userCouponsDao.setItems(userCouponsItemDaos);
            userCouponsDaos.add(userCouponsDao);
        }
        return Result.successResult(userCouponsDaos);
    }

    @Transactional
    public Result deleteByUserId(Long userId){
        List<UserCoupons> userCoupons=userCouponsRepository.findByUserId(userId);
        userCoupons.stream().forEach(i->{
            List<UserCouponItem> userCouponItems=userCouponItemRepository.findByUserCouponId(i.getId());
            userCouponItemRepository.deleteInBatch(userCouponItems);
        });
        userCouponsRepository.deleteInBatch(userCoupons);
        return Result.successResult();
    }
    @Transactional
    public Result deleteByItemId(Long itemId){
        UserCouponItem userCouponItem=userCouponItemRepository.getOne(itemId);
        Long id=userCouponItem.getUserCouponId();
        userCouponItemRepository.delete(userCouponItem);
        List<UserCouponItem> userCouponItems=userCouponItemRepository.findByUserCouponId(id);
        if(userCouponItems.size()==0){
            userCouponsRepository.deleteById(id);
        }
        return Result.successResult();
    }

    public Result addCoupon(Coupon coupon) {
        couponRepository.save(coupon);
        return Result.successResult();
    }

    public Result findByShopId(Long shopId,Integer offset,Integer limit) {
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        List<Coupon> coupons = couponRepository.findByShopId(shopId,offset,limit);
        return Result.successResult(coupons);
    }

    public Result findByShopIdAndUserId(Long userId, Long shopId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        if(shopId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        Optional<Shops> optional_ = shopsRepository.findById(shopId);
        if(!optional_.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        UserCoupons coupons = userCouponsRepository.findByUserIdAndShopId(userId, shopId);
        if (coupons != null){
            List<UserCouponItem> userCouponItems = userCouponItemRepository.findByUserCouponId(coupons.getId());
            List<Coupon> list = Lists.newArrayList();

            for (UserCouponItem userCouponItem : userCouponItems) {
                Long couponId = userCouponItem.getCouponId();
                Optional<Coupon> optional = couponRepository.findById(couponId);
                optional.get().setCreateTime( userCouponItem.getCreateTime());
//                Coupon coupon = new Coupon();
//                BeanUtils.copyProperties(userCouponItem,coupon);
                list.add(optional.get());
            }
            return Result.successResult(list);
        }
        return Result.successResult();
    }
    @Transactional
    public Result delShopsCouponId(Long shopsCouponId) {
        if(shopsCouponId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
//        Optional<Coupon> optional = couponRepository.findById(shopsCouponId);
//        if(!optional.isPresent()){
//            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
//        }
        couponRepository.deleteById(shopsCouponId);
//        List<UserCouponItem> userCouponItems=userCouponItemRepository.findByCouponId(shopsCouponId);

        userCouponItemRepository.deleteByShopId(shopsCouponId);

        return Result.successResult();
    }
}
