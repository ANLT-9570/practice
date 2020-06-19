package com.yzl.mapper;

import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlUserMapper {
    int countByExample(YzlUserExample example);

    int deleteByExample(YzlUserExample example);

    int deleteByPrimaryKey(long id);

    int insert(YzlUser record);

    int insertSelective(YzlUser record);

    List<YzlUser> selectByExample(YzlUserExample example);

    YzlUser selectByPrimaryKey(long id);

    int updateByExampleSelective(@Param("record") YzlUser record, @Param("example") YzlUserExample example);

    int updateByExample(@Param("record") YzlUser record, @Param("example") YzlUserExample example);

    int updateByPrimaryKeySelective(YzlUser record);

    int updateByPrimaryKey(YzlUser record);

	YzlUser selectByUsername(@Param("username") String username);
}