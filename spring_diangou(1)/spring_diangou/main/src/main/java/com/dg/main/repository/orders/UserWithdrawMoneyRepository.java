package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.UserWithdrawMoney;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserWithdrawMoneyRepository extends JpaRepository<UserWithdrawMoney,Long>, JpaSpecificationExecutor<UserWithdrawMoney> {
    List<UserWithdrawMoney> findByUserId(Integer userId, Pageable pageable);
    UserWithdrawMoney findByWithdrawStreamCode(String code);
}
