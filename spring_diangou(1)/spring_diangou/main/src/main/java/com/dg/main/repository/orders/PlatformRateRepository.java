package com.dg.main.repository.orders;

import com.dg.main.Entity.settings.PlatformRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlatformRateRepository extends JpaRepository<PlatformRate,Integer>, JpaSpecificationExecutor<PlatformRate> {
    PlatformRate findByTypes(Integer types);
}
