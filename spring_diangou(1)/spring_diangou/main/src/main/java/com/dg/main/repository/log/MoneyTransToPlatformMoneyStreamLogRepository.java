package com.dg.main.repository.log;

import com.dg.main.Entity.logs.MoneyTransToPlatformMoneyStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoneyTransToPlatformMoneyStreamLogRepository extends JpaRepository<MoneyTransToPlatformMoneyStreamLog,Long>, JpaSpecificationExecutor<MoneyTransToPlatformMoneyStreamLog> {
}
