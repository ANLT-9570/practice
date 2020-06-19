package com.yzl.mapper;

import com.yzl.pojo.YzlLandscaperenovation;
import com.yzl.pojo.YzlLandscaperenovationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlLandscaperenovationMapper {
    int countByExample(YzlLandscaperenovationExample example);

    int deleteByExample(YzlLandscaperenovationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(YzlLandscaperenovation record);

    int insertSelective(YzlLandscaperenovation record);

    List<YzlLandscaperenovation> selectByExample(YzlLandscaperenovationExample example);

    YzlLandscaperenovation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") YzlLandscaperenovation record, @Param("example") YzlLandscaperenovationExample example);

    int updateByExample(@Param("record") YzlLandscaperenovation record, @Param("example") YzlLandscaperenovationExample example);

    int updateByPrimaryKeySelective(YzlLandscaperenovation record);

    int updateByPrimaryKey(YzlLandscaperenovation record);
}