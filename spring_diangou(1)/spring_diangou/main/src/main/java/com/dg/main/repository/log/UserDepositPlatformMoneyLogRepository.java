package com.dg.main.repository.log;

import com.dg.main.Entity.logs.UserDepositPlatformMoneyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDepositPlatformMoneyLogRepository   extends JpaRepository<UserDepositPlatformMoneyLog,Long>, JpaSpecificationExecutor<UserDepositPlatformMoneyLog> {
}
