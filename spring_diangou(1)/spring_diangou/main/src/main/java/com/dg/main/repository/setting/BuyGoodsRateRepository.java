package com.dg.main.repository.setting;

import com.dg.main.Entity.settings.BuyGoodsRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuyGoodsRateRepository extends JpaRepository<BuyGoodsRate,Integer>, JpaSpecificationExecutor<BuyGoodsRate> {
    BuyGoodsRate findByTypes(Integer types);
}
