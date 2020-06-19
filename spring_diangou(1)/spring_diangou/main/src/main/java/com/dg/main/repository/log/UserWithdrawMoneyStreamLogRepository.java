package com.dg.main.repository.log;

import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserWithdrawMoneyStreamLogRepository extends JpaRepository<UserWithdrawMoneyStreamLog,Long> , JpaSpecificationExecutor<UserWithdrawMoneyStreamLog> {
}
