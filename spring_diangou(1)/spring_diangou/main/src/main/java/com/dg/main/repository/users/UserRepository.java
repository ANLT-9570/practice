package com.dg.main.repository.users;

import com.dg.main.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
//    @Query(value = "update user set phone = :phone where  id = :id ",nativeQuery = true)
//    void updatePhone(String phone,Long id);
}
