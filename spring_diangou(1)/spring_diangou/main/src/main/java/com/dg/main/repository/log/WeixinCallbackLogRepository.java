package com.dg.main.repository.log;

import com.dg.main.Entity.logs.WeixinCallbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeixinCallbackLogRepository extends JpaRepository<WeixinCallbackLog,Long>, JpaSpecificationExecutor<WeixinCallbackLog> {
}
