package com.dg.main.dao.orders;

import com.dg.main.Entity.settings.CompanyWithDrawPlatformMoneyRate;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyWithDrawPlatformMoneyRateMapper   extends BaseMapper<CompanyWithDrawPlatformMoneyRate>,BaseDao<CompanyWithDrawPlatformMoneyRate> {
    CompanyWithDrawPlatformMoneyRate selectByTypes(@Param("type")Integer type);
}
