package com.yzl.mapper;

import com.yzl.pojo.YzlLog;
import com.yzl.pojo.YzlLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlLogMapper {
    int countByExample(YzlLogExample example);

    int deleteByExample(YzlLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlLog record);

    int insertSelective(YzlLog record);

    List<YzlLog> selectByExample(YzlLogExample example);

    YzlLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlLog record, @Param("example") YzlLogExample example);

    int updateByExample(@Param("record") YzlLog record, @Param("example") YzlLogExample example);

    int updateByPrimaryKeySelective(YzlLog record);

    int updateByPrimaryKey(YzlLog record);

	List<YzlLog> findLog(@Param("anumber")String anumber, @Param("time")String time,@Param("zllb") String zllb);
}