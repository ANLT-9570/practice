package com.dg.main.repository;

import com.dg.main.Entity.orders.LogisticsState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsStateRepository extends JpaRepository<LogisticsState,Long> {


    @Query(value = "select * from logistics_state where order_code = :itemCode limit 1",nativeQuery = true)
    LogisticsState findByOrderCode(@Param("itemCode") String itemCode);
}
