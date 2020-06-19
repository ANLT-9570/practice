package com.dg.main.repository.users;

import com.dg.main.Entity.Follow;
import com.dg.main.Entity.users.UserCoupons;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserCouponsRepository extends JpaRepository<UserCoupons,Long>, JpaSpecificationExecutor<UserCoupons> {
    UserCoupons findByUserIdAndShopId(Long userId,Long shopId);
    List<UserCoupons> findByUserId(Long userId, Pageable pageable);
    List<UserCoupons> findByUserId(Long userId);
}
