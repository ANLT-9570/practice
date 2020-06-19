package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.logs.RedpackAreaRankLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RedpackAreaRankLogRepository  extends JpaRepository<RedpackAreaRankLog,Long>, JpaSpecificationExecutor<RedpackAreaRankLog> {

    @Query(nativeQuery = true,value = "select MAX(create_time) from red_pack_area_rank_log")
    String findByMaxCreateTime();

    @Query(nativeQuery = true,value = "select * from red_pack_area_rank_log rls " +
                                            "where rls.city =" +
                                                "( " +
                                                    "select rl.city from red_pack_area_rank_log rl " +
                                                    " where shop_id = (:shopId) and rl.create_time like (:createTime) " +
                                                ")" +
                                            "and rls.create_time like (:createTime) order by money desc")
    List<RedpackAreaRankLog> findByShopIdAndCreateTime(@Param("shopId")Long shopId,@Param("createTime") String createTime);

    @Query(nativeQuery = true,value = "select * from red_pack_area_rank_log rls " +
            "where rls.city =" +
            "( " +
            "select rl.city from red_pack_area_rank_log rl " +
            " where shop_id = (:shopId) and rl.create_time =(select MAX(create_time) from red_pack_area_rank_log) " +
            ")" +
            "and rls.create_time  =(select MAX(create_time) from red_pack_area_rank_log) order by money desc")
    List<RedpackAreaRankLog> findByShopIdAndCreateTimeGorup(@Param("shopId")Long shopId);
}
