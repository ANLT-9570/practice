package com.dg.main.repository.users;

import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserBankCardRepository   extends JpaRepository<UserBankCard,Long>, JpaSpecificationExecutor<UserBankCard> {
    List<UserBankCard> findByUserId(Long userId);
}
