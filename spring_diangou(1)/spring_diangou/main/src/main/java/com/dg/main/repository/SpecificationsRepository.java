package com.dg.main.repository;


import com.dg.main.Entity.shop.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecificationsRepository extends JpaRepository<Specifications,Long>, JpaSpecificationExecutor<Specifications> {

    @Query(value = "select * from specifications where status=1 order by rand() limit :offset,:limit",nativeQuery = true)
    List<Specifications> findAllRand(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Query(nativeQuery = true,value = "select * from specifications where shop_name like :name and shop_id in(:shopIds)")
    List<Specifications> findByShopIdAndShopName(@Param("name")String name, @Param("shopIds")List<Long> shopIds);

    @Query(nativeQuery = true,value = "select * from specifications where name like :name limit :offset,:limit")
    List<Specifications> findByName(@Param("name")String name, @Param("offset") Integer offset,@Param("limit")  Integer limit);

    @Query(nativeQuery = true,value = "select * from specifications where shop_id in(:shopIds)")
    List<Specifications> findByShopIdList(@Param("shopIds")List<Long> shopIds);

    @Query(value = "select * from specifications where shop_id=:ShopId and status = 1 limit :offset,:limit",nativeQuery = true)
    List<Specifications> findByShopId(@Param("ShopId")Long ShopId,@Param("offset") Integer offset,@Param("limit")  Integer limit);

    @Query(value = "select * from specifications where shop_id = :shopId and name like :condition limit :offset,:limit",nativeQuery = true)
    List<Specifications> findByShopIdAndName(@Param("shopId")Long shopId,@Param("condition") String condition,@Param("offset") Integer offset,@Param("limit")  Integer limit);

    @Query(value = "select * from specifications where rand() limit :offset,:limit",nativeQuery = true)
    List<Specifications> findAll(@Param("offset")Integer offset,@Param("limit") Integer limit);

    @Query(value = "select * from specifications where shop_id=:shopId  order by top asc limit :offset,:limit",nativeQuery = true)
    List<Specifications> findByShopIdAndOrderByTopAsc(@Param("shopId")Long shopId, @Param("offset")Integer offset,@Param("limit") Integer limit);

    @Modifying
    @Query(value = "update specifications set top = 2 where shop_id = (:shopId)",nativeQuery = true)
    void updateByShopIdAndTop(@Param("shopId")Long shopId);

    List<Specifications> findByUserIdAndStatus(Long userId,Integer status);

    @Modifying()
    @Query(value = "update specifications set status = (:status) where id=(:id)",nativeQuery = true)
    void updateStatus(@Param("id")Long id,@Param("status") Integer status);

    @Query(value = "select * from specifications where shop_id = (:shopId) and status = (:status) limit :offset,:limit",nativeQuery = true)
    List<Specifications> findByShopIdAndStatus(@Param("shopId")Long shopId,@Param("status") Integer status,@Param("offset")Integer offset,@Param("limit")Integer limit);

    @Modifying()
    @Query(value = "update specifications  set id_delete = 1 where user_id = (:userId)",nativeQuery = true)
    void deleteByUserId(@Param("userId")Long userId);

    @Modifying()
    @Query(value = "update specifications  set id_delete = 1 where shop_id = (:shopId)",nativeQuery = true)
    void deleteByShopId(@Param("shopId")Long shopId);

    @Modifying()
    @Query(value = "update specifications set top = 1 where id = (:specificationId)",nativeQuery = true)
    void updateByShopIdAndTopAndSpecificationId(@Param("specificationId")Long specificationId);

}
