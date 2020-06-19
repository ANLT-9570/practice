package com.dg.main.repository.log;

import com.dg.main.Entity.logs.CompanyDailyStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDailyStreamLogRepository extends JpaRepository<CompanyDailyStreamLog,Long>, JpaSpecificationExecutor<CompanyDailyStreamLog> {
}
