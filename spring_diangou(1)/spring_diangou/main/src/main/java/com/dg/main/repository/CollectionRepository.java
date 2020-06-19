package com.dg.main.repository;

import com.dg.main.Entity.MyCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CollectionRepository extends JpaRepository<MyCollection,Long>, JpaSpecificationExecutor<MyCollection> {
    MyCollection findByUserIdAndSpecificationsIdAndShopId(Long uid, Long specificationsId, Long shopId);

    @Transactional
    @Modifying
    @Query(value = "delete from MyCollection m where m.id in (:id)")
    void deleteIds(@Param("id") List<Long> id);

    @Transactional
    void deleteByUserId(Long userId);

    @Query(value = "select * from my_collection where user_id = :userId and specifications_id = :specificationId",nativeQuery = true)
    MyCollection findByUserIdAndSpecificationsId(@Param("specificationId")Long specificationId, @Param("userId")Long userId);
}
