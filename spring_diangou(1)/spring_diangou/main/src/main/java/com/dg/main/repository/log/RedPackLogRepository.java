package com.dg.main.repository.log;

import com.dg.main.Entity.logs.RedPackLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RedPackLogRepository extends JpaRepository<RedPackLog,Long>, JpaSpecificationExecutor<RedPackLog> {
    List<RedPackLog> getByUserId(Long userId, Pageable pageable);
    List<RedPackLog> getByShopId(Long shopId,Pageable pageable);

    @Query(nativeQuery = true,value = "select * from red_pack_log where user_id = :userId order by take_time desc limit 1")
    RedPackLog findByUserIdBest(@Param("userId") Integer userId);

    @Query(nativeQuery = true,value = "select * from red_pack_log where shop_id = (:shopId) and create_time like (:dateTime)")
    List<RedPackLog> findByShopIdAndCreateTime(@Param("shopId")Long shopId,@Param("dateTime") String dateTime);

    @Query(nativeQuery = true,value = "select * from red_pack_log where user_id = (:userId) and create_time like (:dateTime)")
    List<RedPackLog> findByUserIdAndCreateTime(@Param("userId")Long userId,@Param("dateTime") String dateTime);

    @Query(value = "select * from red_pack_log group by shop_id",nativeQuery = true)
    List<RedPackLog> findByShopIdAllGroup();

    @Query(value = "select count(take_money) from red_pack_log where shop_id = (:shopId)",nativeQuery = true)
    Long findByShopIdCountMoney(@Param("shopId")Long shopId);

    List<RedPackLog> findByShopId(Long shopId);

    @Query(nativeQuery = true,value = "select count(take_money) from red_pack_log where user_id = (:userId) and create_time like (:dateTime)")
    Long findByUserIdCountAndCreateTime(@Param("userId")Integer userId,@Param("dateTime") String dateTime);
}
