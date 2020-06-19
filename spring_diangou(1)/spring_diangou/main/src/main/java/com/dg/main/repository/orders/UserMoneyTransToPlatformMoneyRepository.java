package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.UserMoneyTransToPlatformMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserMoneyTransToPlatformMoneyRepository extends JpaRepository<UserMoneyTransToPlatformMoney,Long> , JpaSpecificationExecutor<UserMoneyTransToPlatformMoney> {
}
