package com.dg.main.repository.users;

import com.dg.main.Entity.Follow;
import com.dg.main.Entity.users.UserCouponItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCouponItemRepository  extends JpaRepository<UserCouponItem,Long>, JpaSpecificationExecutor<UserCouponItem> {
    UserCouponItem findByUserCouponIdAndSpecificationId(Long userCouponId,Long specificationId);
    UserCouponItem findByUserCouponIdAndCouponId(Long userCouponId,Long couponId);
    List<UserCouponItem> findByUserCouponId(Long userCouponId);
    List<UserCouponItem> findByCouponId(Long couponId);
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_coupon_item where coupon_id = (:shopsCouponId)")
    void deleteByShopId(@Param("shopsCouponId") Long shopsCouponId);
}
