package com.dg.main.repository.im;

import com.dg.main.Entity.im.ChatMate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatMateRepository extends JpaRepository<ChatMate,Long>, JpaSpecificationExecutor<ChatMate> {
    List<ChatMate> findByUserId(Long userId);
}
