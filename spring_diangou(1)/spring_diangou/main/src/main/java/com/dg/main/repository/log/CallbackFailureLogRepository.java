package com.dg.main.repository.log;

import com.dg.main.Entity.logs.CallbackFailureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CallbackFailureLogRepository extends JpaRepository<CallbackFailureLog,Long>, JpaSpecificationExecutor<CallbackFailureLog> {
}
