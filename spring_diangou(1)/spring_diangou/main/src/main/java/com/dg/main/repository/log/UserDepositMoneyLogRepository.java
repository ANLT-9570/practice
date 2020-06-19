package com.dg.main.repository.log;

import com.dg.main.Entity.logs.UserDepositMoneyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDepositMoneyLogRepository extends JpaRepository<UserDepositMoneyLog,Long>, JpaSpecificationExecutor<UserDepositMoneyLog> {
}
