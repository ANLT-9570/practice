package com.yzl.mapper;

import com.yzl.pojo.YzlQualitypromotion;
import com.yzl.pojo.YzlQualitypromotionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlQualitypromotionMapper {
    int countByExample(YzlQualitypromotionExample example);

    int deleteByExample(YzlQualitypromotionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlQualitypromotion record);

    int insertSelective(YzlQualitypromotion record);

    List<YzlQualitypromotion> selectByExample(YzlQualitypromotionExample example);

    YzlQualitypromotion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlQualitypromotion record, @Param("example") YzlQualitypromotionExample example);

    int updateByExample(@Param("record") YzlQualitypromotion record, @Param("example") YzlQualitypromotionExample example);

    int updateByPrimaryKeySelective(YzlQualitypromotion record);

    int updateByPrimaryKey(YzlQualitypromotion record);
}