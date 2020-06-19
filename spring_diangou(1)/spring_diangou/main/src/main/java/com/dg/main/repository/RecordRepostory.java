package com.dg.main.repository;

import com.dg.main.Entity.Record;
import com.dg.main.vo.RecordVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepostory  extends JpaRepository<Record,Long>, JpaSpecificationExecutor<Record> {

    List<Record> findByUserId(Long userId);

    @Query(nativeQuery = true,value = "select * from record r where user_id = :userId and specifications_id = :specificationsId and browse_time like  :fordate12 ")
    Record findByUserIdAndSpecificationsIdAndBrowseTimeLikess(@Param("userId")Long userId, @Param("specificationsId")Long specificationsId,@Param("fordate12")String fordate1);

    @Query(nativeQuery = true,value = "select * from record where user_id = :userId group by DATE_FORMAT(browse_time,'%y-%m-%d') limit :offset,:limit")
    List<Record> findByUserIdGroupBy(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);

    @Query(nativeQuery = true,value = "select * from record where user_id = :userId and browse_time like :newbrowseTime ")
    List<Record> findByUserIdAndBrowseTime(@Param("userId")Long userId, @Param("newbrowseTime") String newbrowseTime);

    @Query(nativeQuery = true,value = "select * from record where shop_id = (:shopId) and browse_time like (:dateTime)")
    List<Record> findByShopIdNow(@Param("shopId")Long shopId,@Param("dateTime") String dateTime);
}
