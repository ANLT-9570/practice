package com.dg.main.repository.log;

import com.dg.main.Entity.logs.ZhifubaoCallbackFailureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ZhifubaoCallbackFailureLogRepository extends JpaRepository<ZhifubaoCallbackFailureLog,Long>, JpaSpecificationExecutor<ZhifubaoCallbackFailureLog> {
}
