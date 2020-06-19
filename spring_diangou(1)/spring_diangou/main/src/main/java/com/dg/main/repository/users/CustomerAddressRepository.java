package com.dg.main.repository.users;

import com.dg.main.Entity.users.CustomerAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long>, JpaSpecificationExecutor<CustomerAddress> {
    CustomerAddress findByUserIdAndIsDefault(Long userId,Integer isDefault);
    void deleteByUserId(Long userId);
    List<CustomerAddress> findByUserId(Long userId, Pageable pageable);

    @Query(nativeQuery = true,value = "select * from customer_address where user_id = (:userId) order by is_default desc limit :offset,:limit")
    List<CustomerAddress> findByUserIdPage(@Param("userId") Long userId,@Param("offset") Integer offset,@Param("limit") Integer limit);
//    void deleteBy(List<CustomerAddress> customerAddresses);

}
