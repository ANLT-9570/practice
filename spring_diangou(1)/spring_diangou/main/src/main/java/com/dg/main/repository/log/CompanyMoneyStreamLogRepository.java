package com.dg.main.repository.log;

import com.dg.main.Entity.logs.CompanyMoneyStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyMoneyStreamLogRepository extends JpaRepository<CompanyMoneyStreamLog,Long> , JpaSpecificationExecutor<CompanyMoneyStreamLog> {
}
