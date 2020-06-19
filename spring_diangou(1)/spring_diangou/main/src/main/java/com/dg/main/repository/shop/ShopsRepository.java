package com.dg.main.repository.shop;

import com.dg.main.Entity.shop.ShopCar;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.vo.ShopsVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ShopsRepository    extends JpaRepository<Shops,Long>, JpaSpecificationExecutor<Shops> {
    List<Shops> findByUserId(Long userId, Pageable pageable);

    @Query(value = "select * from shops where user_id = (:userId)",nativeQuery = true)
    List<Shops> findByUserId(@Param("userId")Long userId);

    @Query(value = "select * from shops where id in(:list)",nativeQuery = true)
    List<Shops> findById(@Param("list") List<Long> list);

    @Query(nativeQuery = true,
    value = "select ps.id,ps.display_img,ps.name,sd.money from shops ps left join "
    +"(select s.id,sum(k.platform_money) as money from shops s left join red_pack k on s.id = k.shop_id where k.create_time like :time group by k.shop_id  order by money desc ) "
    +"sd on ps.id = sd.id limit :offset,:limit"
    )
    List<ShopsVo> show(@Param("time") String time, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Query(nativeQuery = true,value = "select s.id from shops s where s.city like :city limit :offset,:limit")
    List<Long> findByCity(@Param("city")String city,@Param("offset")Integer offset,@Param("limit") Integer limit);

    @Query(nativeQuery = true,value = "select * from shops where category_id = :categoryId and city like :city limit :offset,:limit")
    List<Shops> findByCategoryIdAndCity(@Param("categoryId")Long categoryId,@Param("city") String city,@Param("offset") Integer offset,@Param("limit")  Integer limit);

    @Query(nativeQuery = true,value = "select * from shops where category_id = :categoryId limit :offset,:limit")
    List<Shops> findByCategoryId(@Param("categoryId")Long categoryId,@Param("offset") Integer offset,@Param("limit")  Integer limit);

    @Query(nativeQuery = true,value = "select * from shops s where s.city like :city limit :offset,:limit")
    List<Shops> findByCityAll(@Param("city")String city,@Param("offset")Integer offset,@Param("limit") Integer limit);

    @Query(value = "select * from shops order by rand() limit :offset,:limit",nativeQuery = true)
    List<Shops> findAllRand(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Query(value = "select * from shops limit :offset,:limit",nativeQuery = true)
    List<Shops> findAll(@Param("offset") Integer offset,@Param("limit") Integer limit);

    Shops findByIdAndUserId(Long shopId,Long userId);

    @Query(value = "select * from shops s right join red_pack k on k.shop_id = s.id where s.id in(:shopsId) and k.send_time > :timestamp",nativeQuery = true)
    List<Shops> findByShopsIdAndSendTime(@Param("shopsId") List<Long> shopsId,@Param("timestamp")Timestamp timestamp);

    Shops findByName(String name);

    @Modifying
    @Query(value = "delete from shops where user_id = (:userId)",nativeQuery = true)
    void deleteByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = "update shops set is_default = 1 where user_id = (:userId)",nativeQuery = true)
    void updateIsDefaultAndUserId(@Param("userId")Long userId);

    @Modifying
    @Query(value = "update shops set is_default = 2 where user_id = (:userId) and id = (:shopId)",nativeQuery = true)
    void updateByIdIsDefaultAndUserId(@Param("userId")Long userId, @Param("shopId")Long shopId);

    @Query(value = "select * from shops group by city",nativeQuery = true)
    List<Shops> findByCityGroup();

    @Query(nativeQuery = true,value = "select * from shops where user_id = ( " +
            "select mobile_use_id from mobile_user where mobile_use_id = (:mobileUseId) " +
            ") and is_default = 2 " )
    Shops findByUserIds(@Param("mobileUseId")Long mobileUseId);

    @Query(value = "select * from shops limit :offset,:limit",nativeQuery = true)
    List<Shops> findByStatusPage(@Param("limit")int limit,@Param("offset") int offset);

    @Query(nativeQuery = true,value = "select count(*) from shops")
    Long findPageCount();

    @Query(nativeQuery = true,value = "select * from shops where user_id = (:mobileUseId) and is_default = 1")
    Shops findByUserIdAndIsDefault(@Param("mobileUseId")Long mobileUseId);
}
