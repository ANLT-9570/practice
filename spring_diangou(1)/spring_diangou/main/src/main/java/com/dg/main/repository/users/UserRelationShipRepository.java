package com.dg.main.repository.users;

import com.dg.main.Entity.User;
import com.dg.main.Entity.users.UserRelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRelationShipRepository extends JpaRepository<UserRelationShip,Long>, JpaSpecificationExecutor<UserRelationShip> {
    UserRelationShip findByShipUserId(Long userId);

    List<UserRelationShip> findByUserId(Long userId);

    @Query(value = "select * from user_relation_ship where user_id = (:userId) and create_time like (:dataTime)",nativeQuery = true)
    List<UserRelationShip> findByUserIdAndCreateTime(@Param("userId") Long userId, @Param("dataTime")String dataTime);
}
