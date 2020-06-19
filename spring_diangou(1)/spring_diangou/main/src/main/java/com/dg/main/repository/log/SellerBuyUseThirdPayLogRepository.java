package com.dg.main.repository.log;

import com.dg.main.Entity.logs.SellerBuyUseThirdPayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerBuyUseThirdPayLogRepository extends JpaRepository<SellerBuyUseThirdPayLog,Long>, JpaSpecificationExecutor<SellerBuyUseThirdPayLog> {
}
