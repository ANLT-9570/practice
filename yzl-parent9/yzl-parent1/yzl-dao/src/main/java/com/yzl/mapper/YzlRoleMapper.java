package com.yzl.mapper;

import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlRoleMapper {
    int countByExample(YzlRoleExample example);

    int deleteByExample(YzlRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlRole record);

    int insertSelective(YzlRole record);

    List<YzlRole> selectByExample(YzlRoleExample example);

    YzlRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlRole record, @Param("example") YzlRoleExample example);

    int updateByExample(@Param("record") YzlRole record, @Param("example") YzlRoleExample example);

    int updateByPrimaryKeySelective(YzlRole record);

    int updateByPrimaryKey(YzlRole record);
}