package com.dg.main.repository.message;

import com.dg.main.Entity.message.UserOrderMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserOrderMessageRepository extends JpaRepository<UserOrderMessage,Long>, JpaSpecificationExecutor<UserOrderMessage> {
}
