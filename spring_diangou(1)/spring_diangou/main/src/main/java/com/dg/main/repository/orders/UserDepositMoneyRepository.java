package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.UserDepositMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDepositMoneyRepository  extends JpaRepository<UserDepositMoney,Integer>, JpaSpecificationExecutor<UserDepositMoney> {
    UserDepositMoney findByDepositCode(String code);
}
