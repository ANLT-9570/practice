package com.dg.main.repository;


import com.dg.main.Entity.users.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaleRepository extends JpaRepository<MobileUser,Long>, JpaSpecificationExecutor<MobileUser> {
}
