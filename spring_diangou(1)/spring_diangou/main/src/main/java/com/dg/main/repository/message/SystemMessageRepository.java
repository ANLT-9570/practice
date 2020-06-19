package com.dg.main.repository.message;

import com.dg.main.Entity.message.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SystemMessageRepository extends JpaRepository<SystemMessage,Long>, JpaSpecificationExecutor<SystemMessage> {
}
