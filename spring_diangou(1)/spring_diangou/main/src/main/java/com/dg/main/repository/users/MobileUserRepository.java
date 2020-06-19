package com.dg.main.repository.users;

import com.dg.main.Entity.users.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MobileUserRepository   extends JpaRepository<MobileUser,Long>, JpaSpecificationExecutor<MobileUser> {


        @Query(value = "from MobileUser where mobileUseId = :mobileUseId")
        MobileUser selectMobileUse(@Param("mobileUseId")Long mobileUseId);
        @Modifying
        @Query(value = "update mobile_user set phone = :phone where mobile_use_id = :userId",nativeQuery = true)
        void updatePhone(@Param("userId") Long userId, @Param("phone") String phone);
        MobileUser findByPhoneAndLoginPassword(String phone,String loginPassword);
        MobileUser findByPhoneAndPayPassword(String phone,String payPassword);


        MobileUser findByPhoneAndRole(String phone,Integer role);

        MobileUser findByPhoneAndLoginPasswordAndIsCompanyEmployee(String phone, String password, int stat);

        MobileUser findByPhoneAndIsCompanyEmployee(String phone, int stat);

        @Query(value = "select * from mobile_user where mobile_use_id in :shipUserIds and role = 2",nativeQuery = true)
        List<MobileUser> findByMobileUserIds(@Param("shipUserIds")List<Long> shipUserIds);

        @Query(value = "select * from mobile_user where mobile_use_id in(select ship_user_id from user_relation_ship where user_id=(:userId))",nativeQuery = true)
        List<MobileUser> findByMobileUserIdRelation(@Param("userId")Long userId);
}
