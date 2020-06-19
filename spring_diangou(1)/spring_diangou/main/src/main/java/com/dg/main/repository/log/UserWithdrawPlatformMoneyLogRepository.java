package com.dg.main.repository.log;

import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserWithdrawPlatformMoneyLogRepository extends JpaRepository<UserWithdrawPlatformMoneyLog,Long>, JpaSpecificationExecutor<UserWithdrawPlatformMoneyLog> {
}
