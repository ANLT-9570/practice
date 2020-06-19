package com.dg.main.repository;

import com.dg.main.Entity.AppComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppCommentsRepostory extends JpaRepository<AppComment,Long>, JpaSpecificationExecutor<AppComment> {
}
