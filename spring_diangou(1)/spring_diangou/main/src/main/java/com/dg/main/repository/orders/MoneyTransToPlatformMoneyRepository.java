package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.MoneyTransToPlatformMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoneyTransToPlatformMoneyRepository  extends JpaRepository<MoneyTransToPlatformMoney,Long>, JpaSpecificationExecutor<MoneyTransToPlatformMoney> {
}
