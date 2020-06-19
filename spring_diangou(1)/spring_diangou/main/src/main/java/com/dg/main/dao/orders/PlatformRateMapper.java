package com.dg.main.dao.orders;

import com.dg.main.Entity.settings.PlatformRate;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRateMapper   extends BaseMapper<PlatformRate>, BaseDao<PlatformRate> {
    PlatformRate selectByTypes(@Param("type")Integer type);
}
