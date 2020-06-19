package com.dg.main.repository;

import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDepositPlatformMoneyRepository extends JpaRepository<UserDepositPlatformMoney,Long>, JpaSpecificationExecutor<UserDepositPlatformMoney> {
}
