package com.dg.main.repository.users;

import com.dg.main.Entity.users.UserThirdAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserThirdAccountRepository extends JpaRepository<UserThirdAccount,Long>, JpaSpecificationExecutor<UserThirdAccount> {
    UserThirdAccount findByUserId(Long userId);
}
