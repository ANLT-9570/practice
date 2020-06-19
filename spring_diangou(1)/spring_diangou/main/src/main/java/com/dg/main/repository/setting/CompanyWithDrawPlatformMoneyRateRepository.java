package com.dg.main.repository.setting;

import com.dg.main.Entity.settings.CompanyWithDrawPlatformMoneyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyWithDrawPlatformMoneyRateRepository extends JpaRepository<CompanyWithDrawPlatformMoneyRate,Long>, JpaSpecificationExecutor<CompanyWithDrawPlatformMoneyRate> {
    CompanyWithDrawPlatformMoneyRate findByTypes(Integer types);
}
