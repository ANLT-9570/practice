package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.Category;
import com.dg.main.Entity.shop.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepository  extends JpaRepository<SubCategory,Long>, JpaSpecificationExecutor<SubCategory> {
    List<SubCategory> findByParentId(Long id);

    @Query(nativeQuery = true,value = "select * from sub_category limit :offset,:limit")
    List<SubCategory> findAllPage(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Query(nativeQuery = true,value = "select count(*) from sub_category")
    Long findAllCount();

    @Modifying
    @Query(nativeQuery = true,value = "delete from sub_category where parent_id=(:id)")
    void deleteByParentId(@Param("id")Long id);

    @Query(nativeQuery = true,value = "select * from sub_category where parent_id = (:id) limit :offset,:limit")
    List<SubCategory> findByParentIdList(@Param("id")Long id,@Param("offset") Integer offset, @Param("limit")Integer limit);

    @Query(nativeQuery = true,value = "select count(*) from sub_category where parent_id = (:id)")
    Long findByParentIdListCount(@Param("id")Long id);
}
