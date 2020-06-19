package com.dg.main.repository.log;

import com.dg.main.Entity.logs.UserMoneyStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserMoneyStreamLogRepository extends JpaRepository<UserMoneyStreamLog,Long>, JpaSpecificationExecutor<UserMoneyStreamLog> {
}
