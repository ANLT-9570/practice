package com.yzl.mapper;

import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserRole;
import com.yzl.pojo.YzlUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YzlUserRoleMapper {
    int countByExample(YzlUserRoleExample example);

    int deleteByExample(YzlUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YzlUserRole record);

    int insertSelective(YzlUserRole record);

    List<YzlUserRole> selectByExample(YzlUserRoleExample example);

    YzlUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YzlUserRole record, @Param("example") YzlUserRoleExample example);

    int updateByExample(@Param("record") YzlUserRole record, @Param("example") YzlUserRoleExample example);

    int updateByPrimaryKeySelective(YzlUserRole record);

    int updateByPrimaryKey(YzlUserRole record);
    // 根据用户Id查询所有的角色Id -->
	List<Integer> queryRoleIdsByUserId(Integer userId);
	
    // 根据用户Id查询所有的角色Id 返回结果为字符串-->
	List<String> queryRoleIdsByUserIdResultStr(Integer userId);

	YzlUser selectByUsername(String username);
}