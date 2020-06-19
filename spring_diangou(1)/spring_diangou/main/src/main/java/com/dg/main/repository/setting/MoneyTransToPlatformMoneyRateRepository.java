package com.dg.main.repository.setting;

import com.dg.main.Entity.settings.MoneyTransToPlatformMoneyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoneyTransToPlatformMoneyRateRepository extends JpaRepository<MoneyTransToPlatformMoneyRate,Integer>, JpaSpecificationExecutor<MoneyTransToPlatformMoneyRate> {
    MoneyTransToPlatformMoneyRate findByTypes(Integer types);
}
