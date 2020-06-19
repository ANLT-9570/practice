package com.dg.main.serviceImpl;


import com.dg.main.Entity.users.Coupon;

import com.dg.main.Entity.shop.Shops;

import com.dg.main.repository.users.CouponRepository;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.CouponItemVo;
import com.dg.main.vo.ShopsItemVo;
import com.dg.main.vo.ShopsVo;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    SpecificationsRepository specificationsRepository;
    @Autowired
    ShopsRepository shopsRepository;
    public Result addCoupon(Coupon coupon){
        couponRepository.save(coupon);
        return Result.successResult();
    }
    public Result updateCoupon(Coupon coupon){
        couponRepository.save(coupon);
        return Result.successResult();
    }
    public Result deleteCoupon(Coupon coupon){
        couponRepository.delete(coupon);

        return Result.successResult();
    }
    public Result deleteBy(Long id){
        couponRepository.deleteById(id);
        return Result.successResult();
    }
    public Result findAll(Long userId, Integer offset, Integer limit) {

        List<Coupon> list = couponRepository.findByUserId(userId, offset, limit);
        //商铺id
        List<Long> shopIds = Lists.newArrayList();
        //购物券id
        List<Long> couIds = Lists.newArrayList();
        for (Coupon coupon : list) {
            shopIds.add(coupon.getShopId());
            couIds.add(coupon.getId());
        }

        List<Shops> shopsList =shopsRepository.findById(shopIds);

        List<ShopsVo> shopsVoList = Lists.newArrayList();

        for (Shops shops : shopsList) {
            ShopsVo shopsVo = new ShopsVo();
            shopsVoList.add(shopsVo);
        }
        List<CouponItemVo> itemList = Lists.newArrayList();

        List<Shops> shops = Lists.newArrayList();
        return Result.successResult(shopsVoList);
//        for (Coupon coupon : list) {
//
//            CouponItemVo couponItemVo = new CouponItemVo();
//
//            ShopsVo shopsVo = new ShopsVo();
//
//            ShopsItemVo shopsItemVo = new ShopsItemVo();
//
//            CouponItem item = couponItemRepository.findByCouponId(coupon.getId());
//
//            shopsItemVo.setEndTime(item.getEndTime());
//            shopsItemVo.setPreferentialPrice(item.getPreferentialPrice());
//            shopsItemVo.setConditions(item.getConditions());
//
////            shopsVo.setItemVos();
//
//            Optional<Shops> optional = shopsRepository.findById(coupon.getShopId());
//
//            shops.add(optional.get());
////            couponItemVo.setDisplayImg(optional.get().getDisplayImg());
////            couponItemVo.setShpopName(optional.get().getName());
//
//
//
//            itemList.add(couponItemVo);
//        }



//        for (CouponItem couponItem : itemList) {
//            CouponItemVo couponItemVo = new CouponItemVo();
//
//
//
//            couponItemVos.add(couponItemVo);
//        }

        //
//        PageRequest pageRequest = PageRequest.of(offset, limit);
//        Specification<CouponItem> specification = new Specification<CouponItem>() {
//            @Override
//            public Predicate toPredicate(Root<CouponItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> list = new ArrayList<>();
//                list.add(cb.equal(root.get("userId").as(Long.class),userId));
//                Predicate[] p = new Predicate[list.size()];
//                return cb.and(list.toArray(p));
//            }
//        };
//
//        Page<CouponItem> page = couponItemRepository.findAll(specification, pageRequest);
//        List<CouponItem> content = page.getContent();
//        List<CouponItemVo> couponItemVos = Lists.newArrayList();
//
//        for(CouponItem  couponItem :content){
//            CouponItemVo couponItemVo = new CouponItemVo();
//            Long shopId = 0L;//couponItem.getShopId();
//            Optional<Shops> optional = shopsRepository.findById(shopId);
//
//            couponItemVo.setShpopName(optional.get().getName());
//            couponItemVo.setDisplayImg(optional.get().getDisplayImg());
//            couponItemVos.add(couponItemVo);
//        }

    }



}
