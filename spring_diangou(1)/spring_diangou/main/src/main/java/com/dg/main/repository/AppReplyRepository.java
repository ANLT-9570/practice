package com.dg.main.repository;

import com.dg.main.Entity.AppReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppReplyRepository extends JpaRepository<AppReply,Long>, JpaSpecificationExecutor<AppReply> {

}
