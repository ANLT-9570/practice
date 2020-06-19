package com.dg.main.repository.message;

import com.dg.main.Entity.message.UserSystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserSystemMessageRepository extends JpaRepository<UserSystemMessage,Long> , JpaSpecificationExecutor<UserSystemMessage> {
}
