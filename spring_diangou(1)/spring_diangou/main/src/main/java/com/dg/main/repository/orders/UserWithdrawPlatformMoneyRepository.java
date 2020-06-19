package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserWithdrawPlatformMoneyRepository extends JpaRepository<UserWithdrawPlatformMoney,Long>, JpaSpecificationExecutor<UserWithdrawPlatformMoney> {
    List<UserWithdrawPlatformMoney> findByUserId(Long userId, Pageable pageable);

    UserWithdrawPlatformMoney findByWithdrawStreamCode(String code);
}
