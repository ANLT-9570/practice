package com.dg.main.repository;

import com.dg.main.Entity.orders.RedPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface RedPackRepository extends JpaRepository<RedPack,Long>, JpaSpecificationExecutor<RedPack> {

    @Query(value = "select sum(platform_money) as money from red_pack where shop_id = :shopId and create_time like :times",nativeQuery = true)
    Long findByShopIdAndSum(@Param("shopId") Long shopId,@Param("times")String times);

    @Query(value = "select sum(platform_money) as money from red_pack where shop_id = :shopId",nativeQuery = true)
    Long findByShopIdAndSum(@Param("shopId") Long shopId);

    @Query(value = "select k.shop_id from red_pack k  where create_time in( select MAX(create_time) from red_pack group by  shop_id) group by k.shop_id",nativeQuery = true)
    List<Long> findByShopIdGroup();

    @Query(value = "select * from red_pack where shop_id = :shopId order by send_time desc limit 1",nativeQuery = true)
    RedPack selectShopId(@Param("shopId") Long shopId);

    @Query(value = "select * from shops s right join red_pack k on k.shop_id = s.id where s.id in(:shopsId) and k.send_time > :timestamp",nativeQuery = true)
    List<RedPack> findByShopsIdAndSendTime(@Param("shopsId") List<Long> shopsId,@Param("timestamp")Timestamp timestamp);

    @Query(value = "select * from red_pack where shop_id = (:shopsId) and create_time like (:dateTime)",nativeQuery = true)
    List<RedPack> findByShopsIdAndCreateTimeCount(@Param("shopsId")Long shopId,@Param("dateTime") String dateTime);

    @Query(value = "select * from red_pack where user_id = (:userId) and create_time like (:dateTime)",nativeQuery = true)
    List<RedPack> findByUserIdAndCreateTimeCount(@Param("userId")Long userId,@Param("dateTime") String dateTime);

    @Query(value = "select * from red_pack where shop_id = (:shopId)",nativeQuery = true)
    List<RedPack> findByShopId(@Param("shopId")Long shopId);
}
