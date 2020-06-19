package com.dg.main.repository.users;

import com.dg.main.Entity.Follow;
import com.dg.main.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long>, JpaSpecificationExecutor<Follow> {

    Follow findByUserIdAndShopId(Long uid, Long shopId);

    @Transactional
    @Modifying
    @Query(value = "delete from Follow w where w.id in (:id)")
    void deleteIds(@Param("id") List<Long> id);

    @Transactional
    void deleteByUserId(Long userId);


}
