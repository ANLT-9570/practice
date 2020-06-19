package com.yzl.mapper;

import com.yzl.pojo.YzlEpxDistrict;
import com.yzl.pojo.YzlEpxDistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlEpxDistrictMapper {
    int countByExample(YzlEpxDistrictExample example);

    int deleteByExample(YzlEpxDistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlEpxDistrict record);

    int insertSelective(YzlEpxDistrict record);

    List<YzlEpxDistrict> selectByExample(YzlEpxDistrictExample example);

    YzlEpxDistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlEpxDistrict record, @Param("example") YzlEpxDistrictExample example);

    int updateByExample(@Param("record") YzlEpxDistrict record, @Param("example") YzlEpxDistrictExample example);

    int updateByPrimaryKeySelective(YzlEpxDistrict record);

    int updateByPrimaryKey(YzlEpxDistrict record);
}