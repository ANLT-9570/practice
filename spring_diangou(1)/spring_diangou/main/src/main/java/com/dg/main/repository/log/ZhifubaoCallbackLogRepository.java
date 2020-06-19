package com.dg.main.repository.log;

import com.dg.main.Entity.logs.ZhifubaoCallbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ZhifubaoCallbackLogRepository extends JpaRepository<ZhifubaoCallbackLog,Long>, JpaSpecificationExecutor<ZhifubaoCallbackLog> {
}
