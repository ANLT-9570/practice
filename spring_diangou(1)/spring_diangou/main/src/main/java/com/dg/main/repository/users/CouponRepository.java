package com.dg.main.repository.users;

import com.dg.main.Entity.users.Coupon;
import com.dg.main.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long>, JpaSpecificationExecutor<Coupon> {

    @Query(value = "select * from coupon n where user_id = :userId limit :offset,:limit",nativeQuery = true)
    List<Coupon> findByUserId(@Param("userId") Long userId,@Param("offset") Integer offset,@Param("limit") Integer limit);

//    @Query(value = "select n.shop_id from coupon n where user_id = :userId limit :offset,:limit",nativeQuery = true)
//    List<Long> findByUserId(@Param("userId") Long userId,@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Modifying
    @Query(value = "delete from Coupon n where n.id in (:id)")
    void deleteByIdAll(@Param("id")List<Long> id);

    Coupon findByUserIdAndShopId(Long userId, Long userId1);

    @Query(value = "select * from coupon where shop_id = :shopId limit :offset,:limit",nativeQuery = true)
    List<Coupon> findByShopId(@Param("shopId")Long shopId,@Param("offset") Integer offset,@Param("limit") Integer limit);
}
