package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {


    Category findByName(String name);

    @Query(nativeQuery = true,value = "select * from category  limit :offset,:limit")
    List<Category> findAllLimit(@Param("offset") Integer offset, @Param("limit")Integer limit);

    @Query(nativeQuery = true,value = "select count(*) from category")
    Long findAllCount();
}
