package com.dg.main.repository.setting;

import com.dg.main.Entity.settings.SendOrderTimeSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SendOrderTimeSettingRepository extends JpaRepository<SendOrderTimeSetting,Long>, JpaSpecificationExecutor<SendOrderTimeSetting> {
    SendOrderTimeSetting findByTypes(Integer types);
}
