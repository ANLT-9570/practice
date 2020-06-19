package com.yzl.mapper;

import com.yzl.pojo.YzlRoleMenu;
import com.yzl.pojo.YzlRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlRoleMenuMapper {
    int countByExample(YzlRoleMenuExample example);

    int deleteByExample(YzlRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlRoleMenu record);

    int insertSelective(YzlRoleMenu record);

    List<YzlRoleMenu> selectByExample(YzlRoleMenuExample example);

    YzlRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlRoleMenu record, @Param("example") YzlRoleMenuExample example);

    int updateByExample(@Param("record") YzlRoleMenu record, @Param("example") YzlRoleMenuExample example);

    int updateByPrimaryKeySelective(YzlRoleMenu record);

    int updateByPrimaryKey(YzlRoleMenu record);

	List<Integer> selectBymenuId(@Param("menuId")Long menuId);
}