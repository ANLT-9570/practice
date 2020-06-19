package com.dg.main.repository;


import com.dg.main.Entity.users.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusinessRepository extends JpaRepository<MobileUser,Long>, JpaSpecificationExecutor<MobileUser> {
}
