package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.logs.RedpackRankLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RedpackRankLogRepository  extends JpaRepository<RedpackRankLog,Long>, JpaSpecificationExecutor<RedpackRankLog> {

    @Query(nativeQuery = true,value = "select * from red_pack_rank_log where shop_id = (:shopId) and create_time = (select max(create_time) from red_pack_rank_log )")
    RedpackRankLog findByShopIdGroup(@Param("shopId") Long shopId);

    @Query(nativeQuery = true,value = " select * from ( " +
                                            " select * from red_pack_rank_log r " +
                                            " where create_time = (select max(create_time) from red_pack_rank_log ) group by shop_id " +
                                        " ) ss order by ss.money desc ")
    List<RedpackRankLog> findByShopIdAndCreateTimeGroup(Long shopId);
}
