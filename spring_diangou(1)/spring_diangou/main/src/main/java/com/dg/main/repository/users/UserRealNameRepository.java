package com.dg.main.repository.users;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.UserRealName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRealNameRepository    extends JpaRepository<UserRealName,Long>, JpaSpecificationExecutor<UserRealName> {

    UserRealName findByUserId(Long userId);

    @Query(value = "select * from user_real_name  limit :offset,:limit",nativeQuery = true)
    List<UserRealName> findAll(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Modifying
    @Query(value = "delete from user_real_name where user_id = (:userid)",nativeQuery = true)
    void deleteByUserId(@Param("userid")Long userid);
}
