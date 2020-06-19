package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.UserThirdPlatformInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserThirdPlatformInfoRepository extends JpaRepository<UserThirdPlatformInfo,Long>, JpaSpecificationExecutor<UserThirdPlatformInfo> {
}
